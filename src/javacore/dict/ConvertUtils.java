package javacore.dict;

import javacore.beanutils.Score;
import javacore.beanutils.Student;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

public class ConvertUtils {
    public void convert(Object orig, Map<String, Map<Integer, String>> dictInfoMap) throws Exception {
        if (orig instanceof Map) {
            injectMap((Map<String, Object>) orig, dictInfoMap);
        } else if (orig instanceof List) {
            injectList((List) orig, dictInfoMap);
        } else {
            injectJavaBean(orig, dictInfoMap);
        }
    }

    private void injectList(List tmpOrigList, Map<String, Map<Integer, String>> dictInfoMap) throws Exception {
        if (tmpOrigList != null) {
            for (Object orig : tmpOrigList) {
                Class clazz = orig.getClass();
                if (isBaseAssignableFromClazz(clazz)) {
                    break;
                } else {
                    if (List.class.isAssignableFrom(clazz)) {
                        injectList((List) orig, dictInfoMap);
                        continue;
                    } else if (Map.class.isAssignableFrom(clazz)) {
                        injectMap((Map) orig, dictInfoMap);
                        continue;
                    } else {
                        injectJavaBean(orig, dictInfoMap);
                        continue;
                    }
                }
            }
        }
    }

    private void injectMap(Map<String, Object> tmpOrigMap, Map<String, Map<Integer, String>> dictInfoMap) throws Exception {
        if (tmpOrigMap != null) {
            Map<String, Object> newOrigMap = new HashMap<>(tmpOrigMap);
            for (Map.Entry<String, Object> entry : newOrigMap.entrySet()) {
                String name = entry.getKey();
                Object orig = entry.getValue();
                Class clazz = orig.getClass();

                if (isBaseAssignableFromClazz(clazz)) {
                } else {
                    if (List.class.isAssignableFrom(clazz)) {
                        injectList((List) orig, dictInfoMap);
                        continue;
                    } else if (Map.class.isAssignableFrom(clazz)) {
                        injectMap((Map) orig, dictInfoMap);
                        continue;
                    } else {
                        injectJavaBean(orig, dictInfoMap);
                        continue;
                    }
                }


                // 查看属性name在字典表中是否存在
                Map<Integer, String> dictFieldMap = existFieldInDict(name, dictInfoMap);
                if (dictFieldMap != null) {
                    if (orig instanceof Integer) {
                        tmpOrigMap.put(name + "Name", dictFieldMap.get(orig));
                    }
                }
            }
        }
    }

    private void injectJavaBean(Object orig, Map<String, Map<Integer, String>> dictInfoMap) throws Exception {
        Class clazz = orig.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] origPds = beanInfo.getPropertyDescriptors();
        int origPdsLen = origPds.length;
        for (int i = 0; i < origPdsLen; i++) {
            PropertyDescriptor origPd = origPds[i];
            String name = origPd.getName();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            Class typeClazz = origPd.getPropertyType();
            if (isBaseAssignableFromClazz(typeClazz)) {
            } else {
                if (List.class.isAssignableFrom(typeClazz)) {
                    Method readMethod = origPd.getReadMethod();
                    setAccessible(readMethod);
                    List tmpOrig = (List) readMethod.invoke(orig, null);
                    injectList(tmpOrig, dictInfoMap);
                    continue;
                } else if (Map.class.isAssignableFrom(typeClazz)) {
                    Method readMethod = origPd.getReadMethod();
                    setAccessible(readMethod);
                    Map tmpOrig = (Map) readMethod.invoke(orig, null);
                    injectMap(tmpOrig, dictInfoMap);
                    continue;
                } else {
                    Method readMethod = origPd.getReadMethod();
                    setAccessible(readMethod);
                    Object tmpOrig = readMethod.invoke(orig, null);
                    injectJavaBean(tmpOrig, dictInfoMap);
                    continue;
                }
            }


            // 查看属性name在字典表中是否存在
            Map<Integer, String> dictFieldMap = existFieldInDict(name, dictInfoMap);
            if (dictFieldMap != null) {
                for (int j = 0; j < origPdsLen; j++) {
                    PropertyDescriptor destPd = origPds[j];
                    String noNameStr = destPd.getName().replace("Name", "");
                    if (name.equals(noNameStr) && name.length() < destPd.getName().length()) {
                        // 获取orig中key对应的值value
                        Method readMethod = origPd.getReadMethod();
                        setAccessible(readMethod);
                        Object key = readMethod.invoke(orig, null);
                        // (name+Name属性)注入值
                        Method writeMethod = destPd.getWriteMethod();
                        setAccessible(writeMethod);
                        if (key instanceof Integer) {
                            String value = dictFieldMap.get(key);
                            writeMethod.invoke(orig, value);
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断clazz是否是java数据类型类的 接口或者类的 子类
     *
     * @param clazz 待判断的类类型
     * @return
     */
    private boolean isBaseAssignableFromClazz(Class clazz) {
        boolean flag = false;
        if (clazz.isAssignableFrom(String.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Integer.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Double.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Long.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Float.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(BigDecimal.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Date.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Boolean.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Character.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Short.class)) {
            flag = true;
        } else if (clazz.isAssignableFrom(Byte.class)) {
            flag = true;
        }
        return flag;
    }

    private void setAccessible(Method method) {
        if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            method.setAccessible(true);
        }
    }

    private Map<Integer, String> existFieldInDict(String name, Map<String, Map<Integer, String>> dictInfoMap) {
        Set<String> set = dictInfoMap.keySet();
        if (set.contains(name)) {
            return dictInfoMap.get(name);
        }
        return null;
    }

    private static Map<String, Map<Integer, String>> initData(Student student) {
        DictInfo dictInfo1 = new DictInfo(1000, "中国", "country_type");
        DictInfo dictInfo2 = new DictInfo(1001, "美国", "country_type");
        DictInfo dictInfo3 = new DictInfo(2000, "简单", "hard_type");
        DictInfo dictInfo4 = new DictInfo(2001, "较难", "hard_type");

        List<DictInfo> dictInfoList = new ArrayList<>();
        dictInfoList.add(dictInfo1);
        dictInfoList.add(dictInfo2);
        dictInfoList.add(dictInfo3);
        dictInfoList.add(dictInfo4);

        Map<String, Map<Integer, String>> tmpMap = new HashMap<>();
        for (DictInfo dictInfo : dictInfoList) {
            String category = dictInfo.getCategory();
            Map<Integer, String> map = tmpMap.get(category);
            if (map == null) {
                map = new HashMap<>();
                tmpMap.put(category, map);
            }
            map.put(dictInfo.getTypeCode(), dictInfo.getTypeName());
        }
        Map<String, Map<Integer, String>> dictInfoMap = new HashMap<>(tmpMap);
        for (Map.Entry<String, Map<Integer, String>> entry : tmpMap.entrySet()) {
            String key = entry.getKey();
            String[] keyArr = key.split("_");
            if (keyArr.length < 2)
                continue;
            for (int i = 1; i < keyArr.length; i++) {
                String s = keyArr[i];
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
                keyArr[i] = s;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keyArr.length; i++) {
                sb.append(keyArr[i]);
            }
            dictInfoMap.put(sb.toString(), entry.getValue());
        }

        student.setName("cyx");
        student.setAge(29);
        student.setCountryType(1000);

        Score score1 = new Score();
        score1.setClassName("语文");
        score1.setScore(80);
        score1.setHardType(2001);

        Score score2 = new Score();
        score2.setClassName("数学");
        score2.setScore(100);
        score2.setHardType(2000);

        List<Score> list = new ArrayList<>();
        list.add(score1);
        list.add(score2);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("country_type", 1000);
        map1.put("hard_type", 2001);
        mapList.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("country_type", 1001);
        map2.put("hard_type", 2000);
        mapList.add(map2);

        student.setList(list);
        student.setMapList(mapList);

        return dictInfoMap;
    }
}

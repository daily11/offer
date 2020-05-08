package javacore.dict;

import javacore.beanutils.Score;
import javacore.beanutils.Student;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

public class ConvertUtils {
    public static void main(String[] args) throws Exception {
        Student student = new Student();
        //<种类，<类型码，类型名>>
        Map<String, Map<Integer, String>> dictInfoMap = initData(student);

        convert(student, dictInfoMap);

        System.out.println("hhh");
    }

    private static void convert(Object orig, Map<String, Map<Integer, String>> dictInfoMap) throws Exception {
        if (orig instanceof Map) {

        } else if (orig instanceof List) {

        } else {
            Class clazz = orig.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = descriptors[i];
                String name = propertyDescriptor.getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                // 查看属性name在字典表中是否存在
                Map<Integer, String> dictFieldMap = existFieldInDict(name, dictInfoMap);
                if(dictFieldMap!=null){
                    for (int j = 0; j < descriptors.length; j++) {
                        String noNameStr = descriptors[j].getName().replace("Name","");
                        if(name.equals(noNameStr) && name.length()<descriptors[j].getName().length()) {
                            // 获取orig中key对应的值value
                            Method readMethod = propertyDescriptor.getReadMethod();
                            Object key = readMethod.invoke(orig, null);
                            // (name+Name属性)注入值
                            Method writeMethod = descriptors[j].getWriteMethod();
                            String value = dictFieldMap.get(key);
                            writeMethod.invoke(orig,value);
                        }
                    }
                }


            }
        }
    }

    private static Map<Integer, String> existFieldInDict(String name, Map<String, Map<Integer, String>> dictInfoMap) {
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

        student.setList(list);

        return dictInfoMap;
    }
}

package javacore.beanutils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class BeanUtils {

    public static void main(String[] args) throws Exception {
//        testInjectMapBean();
        testInjectJavaBean();
    }

    private static void testInjectJavaBean() throws Exception {
        Student dest = new Student();
        Student orig = new Student();
        orig.setName("cyx");
        orig.setAge(28);
        copyProperties(dest, orig);
    }

    private static void testInjectMapBean() throws Exception {
//        String beanStr = "'{'name':'cyx','age':28}'";
        Student student = new Student();
        Map<String, Object> dbMap = new HashMap<>();
        dbMap.put("name", "cyx");
        dbMap.put("age", 28);

        Score score = new Score("数学", 100);
        List<Score> list = new ArrayList<>();
        list.add(score);
        dbMap.put("list", list);

        copyProperties(student, dbMap);

        System.out.println(student);
    }


    public static void copyProperties(Object dest, Object orig) throws Exception {
        if (orig instanceof Map) {
            injectMapBean(dest, orig);
        } else /* if (orig is a standard JavaBean) */ {
            injectJavaBean(dest, orig);
        }
        System.out.println(dest);
    }

    private static void injectJavaBean(Object dest, Object orig) throws Exception {
        PropertyDescriptor[] destPds = getPropertyDescriptors(dest.getClass());
        for (int i = 0; i < destPds.length; i++) {
            PropertyDescriptor destPd = destPds[i];
            Method writeMethod = destPd.getWriteMethod();
            if (writeMethod != null) {
                String destPdName = destPd.getName();
                if ("class".equals(destPdName)) {
                    continue; // No point in trying to set an object's class
                }
                // 获取orig中key对应的值value
                PropertyDescriptor sourcePd = getPropertyDescriptor(orig.getClass(), destPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(orig);
                    // 将value值注入dest中key对应的值中
                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                        writeMethod.setAccessible(true);
                    }
                    writeMethod.invoke(dest, value);
                }
            }
        }
    }

    private static PropertyDescriptor[] getPropertyDescriptors(Class clazz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        return beanInfo.getPropertyDescriptors();
    }

    private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) throws Exception {
        PropertyDescriptor[] origPds = getPropertyDescriptors(clazz);
        for (PropertyDescriptor origPd : origPds) {
            if (origPd.getName().equals(propertyName)) {
                return origPd;
            }
        }
        return null;
    }

    private static void injectMapBean(Object dest, Object orig) throws Exception {
        Class clazz = dest.getClass();
        Iterator entries = ((Map) orig).entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();

            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] destPds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor destPd : destPds) {
                String destPdName = destPd.getName();
                if (key.equals(destPdName)) {
                    Method writeMethod = destPd.getWriteMethod();
                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                        writeMethod.setAccessible(true);
                    }
                    writeMethod.invoke(dest, value);
                }
            }
        }
    }

}

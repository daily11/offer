package javacore.beanutils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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
    }

    private static void injectJavaBean(Object dest, Object orig) throws Exception {
        Class clazz = dest.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < descriptors.length; i++) {
            PropertyDescriptor propertyDescriptor = descriptors[i];
            String name = propertyDescriptor.getName();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            // 获取orig中key对应的值value
            Method readMethod = propertyDescriptor.getReadMethod();
            Object value = readMethod.invoke(orig, null);
            // 将value值注入dest中key对应的值中
            Method writeMethod = propertyDescriptor.getWriteMethod();
            writeMethod.invoke(dest, value);
        }
        System.out.println(dest);
    }

    private static void injectMapBean(Object dest, Object orig) throws Exception {
        Class clazz = dest.getClass();
        Iterator entries = ((Map) orig).entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();

            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : descriptors) {
                String name = propertyDescriptor.getName();
                if (key.equals(name)) {
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(dest, value);
                }
            }
        }
    }

}

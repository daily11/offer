package javacore.beanutils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeanUtils {

    public static void main(String[] args) throws Exception{
        Student student = new Student();

        Map<String, Object> dbMap = new HashMap<>();
        dbMap.put("name","cyx");
        dbMap.put("age",28);

        copyProperties(student,dbMap);
        System.out.println(student);
    }

    public static void copyProperties(Object dest, Object orig) throws Exception{
        if (orig instanceof Map) {
            injectMapBean(dest,orig);
        }
    }

    private static void injectMapBean(Object dest, Object orig) throws Exception{
        Class clazz = dest.getClass();
        Iterator entries = ((Map) orig).entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();

            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : descriptors) {
                String name = propertyDescriptor.getName();
                if (key.equals(name)) {
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(dest,value);
                }
            }
        }
    }

}

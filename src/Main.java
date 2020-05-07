import javacore.beanutils.Student;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Object target = new Student();
        Student source = new Student();
        source.setName("cyx");
        source.setAge(29);


    }
}

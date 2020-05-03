package annotatioin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationParser {

    public static void main(String[] args) throws Exception {
        Class clazz = Person.class;

        //类注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            PersonAnnotation personAnnotation = (PersonAnnotation) annotation;
            System.out.println(personAnnotation.name() + " , " + personAnnotation.age());
        }

        //成员变量注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean hasAnnotation = field.isAnnotationPresent(PersonAnnotation.class);
            if (hasAnnotation) {
                PersonAnnotation personAnnotation = (PersonAnnotation) field.getAnnotation(PersonAnnotation.class);
                System.out.println(personAnnotation.name() + " , " + personAnnotation.age());
            }
        }

        //方法注解
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(PersonAnnotation.class);
            if (hasAnnotation) {
                PersonAnnotation personAnnotation = (PersonAnnotation) method.getAnnotation(PersonAnnotation.class);
                System.out.println(personAnnotation.name() + " , " + personAnnotation.age());
            }
        }
    }

}

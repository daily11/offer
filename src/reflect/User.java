package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class User {
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Class clazz = User.class;
        Constructor constructor = clazz.getConstructor();
        User user = (User) constructor.newInstance();

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(user, "cyx");
        System.out.println("name=" + nameField.get(user));

        Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        setNameMethod.setAccessible(true);
        setNameMethod.invoke(user, "wxp");
        Method getNameMethod = clazz.getDeclaredMethod("getName", null);
        getNameMethod.setAccessible(true);
        String name = (String) getNameMethod.invoke(user);
        System.out.println("name=" + name);

    }
}

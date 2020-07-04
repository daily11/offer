package javacore.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        // 被代理的对象是userDao
        UserDao userDao = new UserDao();
        InvocationHandler handler = new UserDaoInvocationHandler(userDao);
        // 代理对象
        Dao proxy = (Dao) Proxy.newProxyInstance(
                Dao.class.getClassLoader(),
                new Class[]{Dao.class},
                handler
        );
        // 执行代理对象方法
        proxy.delete(1);
    }
}

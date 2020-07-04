package javacore.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserDaoInvocationHandler implements InvocationHandler {
    private Dao dao;

    public UserDaoInvocationHandler(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preInvoke();
        Object object = method.invoke(dao, args);
        afterInvoke();
        return object;
    }

    private void preInvoke() {
        System.out.println("preInvoke");
    }

    private void afterInvoke() {
        System.out.println("afterInvoke");
    }
}

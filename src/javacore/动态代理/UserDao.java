package javacore.动态代理;

public class UserDao implements Dao{

    @Override
    public void delete(int id) {
        System.out.println("function delete is executed!");
    }
}

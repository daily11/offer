package 设计模式.代理模式;

public class UserDao implements Dao{

    @Override
    public void delete(int id) {
        System.out.println("function delete is executed!");
    }
}

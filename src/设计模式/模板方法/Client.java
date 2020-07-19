package 设计模式.模板方法;

public class Client {
    public static void main(String[] args) {
        BaseActivity activity = new UserActivity();
        activity.init();
    }
}

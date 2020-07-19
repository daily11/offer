package 设计模式.模板方法;

public abstract class BaseActivity {

    /**
     * 模板方法
     */
    public void init() {
        this.printPath();
        this.initView();
        this.initListener();
    }

    /**
     * 公有方法
     */
    public void printPath() {
        System.out.println("打印类路径！");
    }

    /**
     * 子类实现的方法
     */
    public abstract void initView();

    /**
     * 子类实现的方法
     */
    public abstract void initListener();

}
package 设计模式.模板方法;

public class UserActivity extends BaseActivity {

	public void initView() {
		System.out.println("子类视图初始化");
	}

	public void initListener() {
		System.out.println("子类监听器初始化");
	}

}
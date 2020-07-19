package 设计模式.外观模式;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("矩形绘制完成！");
	}

}
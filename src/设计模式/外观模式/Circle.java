package 设计模式.外观模式;

public class Circle implements Shape {

    @Override
    public void draw() {
		System.out.println("原形绘制完成！");
    }

}
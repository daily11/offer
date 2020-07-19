package 设计模式.外观模式;

public class ShapeMaker {

    private Circle circle;
    private Rectangle rectangle;

    public void drawCircle() {
        this.circle.draw();
    }

    public void drawRectangle() {
        this.rectangle.draw();
    }

    public ShapeMaker() {
        this.circle = new Circle();
        this.rectangle = new Rectangle();
    }

}
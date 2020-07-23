package 设计模式.中介者模式;

public class Client {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Component product = new Product(mediator);
        Component map = new Map(mediator);
        Component view = new View(mediator);
        Component field = new Field(mediator);
        Component chart = new Chart(mediator);

        product.delete(1);
        map.delete(2);
        view.delete(3);
        field.delete(4);
        chart.delete(5);
    }
}

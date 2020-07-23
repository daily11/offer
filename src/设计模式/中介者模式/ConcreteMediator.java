package 设计模式.中介者模式;

public class ConcreteMediator extends Mediator {

    private Product product;
    private Map map;
    private View view;
    private Field field;
    private Chart chart;

    /**
     * @param c
     */
    @Override
    public void componentChanged(Component c, int i) {
        if (c instanceof Map) {
            System.out.println("Map级联删除" + i);
        } else if (c instanceof Product) {
            System.out.println("Product级联删除" + i);
        } else if (c instanceof View) {
            System.out.println("View级联删除" + i);
        } else if (c instanceof Field) {
            System.out.println("Field级联删除" + i);
        } else if (c instanceof Chart) {
            System.out.println("Chart级联删除" + i);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }
}
package annotatioin;

@PersonAnnotation(
        name = "zou",
        age = 19
)
public class Person {
    @PersonAnnotation(
            name = "wxp",
            age = 20
    )
    private String name;

    @PersonAnnotation(
            name = "jt",
            age = 21
    )
    public String getName() {
        return name;
    }
}

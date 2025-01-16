import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {
    private int age;
    private String name;

    public void SetAge(int age) {
        this.age = age;
    }
    public void PlusAge() {
        this.age += 1;
    }

}


import javax.ejb.Stateful;

@Stateful
//        (name = "StatefulAddBean", mappedName = "BeanFull")
@StatefulQ
public class StatefulAddBean implements AddBeanI{

    private int sum;

    @Override
    public int add(int a, int b) {
        sum += (a + b);
        System.out.println("Stateful sum = " + sum);
        return a + b;
    }

}

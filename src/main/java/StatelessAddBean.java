
import javax.ejb.Stateless;

@Stateless
//        (name = "StatelessAddBean", mappedName = "BeanLess")
@StatelessQ
public class StatelessAddBean implements AddStatefulBeanI{

    private int sum;

    @Override
    public int add(int a, int b) {
        sum += (a + b);
        System.out.println("Stateless sum = " + sum);
        return a + b;
    }

}

import javax.ejb.Local;

@Local
public interface AddBeanI {
    int add(int a, int b);
}

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Add", urlPatterns = "Add")
public class Add extends HttpServlet {

    @EJB
    @StatelessQ
    AddBeanI beanLess;

    @EJB
    @StatefulQ
    AddBeanI beanFull;

    @PostConstruct
    void count(){
        System.out.println("instance 1");
    }

//    @EJB
//    JMSSender sender;
//
//    @EJB
//    JMSReceiver receiver;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));

        int sumA = beanLess.add(a, b);
        int sumB = beanFull.add(a, b);

        PrintWriter out = resp.getWriter();
        out.println("Stateless sum = " + sumA);
        out.println("Stateful sum = "+ sumB);

//        sender.main();
//        receiver.main();
    }
}

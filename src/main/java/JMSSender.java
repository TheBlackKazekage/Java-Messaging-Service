import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@LocalBean
class JMSSender {

    //1. Inject the Queue connection factory
    @Resource(mappedName = "java:/ConnectionFactory")
    QueueConnectionFactory connectionFactory;

    //4. Create the queue
    @Resource(mappedName = "java:/jms/queue/DLQ")
    Queue queue;

    void main() {
        try {
            //2. Create the queue connection and start it
            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            //3. Create the session
            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            //5. Create the sender and the message
            QueueSender sender = session.createSender(queue);
            TextMessage msg = session.createTextMessage();

            Student student = new Student("Martin", "1234");
            ObjectMessage obj = session.createObjectMessage();
            msg.setText(String.valueOf(obj));
            sender.send(msg);

            //6. Attach text to the message and send the message
            String text = "Hello World";
            msg.setText(text);
            sender.send(msg);

            System.out.println("Message sent");

        } catch (JMSException e) {
            System.out.println(e.getMessage());
        }
    }
}

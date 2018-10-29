import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@LocalBean
class JMSReceiver {
    @Resource(mappedName = "java:/ConnectionFactory")
    QueueConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/DLQ")
    Queue queue;

    void main() {
        try {
            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            QueueReceiver receiver = session.createReceiver(queue);

            MyListener listener = new MyListener();
            receiver.setMessageListener(listener);

            System.out.println("Waiting for messages...");

//            while (true){
//                Thread.sleep(1000);
//            }

        } catch (Exception e){
            e.getMessage();
        }
    }
}

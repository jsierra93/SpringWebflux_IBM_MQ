package co.com.jsierra.webfluxibmmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class MqService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${ibm-mq.queue}")
    private String queueName;

    public Mono<String> sendMessage(String message) {
        jmsTemplate.convertAndSend(queueName, message);
        log.info("sendMessage:  {}", message);
        return Mono.just(message);
    }

    /*
    Pendiente de estabilizar
     */
    public Mono<String> sendMessageTopic(String message) {
        jmsTemplate.convertAndSend("DEV.BASE.TOPIC", message);
        log.info("sendMessageTopic:  {}", message);
        return Mono.just(message);
    }

    @JmsListener(destination = "${ibm-mq.queue}")
    public void readMessage(String message) {
        log.info("receivedMessage: {}", message);
    }

}

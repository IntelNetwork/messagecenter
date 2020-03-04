package org.smartwork.msg.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/****短信接收
 */
@Component
public class SmsProvider {


    /***接收kafka消息
     * @param consumerRecord
     */
    @KafkaListener(topics="sendSms")
    public void sendMsg(ConsumerRecord consumerRecord) {
        Optional<Object> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            Object obj  = kfkaMsg.get();
            System.err.println(obj);
        }
    }
}

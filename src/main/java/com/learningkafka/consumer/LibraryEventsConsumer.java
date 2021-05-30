package com.learningkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// If @Component is commented it means that LibraryEventsConsumerManualOffest is active.
@Component
public class LibraryEventsConsumer {

    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){

        // Kafka listener container is going to poll the records and it is going
        // to get multiple records at the same time, but it is going to pass the
        // records one by one to this onMessage method.
        System.out.println("ConsumerRecord : "+consumerRecord);
    }
}

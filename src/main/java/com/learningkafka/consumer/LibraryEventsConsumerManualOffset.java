package com.learningkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

// Here we are demonstrating how to manage consumer offsets manually from the
// application itself. The consumer poll() method returns one or more
// ConsumerRecords. The messageListener is called for each record. The message listener
// container takes appropriate action based on the AckMode set/defined.
// Eg: 1) BATCH: It is Default. The consumer offset is committed when all the records
// returned by the poll() have been processed.
// 2) RECORD : Commit the offset when the listener returns after processing the record.
// 3) MANUAL : THE message listener is responsible to acknowledge() the Acknowledgement.
// After that, the same semantics as BATCH are applied.
// 4) And Many more. Refer docs.

// Below demonstrates the MANUAL mode.

// If @Component is commented it means that LibraryEventsConsumer is active.
//@Component
public class LibraryEventsConsumerManualOffset implements AcknowledgingMessageListener<Integer,String> {

    @Override
    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
        // Kafka listener container is going to poll the records and it is going
        // to get multiple records at the same time, but it is going to pass the
        // records one by one to this onMessage method.
        System.out.println("ConsumerRecord : "+consumerRecord);
        //we are letting the listener know that we have successfully processed the message.
        //In this MANUAL mode we can extra add logic before sending out the acknowledgement.
        acknowledgment.acknowledge();
    }
}

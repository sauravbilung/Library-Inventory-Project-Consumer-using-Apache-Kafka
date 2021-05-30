package com.learningkafka.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;


// 1) Here are modifying the AckMode from default mode which is BATCH to MANUAL.
// For the below logic to work we need to have a listener of certain type.

// 2) @KafkaListener containers in LibraryEventsConsumer classes uses concurrent message listener containers.
// With concurrent message listener container, we can spin up multiple instances of the same kafka message
// listener container. This approach is recommended when the application is not running in a cloud like environment.

@Configuration
@EnableKafka
public class LibraryEventsConsumerConfig {

    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory) {
                ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
                configurer.configure(factory, kafkaConsumerFactory);
                factory.setConcurrency(3); // Point 2. Creating 3 threads with same instance of kakfa listener
                //factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL); // Point No.1
        return factory;
    }
}

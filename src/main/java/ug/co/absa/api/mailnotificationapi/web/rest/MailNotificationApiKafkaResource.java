package ug.co.absa.api.mailnotificationapi.web.rest;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import ug.co.absa.api.mailnotificationapi.broker.KafkaConsumer;

@RestController
@RequestMapping("/api/mail-notification-api-kafka")
public class MailNotificationApiKafkaResource {

    private static final String PRODUCER_BINDING_NAME = "binding-out-0";

    private final Logger log = LoggerFactory.getLogger(MailNotificationApiKafkaResource.class);
    private final KafkaConsumer kafkaConsumer;
    private final StreamBridge streamBridge;

    public MailNotificationApiKafkaResource(StreamBridge streamBridge, KafkaConsumer kafkaConsumer) {
        this.streamBridge = streamBridge;
        this.kafkaConsumer = kafkaConsumer;
    }

    @PostMapping("/publish")
    public void publish(@RequestParam String message) {
        log.debug("REST request the message : {} to send to Kafka topic ", message);
        streamBridge.send(PRODUCER_BINDING_NAME, message);
    }

    @GetMapping("/register")
    public ResponseBodyEmitter register(Principal principal) {
        return kafkaConsumer.register(principal.getName());
    }

    @GetMapping("/unregister")
    public void unregister(Principal principal) {
        kafkaConsumer.unregister(principal.getName());
    }
}

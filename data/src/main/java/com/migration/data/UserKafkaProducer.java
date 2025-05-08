package com.migration.data;
@Service
@RequiredArgsConstructor
public class UserKafkaProducer {
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void publishUserCreated(User user) {
        sendEvent("user-created", user);
    }

    public void publishUserUpdated(User user) {
        sendEvent("user-updated", user);
    }

    private void sendEvent(String topic, User user) {
        UserEvent event = new UserEvent(UUID.randomUUID().toString(), user, Instant.now());
        kafkaTemplate.send(topic, user.getId().toString(), event);
        log.info("Published event to {}: {}", topic, event);
    }
}

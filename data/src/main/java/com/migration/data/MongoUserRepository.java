package com.migration.data;
@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, UUID> {}

@Service
@RequiredArgsConstructor
@Slf4j
public class UserKafkaConsumer {
    private final MongoUserRepository repository;

    @KafkaListener(topics = {"user-created", "user-updated"}, groupId = "mongo-sync")
    public void handleUserEvent(@Payload UserEvent event) {
        log.info("Received event with transactionId={}", event.getTransactionId());
        MongoUser mongoUser = new MongoUser();
        BeanUtils.copyProperties(event.getUser(), mongoUser);
        repository.save(mongoUser);
        log.info("User synced to MongoDB: {}", mongoUser);
    }
}
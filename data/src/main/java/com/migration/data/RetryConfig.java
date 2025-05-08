package com.migration.data;
@Configuration
@EnableRetry
public class RetryConfig {}

// Sample Retryable Method
@Service
@RequiredArgsConstructor
@Slf4j
public class ReliableMongoService {
    private final MongoUserRepository repository;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void saveUser(MongoUser user) {
        repository.save(user);
        log.info("User saved with retry logic: {}", user);
    }

    @Recover
    public void recover(Exception e, MongoUser user) {
        log.error("Failed to save user after retries: {}", user);
        // Optional: send to dead letter or alert
    }
}
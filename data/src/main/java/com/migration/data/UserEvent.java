package com.migration.data;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String transactionId;
    private User user;
    private Instant timestamp;
}
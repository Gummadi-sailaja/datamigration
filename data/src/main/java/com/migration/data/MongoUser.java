package com.migration.data;
@Document(collection = "users")
@Data
public class MongoUser {
    @Id
    private UUID id;
    private String name;
    private String email;
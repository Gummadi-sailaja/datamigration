package com.migration.data;
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private UUID id;
    private String name;
    private String email;
    // other fields...
}
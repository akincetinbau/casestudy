package com.example.assesment.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@RequiredArgsConstructor
public class User {

    @Id
    private String id;

    private String name;
    private String email;
}


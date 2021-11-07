package com.poc.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private String roll;
    private String name;
    private String school;
}

package com.cts.todo.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "task")
public class Task {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String taskName;
    private String description;
    private LocalDateTime taskStartDate;
    private LocalDateTime taskEndDate;
    private String taskStatus;
    private Integer totalEffortRequired;

}

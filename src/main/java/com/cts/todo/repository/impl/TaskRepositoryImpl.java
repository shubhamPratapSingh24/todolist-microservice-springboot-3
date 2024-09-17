package com.cts.todo.repository.impl;

import com.cts.todo.entity.Task;
import com.cts.todo.repository.TaskRepositoryCustom;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepositoryCustom {

    private static final String COLLECTION = "task";
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Task> getTaskByTaskName(String taskName) {
            Query query = new Query().addCriteria(Criteria.where("taskName").is(taskName));
            List<Task> search = mongoTemplate.find(query, Task.class, COLLECTION);
            return search;
        }

        @Override
       public Boolean updateTaskStatus(String taskName , String taskStatus) {
            Query query = new Query().addCriteria(Criteria.where("taskName").is(taskName));
            Update update = new Update().set("taskStatus", taskStatus);
            UpdateResult result = mongoTemplate.updateMulti(query, update, Task.class, COLLECTION);
            return result.wasAcknowledged();
        }

    @Override
    public Boolean updateTaskStatus( String taskStatus) {
        Update update = new Update().set("taskStatus", taskStatus);
        UpdateResult result = mongoTemplate.updateMulti(new Query(), update, Task.class, COLLECTION);
        return result.wasAcknowledged();
    }
}

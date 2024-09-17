package com.cts.todo.repository;

import com.cts.todo.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> , TaskRepositoryCustom {


}

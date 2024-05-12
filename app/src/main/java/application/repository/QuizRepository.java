package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    
}

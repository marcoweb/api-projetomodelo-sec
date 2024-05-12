package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Questao;

public interface QuestaoRepository extends CrudRepository<Questao, Long> {
    
}

package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Opcao;

public interface OpcaoRepository extends CrudRepository<Opcao, Long> {
   
}
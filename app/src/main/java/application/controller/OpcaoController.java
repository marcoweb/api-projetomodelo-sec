package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Opcao;
import application.repository.OpcaoRepository;
import application.repository.QuestaoRepository;

@RestController
@RequestMapping("/opcoes")
public class OpcaoController {
    @Autowired
    private OpcaoRepository opcoesRepo;

    @Autowired
    private QuestaoRepository questoesRepo;

    @GetMapping
    public Iterable<Opcao> getAll() {
        return opcoesRepo.findAll();
    }

    @GetMapping("/{id}")
    public Opcao getOne(@PathVariable long id) {
        Optional<Opcao> result = opcoesRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }
        return result.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(opcoesRepo.existsById(id)) {
            opcoesRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }
    }

    @PostMapping
    public Opcao post(@RequestBody Opcao opcao) {
        if(!questoesRepo.existsById(opcao.getQuestao().getId())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Questão vinculada não encontrada"
            );
        }
        return opcoesRepo.save(opcao);
    }

    @PutMapping("/{id}")
    public Opcao put(@RequestBody Opcao opcao, @PathVariable long id) {
        Optional<Opcao> result = opcoesRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Opção Não Encontrada"
            );
        }
        if(!questoesRepo.existsById(opcao.getQuestao().getId())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Questão vinculada não encontrada"
            );
        }
        result.get().setDescricao(opcao.getDescricao());
        result.get().setCorreto(opcao.isCorreto());
        result.get().setQuestao(opcao.getQuestao());

        return opcoesRepo.save(result.get());
    }
}

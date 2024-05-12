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

import application.model.Questao;
import application.model.Quiz;
import application.repository.QuestaoRepository;
import application.repository.QuizRepository;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizRepository quizRepo;
    @Autowired
    private QuestaoRepository questaoRepo;

    @GetMapping
    public Iterable<Quiz> getAll() {
        return quizRepo.findAll();
    }

    @PostMapping
    public Quiz post(@RequestBody Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @PutMapping("/{id}")
    public Quiz put(@RequestBody Quiz quiz, @PathVariable long id) {
        Optional<Quiz> result = quizRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Quiz Não Encontrado"
            );
        }
        for(Questao q : quiz.getQuestoes()) {
            if(!questaoRepo.existsById(q.getId())) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Questão vinculada não encontrada"
                );
            }
        }
        return quizRepo.save(quiz);
    }

    @DeleteMapping
    public void delete(@PathVariable long id) {
        if(quizRepo.existsById(id)) {
            quizRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Quiz Não Encontrado"
            );
        }
    }
}

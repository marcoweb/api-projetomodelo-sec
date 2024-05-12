package application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "opcoes")
public class Opcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String descricao;
    private boolean correto;
    @ManyToOne
    @JoinColumn(name = "id_questao", nullable = false)
    private Questao questao;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isCorreto() {
        return correto;
    }
    public void setCorreto(boolean correto) {
        this.correto = correto;
    }
    public Questao getQuestao() {
        return questao;
    }
    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}

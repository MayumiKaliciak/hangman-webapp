package de.fellowork.hangman.game.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String playerName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    private List<HangmanLetter> currentWord = new ArrayList<>();

    @ElementCollection
    private List<String> wronglyGuessedLetters = new ArrayList<>();

    public void addErrorLetter(String toLowerCaseGuessLetter) {
        if(!wronglyGuessedLetters.contains(toLowerCaseGuessLetter)){
            wronglyGuessedLetters.add(toLowerCaseGuessLetter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GameState gameState = (GameState) o;
        return id != null && Objects.equals(id, gameState.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package de.fellowork.mayumi.practice.hangmanwebapp.game.persistence;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class HangmanLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String letter;

    private boolean correctlyGuessed = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HangmanLetter hangmanLetter = (HangmanLetter) o;
        return id != null && Objects.equals(id, hangmanLetter.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

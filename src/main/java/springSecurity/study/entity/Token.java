package springSecurity.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Token {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String identifier;
    private String token;

    public Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }
}

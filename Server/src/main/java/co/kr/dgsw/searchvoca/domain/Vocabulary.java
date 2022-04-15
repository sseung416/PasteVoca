package co.kr.dgsw.searchvoca.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vocabulary {
    private int id;

    private String name;

    private String explanation;

    private String language;
}

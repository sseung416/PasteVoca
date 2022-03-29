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
    public int id;

    public String name;

    public String explanation;

    public String language;
}

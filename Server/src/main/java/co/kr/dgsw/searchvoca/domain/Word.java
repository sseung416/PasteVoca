package co.kr.dgsw.searchvoca.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Word {
    private static int TYPE_DIFFICULT = 1;
    private static int TYPE_MIDDLE = 2;
    private static int TYPE_EASY = 3;

    private int id;

    private int vocabularyId;

    private String word;

    private String meaning;

    private int type;
}

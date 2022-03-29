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

    public int id;

    public int vocabularyId;

    public String word;

    public String meaning;

    public int type = TYPE_EASY;
}

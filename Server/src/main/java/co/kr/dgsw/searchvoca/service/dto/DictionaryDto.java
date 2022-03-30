package co.kr.dgsw.searchvoca.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryDto {
    public String word;
    public String pos;
    public Sense sense;
}
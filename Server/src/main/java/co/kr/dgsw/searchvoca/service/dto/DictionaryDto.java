package co.kr.dgsw.searchvoca.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DictionaryDto {
    public int sup_no;
    public String target_code;
    public Sense sense;
    public String pos;
}
package co.kr.dgsw.searchvoca.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class TranslateDto {
    public String translatedText;

    @Override
    public String toString() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("translatedText", this.translatedText);
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

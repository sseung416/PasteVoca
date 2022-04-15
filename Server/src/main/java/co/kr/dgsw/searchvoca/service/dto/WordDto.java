package co.kr.dgsw.searchvoca.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class WordDto {
    private String word;
    private String definition;

    @Override
    public String toString() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("word", this.word);
            map.put("definition", this.definition);
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("word", this.word);
        map.put("definition", this.definition);
        return new JSONObject(map);
    }
}

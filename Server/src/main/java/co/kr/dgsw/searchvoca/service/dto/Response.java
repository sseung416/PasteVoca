package co.kr.dgsw.searchvoca.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class Response<T> {
    private int status;
    private String message;
    private T res;

    @Override
    public String toString() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("status", status);
            map.put("message", message);
            map.put("res", res);

            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

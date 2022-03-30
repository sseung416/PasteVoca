package co.kr.dgsw.searchvoca.service;

import co.kr.dgsw.searchvoca.Key;
import co.kr.dgsw.searchvoca.service.dto.DictionaryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DictionaryServiceImpl {
    private final RestTemplate restTemplate;

    public DictionaryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String getSearchResult(String word) {
        List<DictionaryDto> result = null;

        try {
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(Key.DICTIONARY_BASE_URL)
                    .queryParam("key", Key.DICTIONARY_AUTH)
                    .queryParam("q", word)
                    .queryParam("req_type", "json")
                    .build();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
            System.out.println("DictionaryService-getSearchResult: " + responseEntity.getStatusCode());

            result = this.parse(responseEntity.getBody());
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return toJson(result);
    }

    private List<DictionaryDto> parse(String body) throws ParseException, JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
        JSONObject channelBody = (JSONObject) jsonObject.get("channel");

        return convertDictionaryDto(channelBody.get("item"));
    }

    private List<DictionaryDto> convertDictionaryDto(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonStr = mapper.writeValueAsString(data);

        return mapper.readValue(jsonStr, new TypeReference<>() {});
    }

    private String toJson(Object dto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

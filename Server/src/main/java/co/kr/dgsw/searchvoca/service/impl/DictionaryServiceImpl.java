package co.kr.dgsw.searchvoca.service.impl;

import co.kr.dgsw.searchvoca.Key;
import co.kr.dgsw.searchvoca.service.DictionaryService;
import co.kr.dgsw.searchvoca.service.dto.Response;
import co.kr.dgsw.searchvoca.service.dto.WordDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final RestTemplate restTemplate;

    public DictionaryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getSearchResult(String word) {
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

            return new Response(200, "대충 잘 왔다는 뜻", getJsonArray(responseEntity.getBody())).toString();
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getJsonArray(String body) throws ParseException, JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
        JSONObject channelBody = (JSONObject) jsonObject.get("channel");

        JSONArray itemArray = (JSONArray) channelBody.get("item");
        JSONArray result = new JSONArray();
        for (Object item : itemArray) {
            result.add(convertWordDto((JSONObject) item));
        }

        return result;
    }

    private JSONObject convertWordDto(JSONObject data) {
        String word = data.get("word").toString();
        String definition = ((JSONObject) data.get("sense")).get("definition").toString();
        return new WordDto(word, definition).toJson();
    }
}

package co.kr.dgsw.searchvoca.service.impl;

import co.kr.dgsw.searchvoca.Key;
import co.kr.dgsw.searchvoca.service.PapagoService;
import co.kr.dgsw.searchvoca.service.dto.Response;
import co.kr.dgsw.searchvoca.service.dto.WordDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PapagoServiceImpl implements PapagoService {
    private final RestTemplate restTemplate;

    public PapagoServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String postLanguage(@RequestBody String query) {
        try {
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(Key.NAVER_BASE_URL + "/detectLangs")
                    .queryParam("query", query)
                    .build();

            HttpHeaders headers = this.getPapagoHeaders();
            HttpEntity<?> entity = new HttpEntity(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String.class);

            System.out.println("PapgoService-getLanguage: " + responseEntity.getStatusCode());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonBody = (JSONObject) jsonParser.parse(responseEntity.getBody());
            return jsonBody.get("langCode").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String postTranslate(String source, String target, String text) {
        try {
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(Key.NAVER_BASE_URL + "/n2mt")
                    .queryParam("source", source)
                    .queryParam("target", target)
                    .queryParam("text", text)
                    .build();

            HttpHeaders headers = this.getPapagoHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String.class);

            System.out.println("PapgoService-postTranslate: " + responseEntity.getStatusCode());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonBody = (JSONObject) jsonParser.parse(responseEntity.getBody());
            JSONObject resultBody = (JSONObject) ((JSONObject) jsonBody.get("message")).get("result");

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(new WordDto(text, resultBody.get("translatedText").toString()).toJson());

            return new Response(200, "응애 나는 애기 개발자", jsonArray).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private HttpHeaders getPapagoHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.set("X-Naver-Client-Id", Key.NAVER_CLIENT_ID);
        headers.set("X-Naver-Client-Secret", Key.NAVER_CLIENT_SECRET);

        return headers;
    }
}

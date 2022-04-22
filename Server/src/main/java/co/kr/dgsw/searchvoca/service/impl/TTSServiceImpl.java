package co.kr.dgsw.searchvoca.service.impl;

import co.kr.dgsw.searchvoca.Key;
import co.kr.dgsw.searchvoca.service.TTSService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TTSServiceImpl implements TTSService {
    private final RestTemplate restTemplate;

    public TTSServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void getTTS(String word) {
        try {
//            UriComponents uri = UriComponentsBuilder.fromHttpUrl(Key.GOOGLE_BASE_URL)
//                    .build()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

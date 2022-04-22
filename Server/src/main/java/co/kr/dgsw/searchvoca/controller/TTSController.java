package co.kr.dgsw.searchvoca.controller;

import co.kr.dgsw.searchvoca.service.PapagoService;
import co.kr.dgsw.searchvoca.service.TTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTSController {
    @Autowired private PapagoService papagoService;
    @Autowired private TTSService ttsService;

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create() {
//
//    }
}

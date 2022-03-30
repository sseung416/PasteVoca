package co.kr.dgsw.searchvoca.service;

public interface PapagoService {
    public String postLanguage(String query);

    public String postTranslate(String source, String target, String text);
}

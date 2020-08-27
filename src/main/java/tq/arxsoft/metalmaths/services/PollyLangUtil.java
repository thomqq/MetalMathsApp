package tq.arxsoft.metalmaths.services;

import com.amazonaws.services.polly.model.VoiceId;

import java.util.HashMap;
import java.util.Map;

public class PollyLangUtil implements PollyLang {
    private Map<String, VoiceId> voices = new HashMap<>();
    public PollyLangUtil() {
        voices.put("EN", VoiceId.Joanna);
        voices.put("DE", VoiceId.Marlene);
        voices.put("PL", VoiceId.Ewa);
        voices.put("FR", VoiceId.Celine);
        voices.put("SP", VoiceId.Conchita);
    }

    public VoiceId getVoiceId(String lang) {
        return voices.get(lang);
    }
}

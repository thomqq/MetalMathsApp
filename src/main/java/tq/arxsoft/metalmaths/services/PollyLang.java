package tq.arxsoft.metalmaths.services;

import com.amazonaws.services.polly.model.VoiceId;

public interface PollyLang {
    public VoiceId getVoiceId(String lang);
}

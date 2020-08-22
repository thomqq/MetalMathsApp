package tq.arxsoft.metalmaths.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.*;
import javazoom.jl.decoder.JavaLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import sun.audio.AudioPlayer;

import java.io.IOException;
import java.io.InputStream;

//@PropertySource("classpath:application.properties")
@Service
public class PollyService {

    private AmazonPolly amazonPolly;

    @Autowired
    public PollyService(Region region, @Value("${aws.key.access}") String accesKey, @Value("${aws.key.secret}") String secretKey) {
        //
        // Use your access key id and access secret key
        // Obtain it from AWS console
        //
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accesKey,
                secretKey);
        //
        // Create an Amazon Polly client in a specific region
        //
        this.amazonPolly = AmazonPollyClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region.getName()).build();
    }


    public InputStream synthesize(String text, OutputFormat format) throws IOException {

        //
        // Create speech synthesis request comprising of information such as following:
        // Text
        // Voice
        // The detail will be used to create the speech
        //
        SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(VoiceId.Joanna)
                .withOutputFormat(format);
        //
        // Create the speech
        //
        SynthesizeSpeechResult synthRes = this.amazonPolly.synthesizeSpeech(synthReq);
        //
        // Returns the audio stream
        //
        return synthRes.getAudioStream();
    }

}

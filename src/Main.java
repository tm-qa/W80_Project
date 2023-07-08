import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) {
        String text = "What, is your name Peeyush ?";
        convertTextToSpeech(text, "output.wav");
    }

    public static void convertTextToSpeech(String text, String outputFile) {
        // Set path to FreeTTS voices
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        // Create voice manager
        VoiceManager voiceManager = VoiceManager.getInstance();

        // Choose voice
        Voice voice = voiceManager.getVoice("kevin16");
        if (voice == null) {
            System.out.println("Cannot find specified voice.");
            return;
        }

        // Allocate synthesizer
        voice.allocate();

        try {
            // Create audio stream
            OutputStream audioStream = new FileOutputStream(outputFile);

            // Synthesize text to speech
            voice.speak(text);

            // Deallocate synthesizer
            voice.deallocate();

            System.out.println("Text converted to speech. Output file: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error converting text to speech: " + e.getMessage());
        }
    }
}

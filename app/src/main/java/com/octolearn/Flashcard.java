package com.octolearn;

public class Flashcard {
    private String word;
    private String translation;
    private String sample;
    private String transcription;
    private String catalogName;

    public Flashcard(String word, String translation, String sample, String transcription, String catalogName){
        this.word = word;
        this.translation = translation;
        this.sample = sample;
        this.transcription = transcription;
        this.catalogName = catalogName;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getSample() {
        return sample;
    }

    public String getTranscription() {
        return transcription;
    }

    public String getCatalogName() {
        return catalogName;
    }
}

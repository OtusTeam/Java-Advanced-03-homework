package ru.otus.app;


public class PredictionDto {
    private Integer prediction;

    public PredictionDto(Integer prediction) {
        this.prediction = prediction;
    }

    public PredictionDto() {
        this.prediction = null;
    }

    public Integer getPrediction() {
        return this.prediction;
    }

    public void setPrediction(Integer prediction) {
        this.prediction = prediction;
    }
}

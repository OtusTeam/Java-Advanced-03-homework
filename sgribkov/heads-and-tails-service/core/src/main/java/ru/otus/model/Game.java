package ru.otus.model;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Game {
    private String datetime;
    private int prediction;
    private int fact;
    private boolean succeed;

    public Game(int prediction) {
        this.prediction = prediction;
    }

    public void setFact(int fact) {
        this.fact = fact;
        this.datetime = Instant.now().toString();
        this.succeed = this.prediction == fact;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Prediction: ")
                .append(prediction)
                .append(", ")
                .append("Fact: ")
                .append(fact)
                .append(". ")
                .append("Result: ")
                .append(succeed ? "success. " : "fail. ")
                .append(datetime)
                .toString();
    }
}

package org.romequiz;

public class AnswerResponse {
    private final boolean correct;
    private final String message;

    public boolean isCorrect() {
        return correct;
    }

    public String getMessage() {
        return message;
    }

    public AnswerResponse(boolean correct, String message) {
        this.correct = correct;
        this.message = message;
    }
}

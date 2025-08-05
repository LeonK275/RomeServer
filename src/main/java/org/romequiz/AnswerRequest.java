package org.romequiz;

public class AnswerRequest {
    private int questionID;
    private int answer;

    public AnswerRequest(int questionID, int answer) {
        this.questionID = questionID;
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getAnswer() {
        return answer;
    }
}

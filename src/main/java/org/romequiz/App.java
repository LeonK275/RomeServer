package org.romequiz;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        QuizQuestion question1 = new QuizQuestion(1, "Wer war der erste Kaiser Roms?", List.of("Julius Cäsar", "Augustus", "Nero"), 1);
        QuizQuestion question2 = new QuizQuestion(2, "Wann wurde Rom gegründet?", List.of("753 v. Chr.", "1 n. Chr", "509 v. Chr"), 0);



        Gson gson = new Gson();
        String jsonF1 = gson.toJson(question1);
        String jsonF2 = gson.toJson(question2);
        List<QuizQuestion> allQuestions = new ArrayList<>();
        allQuestions.add( question1);
        allQuestions.add(question2);

        for(QuizQuestion question : allQuestions){
            if(question.getQuestionID() == 1){

            }
        }


        get("/", (req, res) -> "Salve Roma!");

        get("/quiz", (req, res) -> jsonF1);
        get("/quiz/all", ((request, response) -> gson.toJson(allQuestions)));
        post("/answer",((request, response) -> {
            return null;
        }));


    }
}
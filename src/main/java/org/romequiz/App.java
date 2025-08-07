package org.romequiz;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileReader;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;




import static spark.Spark.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type questionListType = new TypeToken<List<QuizQuestion>>() {}.getType();
        List<QuizQuestion> allQuestions = gson.fromJson(new FileReader("src/main/resources/questions.json"), questionListType);


        /*QuizQuestion question1 = new QuizQuestion(1, "Wer war der erste Kaiser Roms?", List.of("Julius Cäsar", "Augustus", "Nero"), 1);
        QuizQuestion question2 = new QuizQuestion(2, "Wann wurde Rom gegründet?", List.of("753 v. Chr.", "1 n. Chr", "509 v. Chr"), 0);
        QuizQuestion question3 = new QuizQuestion(
                3,
                "Wie hieß das höchste politische Amt in der römischen Republik?",
                List.of("Konsul", "Tribun", "Senator"),
                0
        );

        QuizQuestion question4 = new QuizQuestion(
                4,
                "Welches Bauwerk verband Rom mit dem Süden Italiens?",
                List.of("Via Appia", "Pont du Gard", "Hadrianswall"),
                0
        );

        QuizQuestion question5 = new QuizQuestion(
                5,
                "Welcher römische Gott entspricht dem griechischen Zeus?",
                List.of("Mars", "Jupiter", "Apollo"),
                1
        );

        QuizQuestion question6 = new QuizQuestion(
                6,
                "Was war die Amtssprache des Römischen Reichs?",
                List.of("Altgriechisch", "Latein", "Hebräisch"),
                1
        );

        QuizQuestion question7 = new QuizQuestion(
                7,
                "Wie starb Julius Cäsar?",
                List.of("Er ertrank", "Er wurde vergiftet", "Er wurde erstochen"),
                2
        );




        String jsonF1 = gson.toJson(question1);
        String jsonF2 = gson.toJson(question2);
        List<QuizQuestion> allQuestions = new ArrayList<>();
        allQuestions.add( question1);
        allQuestions.add(question2);
        allQuestions.add(question3);
        allQuestions.add(question4);
        allQuestions.add(question5);
        allQuestions.add(question6);
        allQuestions.add(question7);*/





        get("/", (req, res) -> "Salve Roma!");

        //get("/quiz", (req, res) -> jsonF1);


        get("/quiz/all", ((request, response) -> gson.toJson(allQuestions)));

        get("/quiz/random", ((request, response) -> {

            Random rand = new Random();
            QuizQuestion randomQuestion = allQuestions.get(rand.nextInt(allQuestions.size()));
            return gson.toJson(randomQuestion);

        }));


        post("/quiz/answer",((request, response) -> {

            AnswerRequest incoming = gson.fromJson(request.body(), AnswerRequest.class);
            for(QuizQuestion question : allQuestions){
                if (incoming.getQuestionID() == question.getQuestionID()){
                    if(incoming.getAnswer() == question.getCorrect()){
                        AnswerResponse answer = new AnswerResponse(true, "Richtig!");
                        response.status(200);
                        return gson.toJson(answer);
                    }
                    else {
                        AnswerResponse answer = new AnswerResponse(false, "Leider Falsch!");
                        response.status(200);
                        return gson.toJson(answer);
                    }
                }
            }

            response.status(404);
            AnswerResponse answer = new AnswerResponse(false, "Frage nicht gefunden!");
            return gson.toJson(answer);
        }));

        // POST NEW QUESTION
        post("/quiz/newquestion", ((request, response) -> {

            Type questionListType2 = new TypeToken<QuizQuestion>() {}.getType();
            QuizQuestion question = gson.fromJson(request.body(), questionListType2);
            allQuestions.add(question);

            try {
                FileWriter writer = new FileWriter("src/main/resources/questions.json");
                gson.toJson(allQuestions, writer);
                writer.close();
            }catch (IOException e) {e.printStackTrace();}

            return gson.toJson(new AnswerResponse(true, "Frage erfolgreih gespeichert"));
        }));


    }
}
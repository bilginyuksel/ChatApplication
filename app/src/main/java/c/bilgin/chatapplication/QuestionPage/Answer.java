package c.bilgin.chatapplication.QuestionPage;


import java.util.UUID;

import c.bilgin.chatapplication.HomePage;
import c.bilgin.chatapplication.UserOP.User;

public class Answer {

    private String answer,uid;
    private int rate;
    private User answerer;
    private String question_uid,question;

    public Answer(String answer,String question_uid,String question){
        this.uid = UUID.randomUUID().toString();
        this.answer = answer;
        this.question = question;
        this.rate = 0;
        this.answerer = HomePage.currentUser;
        this.question_uid = question_uid;
    }public Answer(){}

    public String getUid() {
        return uid;
    }

    public int getRate() {
        return rate;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion_uid() {
        return question_uid;
    }

    public String getQuestion() {
        return question;
    }

    public User getAnswerer() {
        return answerer;
    }

    public void rateAnswer(){
        this.rate = rate+1;
    }
}

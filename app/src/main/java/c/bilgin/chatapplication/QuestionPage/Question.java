package c.bilgin.chatapplication.QuestionPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;

public class Question {

    private String uid,question;
    private ArrayList<String> interest;
    private HashMap<String,Answer> answers;
    private User asker;
    private int rate;
    public Question(String question,ArrayList<String> interest,User asker){
        this.uid = UUID.randomUUID().toString();
        this.question = question;
        this.interest = interest;
        this.answers = new HashMap<>();
        this.asker = asker;
        this.rate = 0;

    }public Question(){}

    public HashMap<String, Answer> getAnswers() {
        return answers;
    }

    public User getAsker() {
        return asker;
    }

    public String getUid() {
        return uid;
    }

    public void setAnswers(HashMap<String, Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public int getRate() {
        return rate;
    }
    public void giveAVote(){
        this.rate = rate +1;
    }
    public void addAns(Answer a){
        if(answers ==null) answers =new HashMap<>();
        this.answers.put(a.getUid(),a);
    }
    public void editAns(Answer a){
        answers.remove(a.getUid());
        answers.put(a.getUid(),a);
    }

}

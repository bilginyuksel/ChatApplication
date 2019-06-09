package c.bilgin.chatapplication.QuestionPage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;

public class Question {

    private String uid,question;
    private ArrayList<String> interest;
    private List<Answer> answers ;
    private User asker;
    private int rate;
    public Question(String question,ArrayList<String> interest,User asker){
        this.uid = UUID.randomUUID().toString();
        this.question = question;
        this.answers = new ArrayList<>();
        this.interest = interest;
        this.asker = asker;
        this.rate = 0;

    }public Question(){}

    public User getAsker() {
        return asker;
    }

    public String getUid() {
        return uid;
    }

    public List<Answer> getAnswers() {
        return answers;
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
    public void addAnswer(Answer a){
        if(answers==null) answers= new ArrayList<>();
        this.answers.add(a);
    }
}

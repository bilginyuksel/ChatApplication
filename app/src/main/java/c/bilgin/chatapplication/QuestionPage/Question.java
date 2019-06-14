package c.bilgin.chatapplication.QuestionPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import c.bilgin.chatapplication.UserOP.User;

public class Question {

    private String uid,question;
    private ArrayList<String> interest;
    private HashMap<String,Answer> ans;
    private User asker;
    private int rate;
    public Question(String question,ArrayList<String> interest,User asker){
        this.uid = UUID.randomUUID().toString();
        this.question = question;
        this.interest = interest;
        this.ans = new HashMap<>();
        this.asker = asker;
        this.rate = 0;

    }public Question(){}

    public HashMap<String, Answer> getAns() {
        return ans;
    }

    public User getAsker() {
        return asker;
    }

    public String getUid() {
        return uid;
    }

    public void setAns(HashMap<String, Answer> ans) {
        this.ans = ans;
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
        if(ans==null)ans=new HashMap<>();
        this.ans.put(a.getUid(),a);
    }
    public void editAns(Answer a){
        ans.remove(a.getUid());
        ans.put(a.getUid(),a);
    }

}

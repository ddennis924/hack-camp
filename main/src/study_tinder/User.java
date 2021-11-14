package study_tinder;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class User {
    private final String name;
    private final List<Question> questionList;

    public User(String name){
        this.name = name;
        this.questionList = new ArrayList<>();
    }

    public User(String name, List<Question> seed){
        this.name = name;
        this.questionList = seed;
    }

    public User(List<Question> seed){
        this.name = "guest";
        this.questionList = seed;
    }

    public void addQuestion(Image i, Category c){
        new Question(this, i, c);
    }

    public List<Question> getQList(){
        return this.questionList;
    }

    public String getName(){
        return this.name;
    }

}

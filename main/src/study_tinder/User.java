package study_tinder;

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

    public void addQuestion(Question newQ){
        questionList.add(newQ);
    }

    public List<Question> getQList(){
        return new ArrayList<>(this.questionList);
    }

    public String getName(){
        return this.name;
    }
}

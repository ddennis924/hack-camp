package study_tinder;

import java.util.ArrayList;

public class User {
    private String name;
    private List<Question> questionList;

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
}

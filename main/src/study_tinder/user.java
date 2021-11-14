package study_tinder;

import java.util.ArrayList;
import java.util.List;

public class user {
    private String name;
    private List<Question> questionList;

    public user(String name){
        this.name = name;
        this.questionList = new ArrayList<>();
    }

    public user(String name, List<Question> seed){
        this.name = name;
        this.questionList = seed;
    }

    public user(List<Question> seed){
        this.name = "guest";
        this.questionList = seed;
    }
}

package study_tinder;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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

    public void addQuestion(String i, String c){
        new Question(this, i, c);
    }

    public List<Question> getQList(){
        return this.questionList;
    }

    public String getName(){
        return this.name;
    }

    public Set<String> commonCategories(User other){
        Set<String> out = new HashSet<>();

        Map<String, Integer> thisOccurrence = getOccurrenceMap(this.questionList);
        Map<String, Integer> otherOccurrence = getOccurrenceMap(other.questionList);

        for(String str : thisOccurrence.keySet()){
            if(otherOccurrence.containsKey(c)){
                out.add(str);
            }
        }

        return out;
    }

    private static Map<String, Integer> getOccurrenceMap(List<Question> qList){
        Map<String, Integer> output = new HashMap<>();
        qList.forEach(question -> {
            String thisCat = question.getCategory();
            if(output.containsKey(thisCat)){
                output.replace(thisCat, output.get(thisCat)+1);
            }
            else{
                output.put(thisCat, 1);
            }
        });
        return output;
    }
}

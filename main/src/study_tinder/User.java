package study_tinder;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.*;
import java.util.List;

public class User implements Writable {
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

    public Set<Category> commonCategories(User other){
        Set<Category> out = new HashSet<>();

        Map<Category, Integer> thisOccurrence = getOccurrenceMap(this.questionList);
        Map<Category, Integer> otherOccurrence = getOccurrenceMap(other.questionList);

        for(Category c : thisOccurrence.keySet()){
            if(otherOccurrence.containsKey(c)){
                out.add(c);
            }
        }

        return out;
    }

    private static Map<Category, Integer> getOccurrenceMap(List<Question> qList){
        Map<Category, Integer> output = new HashMap<>();
        qList.forEach(question -> {
            Category thisCat = question.getCategory();
            if(output.containsKey(thisCat)){
                output.replace(thisCat, output.get(thisCat)+1);
            }
            else{
                output.put(thisCat, 1);
            }
        });
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // EFFECTS: returns restaurants in RestaurantList as JSONArray of restaurants
    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Question q : questionList) {
            jsonArray.put(q.toJson());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", name);
        json.put("restaurants", questionsToJson());
        return json;
    }
}

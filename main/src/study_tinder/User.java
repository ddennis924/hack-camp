package study_tinder;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

public class User implements Writable {
    private final String name;
    private final List<Question> questionList;
    private final Set<Question> myQuestions;
    private final List<User> friendList;

    public User(String name){
        this.name = name;
        this.questionList = new ArrayList<>();
        this.myQuestions = new HashSet<>();
        this.friendList = new ArrayList<>();
    }

    public User(String name, List<Question> seed){
        this.name = name;
        this.questionList = seed;
        this.myQuestions = new HashSet<>();
        this.friendList = new ArrayList<>();
    }

    public User(List<Question> seed){
        this.name = "guest";
        this.questionList = seed;
        this.myQuestions = new HashSet<>();
        this.friendList = new ArrayList<>();
    }

    public void addQuestion(String i, String c){
        new Question(this, i, c);
    }

    public List<Question> getQList(){
        return new ArrayList<>(this.questionList);
    }

    public String getName(){
        return this.name;
    }

    public Set<Question> getMyQuestions() {
        return new HashSet<>(this.myQuestions);
    }

    public Set<String> commonCategories(User other){
        Set<String> out = new HashSet<>();

        Map<String, Integer> thisOccurrence = getOccurrenceMap(this.questionList);
        Map<String, Integer> otherOccurrence = getOccurrenceMap(other.questionList);

        for(String str : thisOccurrence.keySet()){
            if(otherOccurrence.containsKey(str)){
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

    public void uploadMyQuestion(Question q){
        myQuestions.add(q);
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
        json.put("questions", questionsToJson());
        return json;
    }
}

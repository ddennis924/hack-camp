package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import study_tinder.Question;
import study_tinder.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// Code modeled after JsonWriter in JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RestaurantList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Question> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuestionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses RestaurantList from JSON object and returns it
    public List<Question> parseQuestionList(JSONObject jsonObject) {
        List<Question> ql = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(ql, nextQuestion);
        }
        return ql;
    }

    // MODIFIES: rl
    // EFFECTS: parses Restaurant from JSON object and adds it to rl
    private void addQuestion(List<Question> ql, JSONObject jsonObject) {
        String name = jsonObject.getString("user");
        User user = addUser(name);

        String question = jsonObject.getString("question");

        String category = jsonObject.getString("category");

        Question newQuestion = new Question(user, question, category);

        ql.add(newQuestion);
    }

    private User addUser(String name) {
        User user = new User(name);
        return user;
    }
}

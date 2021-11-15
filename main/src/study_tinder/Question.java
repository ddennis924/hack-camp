package study_tinder;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Question implements Writable {
    private User user;
    private String content;
    private String category;

    public Question(User user, String str, String c) {
        addUser(user);
        this.content = str;
        this.category = c;
    }

    private void addUser(User user) {
        this.user = user;
        this.user.getQList().add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String image) {
        this.content = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object other){
        if (other == null || this.getClass() != other.getClass()) return false;
        Question q = (Question) other;
        return this.content.equals(q.content);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", content);
        json.put("category", category);
        json.put("user", user);
        return json;
    }
}

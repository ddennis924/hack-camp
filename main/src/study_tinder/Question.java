package study_tinder;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

public class Question implements Writable {
    User user;
    String question;
    String category;

    public Question(User user, String question, String cat){
        addUser(user);
        this.question = question;
        this.category = cat;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("question", question);
        json.put("category", category);
        return json;
    }
}

package study_tinder;

import java.awt.*;

public class Question {
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
}

package study_tinder;

import java.awt.*;

public class Question {
    private User user;
    private Image image;
    private Category category;

    public Question(User user, Image i, Category c) {
        addUser(user);
        this.image = i;
        this.category = c;
    }

    private void addUser(User user) {
        this.user = user;
        this.user.getQList().add(this);
    }

    public Category getCategory(){
        return category;
    }
}

package study_tinder;

import java.awt.*;

public class Question {
    User user;
    Image image;
    Category category;

    public Question(User user, Image i, Category c) {
        addUser(user);
        this.image = i;
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
}

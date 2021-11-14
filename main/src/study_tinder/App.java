package study_tinder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class App {
    public static void main(String[] args){
        User muhan = new User("Alan");
        muhan.addQuestion(new BufferedImage(20, 20 , BufferedImage.TYPE_BYTE_GRAY), Category.MATH );
        System.out.println(muhan.getQList().size());
    }
}

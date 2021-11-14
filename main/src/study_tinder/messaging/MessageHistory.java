package study_tinder.messaging;

import study_tinder.User;

import java.util.ArrayList;
import java.util.List;

public class MessageHistory {
    private User user1;
    private User user2;
    private List<Message> messageHistory;

    public MessageHistory(User sender, User receiver, String message){
        this.user1 = sender;
        this.user2 = receiver;
        this.messageHistory = new ArrayList<>();
        messageHistory.add(new Message(sender, receiver, message));
    }

    public MessageHistory(User u1, User u2){
        this.user1 = u1;
        this.user2 = u2;
        messageHistory = new ArrayList<>();
    }

    public List<Message> getMessageHistory() {
        return new ArrayList<>(messageHistory);
    }

    public void addMessage(Message seed){
        this.messageHistory.add(seed);
    }



}

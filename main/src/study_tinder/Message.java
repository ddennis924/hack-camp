package study_tinder;

public class Message {
    private User sender;
    private User receiver;
    private String message;

    public Message(User sender, User receiver, String _message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = _message;
    }

    public String getMessage(){
        return message;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}

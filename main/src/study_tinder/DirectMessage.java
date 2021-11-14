package study_tinder;

public class DirectMessage {
    private User sender;
    private User receiver;
    private String message;

    public DirectMessage(User u1, User u2, String message){
        this.sender = u1;
        this.receiver = u2;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}

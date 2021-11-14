package server;

import study_tinder.User;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Represents a user that can send and receive messages
 */
public class Peer {
    public static final int COMMON_PORT_ID = 4441;
    private PeerThread peerThread;
    private User user;

    // EFFECTS: Creates a new peer with the given User at COMMON_PORT_ID
    public Peer(User user) throws IOException {
            this.user = user;
            this.peerThread = new PeerThread(new Socket(user.getName(), COMMON_PORT_ID));
    }
}

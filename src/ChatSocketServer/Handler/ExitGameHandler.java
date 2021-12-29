package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.json.JSONObject;

import ChatSocketServer.Group;
import ChatSocketServer.SocketConnectionServer;

public class ExitGameHandler {
	public void run(JSONObject data, BufferedReader in, BufferedWriter out) {
        String username = data.getString("username");
        if (SocketConnectionServer.socketClients.containsKey(username)) {
        	SocketConnectionServer.socketClients.remove(username);
        }
        Group group = new AcceptPairingHandler().getGroup(username);
        if (group != null) {
            new AcceptPairingHandler().removeGroup(username);
        }
        if (WaitingPairingHandler.userQueue.contains(username)) {
            WaitingPairingHandler.userQueue.remove(username);
        }
    }
}

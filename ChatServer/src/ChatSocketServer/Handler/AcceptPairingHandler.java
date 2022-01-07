package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import ChatSocketServer.DataSocketServer;
import ChatSocketServer.Group;
import ChatSocketServer.SocketConnectionServer;

public class AcceptPairingHandler {
	private static DataSocketServer datasocket = new DataSocketServer();

    // Danh sách group chat
    static public ArrayList<Group> groups = new ArrayList<>();

    public void run(JSONObject data, BufferedReader in, BufferedWriter out) {

    	String username = data.getString("username");
        boolean is_accepted = data.getBoolean("is_accepted");
        Group group = getGroup(username);
        
        // group ghép cặp đã được cập nhật khi sever tiến hành ghép cặp WaitingPairingHandler.getPair
        if (group == null){
            return;
        }
        
        String dataSend;
        boolean is_success = false;
        if (is_accepted) {
            group.setAccept_pairing_1(username, true);
            group.setAccept_pairing_2(username, true);

            if (group.isAcceptPairing1() && group.isAcceptPairing2()) {
                dataSend = datasocket.exportDataStartMessage(true);
                is_success = true;
            } else {
                return;
            }
        } else {
            dataSend = datasocket.exportDataStartMessage(false);
            removeGroup(username);
        }
        
        Map<String, Socket> userList = SocketConnectionServer.socketClients;
        Socket socketUser1 = userList.get(group.getUser1());
        Socket socketUser2 = userList.get(group.getUser2());

        try {
            BufferedWriter outUser1 = new BufferedWriter(new OutputStreamWriter(socketUser1.getOutputStream()));
            BufferedWriter outUser2 = new BufferedWriter(new OutputStreamWriter(socketUser2.getOutputStream()));
            
            outUser1.write(dataSend);
            outUser1.newLine();
            outUser1.flush();

            outUser2.write(dataSend);
            outUser2.newLine();
            outUser2.flush();

        } catch (IOException ex) {
        
        }
        
        // Nếu 1 trong 2 từ chối thì cập nhật lại danh sách từ chối
        if (is_success){
            WaitingPairingHandler.denyUsers.put(group.getUser1(), new ArrayList<>());
            WaitingPairingHandler.denyUsers.put(group.getUser2(), new ArrayList<>());
        }
        else{
            ArrayList <String> denyUsers1 = WaitingPairingHandler.denyUsers.get(group.getUser1());
            ArrayList <String> denyUsers2 = WaitingPairingHandler.denyUsers.get(group.getUser2());
            
            denyUsers1.add(group.getUser2());
            denyUsers2.add(group.getUser1());
            
            WaitingPairingHandler.denyUsers.put(group.getUser1(), denyUsers1);
            WaitingPairingHandler.denyUsers.put(group.getUser2(), denyUsers2);
        }
    }

    public Group getGroup(String username) {
        for (Group g : groups) {
            if (g.getUser1().equals(username) || g.getUser2().equals(username)) {
                return g;
            }
        }
        return null;
    }

    public void addGroup(String username1, String username2) {
        Group group = new Group(username1, username2);
        groups.add(group);
    }
    
    public void removeGroup(String username){
        for (Group g : groups) {
            if (g.getUser1().equals(username) || g.getUser2().equals(username)) {
                groups.remove(g);
                break;
            }
        }
    }
}

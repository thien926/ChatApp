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

    static public ArrayList<Group> groups = new ArrayList<>();

    public void run(JSONObject data, BufferedReader in, BufferedWriter out) {

        String username = data.getString("username");
        boolean is_accepted = data.getBoolean("is_accepted");
        Group group = getGroup(username);
        
        if (group == null){
            return ;
        }
        
//        String dataSend1 = "", dataSend2 = "";
        String dataSend = datasocket.exportDataStartMessage(true);
        
        String username1 = group.getUser1();
        String username2 = group.getUser2();
        
        Map<String, Socket> userList = new SocketConnectionServer().getSocketClients();
        Socket socketUser1 = userList.get(username1);
        Socket socketUser2 = userList.get(username2);

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
            ex.printStackTrace();
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

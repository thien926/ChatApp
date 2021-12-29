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
import ChatSocketServer.SocketConnectionServer;

public class WaitingPairingHandler {
	public static ArrayList<String> userQueue = new ArrayList<>();
    private static DataSocketServer datasocket = new DataSocketServer();
            
    public void run(JSONObject data, BufferedReader in, BufferedWriter out){
        userQueue.add(data.getString("username"));
    }
    
    public void getPair(){
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (userQueue.size() >= 2){
                String username1 = userQueue.remove(0);
                String username2 = userQueue.remove(0);
                
//                Map <String, Socket> userList = SocketConnectionServer.getSocketClients();
                Map <String, Socket> userList = new SocketConnectionServer().getSocketClients();
                Socket socketUser1 = userList.get(username1);
                Socket socketUser2 = userList.get(username2);
                
                try {
                    BufferedWriter outUser1 = new BufferedWriter(new OutputStreamWriter(socketUser1.getOutputStream()));
                    BufferedWriter outUser2 = new BufferedWriter(new OutputStreamWriter(socketUser2.getOutputStream()));
                    
                    String dataSendUser1 = datasocket.exportDataSendInvitation(username2);
                    String dataSendUser2 = datasocket.exportDataSendInvitation(username1);
                    
                    outUser1.write(dataSendUser1);
                    outUser1.newLine();
                    outUser1.flush();
                    
                    outUser2.write(dataSendUser2);
                    outUser2.newLine();
                    outUser2.flush();
                
                    new AcceptPairingHandler().addGroup(username1, username2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

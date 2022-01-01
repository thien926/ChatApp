package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import ChatSocketServer.DataSocketServer;
import ChatSocketServer.SocketConnectionServer;

public class WaitingPairingHandler {
	public static ArrayList<String> userQueue = new ArrayList<>();
    private static DataSocketServer datasocket = new DataSocketServer();
    public static Map<String, ArrayList<String>> denyUsers = new HashMap<String, ArrayList<String>>();
            
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
            	String username1 = "";
                String username2 = "";
                
                boolean is_found = false;
                for (int i=0; i<userQueue.size()-1; i++){
                    if (is_found){
                        break;
                    }
                    for (int j=i+1; j<userQueue.size(); j++){
                        ArrayList <String> denyUser_1 = denyUsers.get(userQueue.get(i));
                        ArrayList <String> denyUser_2 = denyUsers.get(userQueue.get(j));
                        
                        if (denyUser_1 == null){
                            denyUser_1 = new ArrayList <String>();
                            denyUsers.put(userQueue.get(i), new ArrayList<>());
                        }
                        
                        if (denyUser_2 == null){
                            denyUser_2 = new ArrayList <String>();
                            denyUsers.put(userQueue.get(j), new ArrayList<>());
                        }
                        
                        if (!denyUser_1.contains(userQueue.get(j)) && 
                            !denyUser_2.contains(userQueue.get(i))){
                            if (i > j){
                            	username1 = userQueue.remove(i);
                            	username2 = userQueue.remove(j);
                            }
                            else{
                            	username2 = userQueue.remove(j);
                                username1 = userQueue.remove(i);
                            }
                            
                            is_found = true;
                            break;
                        }
                    }
                }
                
                if (!is_found){
                    continue;
                }
                
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

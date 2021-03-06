package ChatSocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SocketConnectionClient {
	private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;
    private static String socketHost = "localhost";    
    private static int socketPort = 5001;
    private static Map <String, SocketHandlerClient> actions = new HashMap<String, SocketHandlerClient>();

    public SocketConnectionClient() {}
    
    public void startConnection(){
        try {
            socket = new Socket(socketHost, socketPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("===== Connected to server =====");
            
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleServer(socket, in, out);
                }
            });  
            thread.start();
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void handleServer(Socket socket, BufferedReader in, BufferedWriter out){
        try {
        	DataSocketClient dataSocket = new DataSocketClient();
            while (true){
                String rawDataReceive = in.readLine();
                if (rawDataReceive == null){
                    continue;
                }
                
                System.out.println("Receive" + rawDataReceive);
                JSONObject dataReceive = dataSocket.importData(rawDataReceive);
                JSONObject data = dataReceive.getJSONObject("data");
                String type = dataReceive.getString("type");

                switch (type) {
	                case "send_username_response":
                        // Server tr??? v??? nickname c?? t???n t???i hay kh??ng th?? m??nh t??? ?????nh ngh??a s??? ki???n n??y
                        // t???i LoginGUI
	                    actions.get("send_username_response").onHandle(data, in, out);
	                    break;
	                case "out_waiting_response":
                        // Ch???y s??? ki???n sau khi server tr??? k???t qu??? cho vi???c H???Y gh??p ????i tr?????c khi c?? ?????i t?????ng gh??p ????i
                        actions.get("out_waiting_response").onHandle(data, in, out);
                        break;
	                case "send_invitation":
//                      // Server tr??? v??? l???i m???i gh??p ????i r???i b???t s??? ki???n t???i giao di???n
                        actions.get("send_invitation").onHandle(data, in, out);
                        break;
	                // case "accept_pariring": 
	                // 	actions.get("accept_pariring").onHandle(data, in, out);
	                // 	break;
	                case "start_message":
                        // Sau khi ?????ng ?? th?? server cho ph??p b???t ?????u chat, ti???n h??nh ?????nh ngh??a s??? ki???n
                        // b???t ?????u chat t???i WaitRoom - V??o trang chat
	                	actions.get("start_message").onHandle(data, in, out);
	                	break;
	                case "exit_app_response":
                        // Tho??t game
                        actions.get("exit_app_response").onHandle(data, in, out);
                        break;	
	                case "send_message":
                        // Chat
                        actions.get("send_message").onHandle(data, in, out);
                        break;
	                case "out_room_response":
                        // R???i ph??ng
                        actions.get("out_room_response").onHandle(data, in, out);
                        break;
	                	
                    
                    case "stop":
//                        System.out.println("July");
                        in.close();
                        out.close();
                        socket.close();
                        break;
                }
            }
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void addListenConnection(String actionID, SocketHandlerClient handler){
        actions.put(actionID, handler);
    }
            
    public void stopConnection(){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("===== Closed connection to server =====");
        } catch (IOException e) { System.err.println(e); }
    }
    
    
        
   public void sendData(String data){
        try {
            out.write(data);
            out.newLine();
            out.flush();
        } catch (IOException e) { System.err.println(e); }
    }

//	public static Socket getSocket() {
//		return socket;
//	}
//
//	public static void setSocket(Socket socket) {
//		SocketConnectionClient.socket = socket;
//	}
}

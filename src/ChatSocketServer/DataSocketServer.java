package ChatSocketServer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataSocketServer {
    public DataSocketServer() {}
    
    public String encryptData(String rawData){
        return rawData;
    }
    
    /**
     * 	# data format
	{
            "type": "send_nickname_response",
            "data": {
                "is_success": true,
                "message": "asdasd"
            }
	}
     * @param groups
     * @return 
     */
    public String exportDataSendNickname(boolean is_success, String message){
        JSONObject jo = new JSONObject();      
        JSONObject data = new JSONObject();      
        
        jo.put("type", "send_nickname_response");
        
        data.put("is_success", is_success);        
        data.put("message", message);

        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    
    /** DÃ¹ng
     *  # data format
	{
            "type": "send_invitation",
            "data": {
                "username": 1101, #id ngÆ°á»�i dÃ¹ng há»‡ thá»‘ng muá»‘n ghÃ©p cáº·p
            }
	}
     * @param username
     * @return 
     */
    public String exportDataSendInvitation(String username){
        JSONObject jo = new JSONObject();        
        JSONObject data = new JSONObject();
        
        jo.put("type", "send_invitation");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     * 	# data format
	{
            "type": "start_message",
            "data": {
                "is_started": true
            }
	}
     * @param is_started
     * @param stepType
     * @return 
     */
    public String exportDataStartMessage(boolean is_started){
        JSONObject jo = new JSONObject();        
        JSONObject data = new JSONObject();
        
        jo.put("type", "start_message");
        data.put("is_started", is_started);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     * 	# data format
	{
            "type": "exit_app_response",
            "data": {}
	}
     * @return 
     */
    public String exportDataExitApp(){
        JSONObject jo = new JSONObject();          
        JSONObject data = new JSONObject();   
        
        jo.put("type", "exit_app_response");
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     * 	# data format
	{
            "type": "out_room_response",
            "data": {}
	}
     * @return 
     */
    public String exportDataOutRoom(){
        JSONObject jo = new JSONObject();          
        JSONObject data = new JSONObject();   
        
        jo.put("type", "out_room_response");
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    
    
    
    
    
    
    /** DÃ¹ng
     * 	# data format
	{
            "type": "send_message",
            "data": {
                "username": "1100", # ten user
                "message": "this is a message" # ná»™i dung tin nháº¯n
            }
	}
     * @param username
     * @param message
     * @return 
     */
    public String exportDataSendMessage(String username, String message){
        JSONObject jo = new JSONObject();        
        JSONObject data = new JSONObject();
        
        jo.put("type", "send_message");
        data.put("username", username);
        data.put("message", message);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    
    public JSONObject importData(String rawData){
        return new JSONObject(rawData);
    }
    
    public JSONArray importDataList(String rawData){
        return new JSONArray(rawData);
    }
    
    public static void main(String[] args) {
        
    }
}
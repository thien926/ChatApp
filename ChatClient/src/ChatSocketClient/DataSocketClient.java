package ChatSocketClient;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;

public class DataSocketClient {
    public DataSocketClient() {}
    
    public String encryptData(String rawData){
        return rawData;
    }
    
    /**
     *  # data format
	{
            "type": "send_username",
            "data": {
                "username": "asdasd",
            }
	}
     * @param nickname
     * @return 
     */
    public String exportDataSendUsername(String nickname) {
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();

        jo.put("type", "send_username");
        data.put("username", nickname);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     *  # data format
	{
            "type": "send_invitation",
            "data": {
                "username": 1101, #id ngÆ°á»�i dÃ¹ng há»‡ thá»‘ng muá»‘n ghÃ©p cáº·p
            }
	}
     * @param userID
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
     *  # data format
	{
            "type": "waiting_pairing",
            "data": {
                "username": 1101, #id ngÆ°á»�i dÃ¹ng
            }
	}
     * @param userID
     * @return 
     */
    public String exportDataWaitingPairing(String username){
        JSONObject jo = new JSONObject();        
        JSONObject data = new JSONObject();
        
        jo.put("type", "waiting_pairing");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     *  # data format
	{
            "type": "exit_app",
            "data": {
                "username": "asdasd",
            }
	}
     * @param nickname
     * @return 
     */
    public String exportDataExitApp(String username) {
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();

        jo.put("type", "exit_app");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     * 	# data format
	{
            "type": "send_message",
            "data": {
                "username": 1101, #id ngÆ°á»�i dÃ¹ng
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
    
    /**
     *  # data format
	{
            "type": "out_room",
            "data": {
                "username": "asdasd"
            }
	}
     * @param nickname
     * @return 
     */
    public String exportDataOutRoom(String username) {
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();

        jo.put("type", "out_room");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    
    
    /**
     *  # data format
	{
            "type": "accept_pariring",
            "data": {
                "username": 1101, 
                "is_accepted": true 
            }
	}
     * @param userID
     * @param isAccepted
     * @return 
     */
    public String exportDataAcceptPairing(String username, boolean isAccepted){
        JSONObject jo = new JSONObject();        
        JSONObject data = new JSONObject();
        
        jo.put("type", "accept_pariring");
        data.put("username", username);
        data.put("is_accepted", isAccepted);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     *  # data format
	{
            "type": "out_match",
            "data": {
                "username": 1101, #id ngÆ°á»�i dÃ¹ng 1
            }
	}
     * @param userID1
     * @param userID2
     * @return 
     */
    public String exportDataOutMatch(String username) {
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();

        jo.put("type", "out_match");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    /**
     *  # data format
	{
            "type": "out_waiting",
            "data": {
                "username": "asdasd"
            }
	}
     * @param nickname
     * @return 
     */
    public String exportDataOutWaiting(String username) {
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();

        jo.put("type", "out_waiting");
        data.put("username", username);
        jo.put("data", data);
        return encryptData(jo.toString());
    }
    
    public JSONObject importData(String rawData){
        return new JSONObject(rawData);
    }
    
    public static void main(String[] args) {
    }
}

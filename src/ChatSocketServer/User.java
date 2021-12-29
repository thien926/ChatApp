package ChatSocketServer;

public class User {
	private String username;
	private boolean inChatRoom;
	
	public User(String username, boolean inChatRoom) {
		this.username = username;
		this.inChatRoom = inChatRoom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isInChatRoom() {
		return inChatRoom;
	}

	public void setInChatRoom(boolean inChatRoom) {
		this.inChatRoom = inChatRoom;
	}
}

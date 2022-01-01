package ChatSocketServer;

public class Group {
	private String user1 = null;
	private String user2 = null;
	private boolean acceptPairing1 = false;
    private boolean acceptPairing2 = false;
	
	public Group(String user1, String user2) {
		this.user1 = user1;
		this.user2 = user2;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public boolean isAcceptPairing1() {
		return acceptPairing1;
	}
	
	public boolean isAcceptPairing2() {
		return acceptPairing2;
	}
	
	public void setAccept_pairing_1(String username, boolean acceptPairing1) {
	    if (this.user1.equals(username)) {
	        this.acceptPairing1 = acceptPairing1;
	    }
	}
	
	public void setAccept_pairing_2(String username, boolean acceptPairing2) {
	    if (this.user2.equals(username)) {
	        this.acceptPairing2 = acceptPairing2;
	    }
	}
	
	public boolean inGroup(String user){
        if (user1.equals(user) || user2.equals(user)){
            return true;
        }
        return false;
    }
	
}

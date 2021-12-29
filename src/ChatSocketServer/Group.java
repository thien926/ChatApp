package ChatSocketServer;

public class Group {
	private String user1 = null;
	private String user2 = null;
//	private boolean accept_pairing_1 = false;
//    private boolean accept_pairing_2 = false;
	
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

//	public boolean isAccept_pairing_1() {
//		return accept_pairing_1;
//	}
//
//	public void setAccept_pairing_1(String user1,boolean accept_pairing_1) {
//		if(this.user1.equals(user1)) {
//			this.accept_pairing_1 = accept_pairing_1;
//		}
//	}
//
//	public boolean isAccept_pairing_2() {
//		return accept_pairing_2;
//	}
//
//	public void setAccept_pairing_2(String user2, boolean accept_pairing_2) {
//		if(this.user2.equals(user2)) {
//			this.accept_pairing_2 = accept_pairing_2;
//		}
//	}
	
	public boolean inGroup(String user){
        if (user1.equals(user) || user2.equals(user)){
            return true;
        }
        return false;
    }
	
}

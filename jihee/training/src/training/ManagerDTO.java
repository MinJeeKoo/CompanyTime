package training;

public class ManagerDTO {
	private int mgr_num;
	private String id = null;
	private String pw = null;
	
	public int getMgr_num() {
		return mgr_num;
	}
	public void setMgr_num(int mgr_num) {
		this.mgr_num = mgr_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
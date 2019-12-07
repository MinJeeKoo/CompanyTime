package model;


public class Waiting_MenteeDTO {
	private int mtee_num;
	private String p_id;
	private String js_id;
	private int cf_num;
	private String waiting_date;
	
	public Waiting_MenteeDTO() { }		// 매개변수 없는 생성자
	public Waiting_MenteeDTO(String p_id, String js_id, int cf_num)
	{
		this.p_id = p_id;
		this.js_id = js_id;
		this.cf_num = cf_num;
	}
	
	public int getMtee_num() {
		return mtee_num;
	}
	public void setMtee_num(int mtee_num) {
		this.mtee_num = mtee_num;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getJs_id() {
		return js_id;
	}
	public void setJs_id(String js_id) {
		this.js_id = js_id;
	}
	public int getCf_num() {
		return cf_num;
	}
	public void setCf_num(int cf_num) {
		this.cf_num = cf_num;
	}
	public String getWaiting_date() {
		return waiting_date;
	}
	public void setWaiting_date(String waiting_date) {
		this.waiting_date = waiting_date;
	}
	
	

}

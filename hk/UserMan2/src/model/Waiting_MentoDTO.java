package model;

public class Waiting_MentoDTO {
	private int mto_num;
	private String w_id;
	private int cf_num;
	private String waiting_date;
	
	public Waiting_MentoDTO() { }		// 매개변수 없는 생성자
	public Waiting_MentoDTO(String w_id, int cf_num)
	{
		this.w_id = w_id;
		this.cf_num = cf_num;
	}
	
	public int getMto_num() {
		return mto_num;
	}
	public void setMto_num(int mto_num) {
		this.mto_num = mto_num;
	}
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
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

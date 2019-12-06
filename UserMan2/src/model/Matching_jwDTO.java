package model;

public class Matching_jwDTO {
	private String P_ID;
	private String W_ID;
	
	public Matching_jwDTO(String p_id, String w_id) {
		P_ID = p_id;
		W_ID = w_id;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String p_ID) {
		P_ID = p_ID;
	}

	public String getW_ID() {
		return W_ID;
	}

	public void setW_ID(String w_ID) {
		W_ID = w_ID;
	}

}

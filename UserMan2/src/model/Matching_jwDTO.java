package model;

public class Matching_jwDTO {
	private String JS_ID;
	private String W_ID;
	
	public Matching_jwDTO() {}
	public Matching_jwDTO(String js_id, String w_id) {
		JS_ID = js_id;
		W_ID = w_id;
	}

	public String getJS_ID() {
		return JS_ID;
	}

	public void setJS_ID(String js_ID) {
		JS_ID = js_ID;
	}

	public String getW_ID() {
		return W_ID;
	}

	public void setW_ID(String w_ID) {
		W_ID = w_ID;
	}

}

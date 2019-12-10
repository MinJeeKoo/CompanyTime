package model;

public class SpecDTO {
	private Integer spec_num;
	private String p_id;
	private String w_id;
	private String js_id;
	private String certification;
	private Float grade;
	private String internship;
	private Integer toeic;
	private String opic;
	private String contest;
	private String awards;
	private String study_abroad;
	private String volun;
	private String toeic_speaking;
	
	public SpecDTO() {}
	public SpecDTO(String p_id, String w_id, String js_id, 
			String certification, Float grade, String internship, Integer toeic, 
			String toeic_speaking, String opic, String contest, String awards,
			String study_abroad, String volun) {
		this.p_id = p_id;
		this.w_id = w_id;
		this.js_id = js_id;
		this.certification = certification;
		this.grade = grade;
		this.internship = internship;
		this.toeic = toeic;
		this.toeic_speaking = toeic_speaking;
		this.opic = opic;
		this.contest = contest;
		this.awards = awards;
		this.study_abroad = study_abroad;
		this.volun = volun;
	}
	
	public Integer getSpec_num() {
		return spec_num;
	}
	public void setSpec_num(Integer spec_num) {
		this.spec_num = spec_num;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
	}
	public String getJs_id() {
		return js_id;
	}
	public void setJs_id(String js_id) {
		this.js_id = js_id;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public Float getGrade() {
		return grade;
	}
	public void setGrade(Float grade) {
		this.grade = grade;
	}
	public String getInternship() {
		return internship;
	}
	public void setInternship(String internship) {
		this.internship = internship;
	}
	public Integer getToeic() {
		return toeic;
	}
	public void setToeic(Integer toeic) {
		this.toeic = toeic;
	}
	public String getOpic() {
		return opic;
	}
	public void setOpic(String opic) {
		this.opic = opic;
	}
	public String getContest() {
		return contest;
	}
	public void setContest(String contest) {
		this.contest = contest;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getStudy_abroad() {
		return study_abroad;
	}
	public void setStudy_abroad(String study_abroad) {
		this.study_abroad = study_abroad;
	}
	public String getVolun() {
		return volun;
	}
	public void setVolun(String volun) {
		this.volun = volun;
	}
	public String getToeic_speaking() {
		return toeic_speaking;
	}
	public void setToeic_speaking(String toeic_speaking) {
		this.toeic_speaking = toeic_speaking;
	}
	
	
}

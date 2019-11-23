package model;

public class InfoDTO {
      private Integer iNo = null;         // 사용자 입력 정보PK
      private String pId = null;         // 이직자 FK
      private String wId = null;         // 현직자 FK
     
      private String cfdName = null;// cdfNum-> cfdName으로 바꿈
      private String cName = null;   // cNum ->cName으로 바꿈.
      
      private Integer annual_Income = null;      // 연봉
      private Integer InfoMood = null;      // 부서 분위기
      private Integer jobSat_R = null;
      private String jobSat_H = null;// 직업 만족도
      private Integer cafeteria = null;         // 직원 식당
      private Integer trafficConven = null;      // 교통편의성
      private Integer empWellfare = null;      // 직원복지
   
     /* constructor: exclude hashtag */
   public InfoDTO( String pId, String wId, String cName, String cfdName,
		   Integer annual_Income, Integer InfoMood, Integer jobSat_R,
		   Integer cafeteria, Integer trafficConven, Integer empWellfare) {
	   this.pId = pId;
	   this.wId = wId;
	   this.cName = cName;
	   this.cfdName = cfdName;
	   this.annual_Income = annual_Income;
	   this.InfoMood = InfoMood;
	   this.jobSat_R = jobSat_R;
	   this.cafeteria = cafeteria;
	   this.trafficConven = trafficConven;
	   this.empWellfare = empWellfare;
   }
   
   public Integer getiNo() {
      return iNo;
   }
   public void setiNo(Integer iNo) {
      this.iNo = iNo;
   }
   public String getpId() {
      return pId;
   }
   public void setpId(String pId) {
      this.pId = pId;
   }
   public String getwId() {
      return wId;
   }
   public void setwId(String wId) {
      this.wId = wId;
   }
   public String getCfdName() {
      return cfdName;
   }
   public void setCfdName(String cfdName) {
      this.cfdName = cfdName;
   }
   public String getCName() {
      return cName;
   }
   public void setCName(String cName) {
      this.cName = cName;
   }
   public Integer getAnnual_Income() {
      return annual_Income;
   }
   public void setAnnual_Income(Integer annual_Income) {
      this.annual_Income = annual_Income;
   }
   public Integer getInfoMood() {
      return InfoMood;
   }
   public void setInfoMood(Integer infoMood) {
      InfoMood = infoMood;
   }
   public Integer getJobSat_R() {
      return jobSat_R;
   }
   public void setJobSat_R(Integer jobSat_R) {
      this.jobSat_R = jobSat_R;
   }
   public String getJobSat_H() {
      return jobSat_H;
   }
   public void setJobSat_H(String jobSat_H) {
      this.jobSat_H = jobSat_H;
   }
   public Integer getCafeteria() {
      return cafeteria;
   }
   public void setCafeteria(Integer cafeteria) {
      this.cafeteria = cafeteria;
   }
   public Integer getTrafficConven() {
      return trafficConven;
   }
   public void setTrafficConven(Integer trafficConven) {
      this.trafficConven = trafficConven;
   }
   public Integer getEmpWellfare() {
      return empWellfare;
   }
   public void setEmpWellfare(Integer empWellfare) {
      this.empWellfare = empWellfare;
   }
      
      
}
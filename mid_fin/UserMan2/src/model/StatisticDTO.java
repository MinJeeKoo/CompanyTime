package model;

public class StatisticDTO {
	   private Integer stNo = null;			// 통계PK
	   private Integer cNo = null;			// company FK
	   private Integer cfdNo = null;			// department FK
	   private Integer annual_Income = null;		// 연봉
	   private Integer InfoMood = null;		// 부서 분위기
	   private Integer jobSat = null;			// 직업 만족도
	   private Integer cafeteria = null;			// 직원 식당
	   private Integer trafficConven = null;		// 교통편의성
	   private Integer empWellfare = null;		// 직원복지
	   
	   public Integer getStNo() 
	   {	return stNo;   }
	   
	   public void setStNo (Integer stNo)
	   {	this.stNo = stNo;       }

	    public Integer getCNo() 
	   {	return cNo;   }
	   
	   public void setCNO(Integer cNo) 
	   {	this.cNo = cNo;       }

	   public Integer getCfdNo() 
	   {	return cfdNo;	   }
	   
	   public void setCfdNo(Integer cfdNo) 
	   {	this.cfdNo = cfdNo;       }

	   public Integer getAnnual_Income() 
	   {	return this.annual_Income;      }

	   public void setAnnual_Income(Integer annual_Income) 
	   {	this.annual_Income = annual_Income;       }
	 
	   public Integer getInfoMood() 
	   {	return InfoMood;       }

	   public void setInfoMood(Integer InfoMood) 
	   {	this.InfoMood = InfoMood;       }

	   public Integer getJobSat() 
	   {	return jobSat;       }

	   public void setJobSat(Integer jobSat) 
	   {	this.jobSat = jobSat;      }

	   public Integer getCafeteria() 
	   {	return cafeteria;       }

	   public void setCafeteria(Integer cafeteria) 
	   {	this.cafeteria = cafeteria;       }

	   public Integer getTrafficConven() 
	   {	return trafficConven;       }

	   public void setTrafficConven(Integer trafficConven) 
	   {	this.trafficConven = trafficConven;       }

	   public Integer getEmpWellfare() 
	   {	return empWellfare;       }

	   public void setEmpWellfare(Integer empWellfare) 
	   {	this.empWellfare = empWellfare;      }
}

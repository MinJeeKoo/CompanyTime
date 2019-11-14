package service.dto;

// 신뢰도와 관련한 정보를 저장하기 위한 DTO(Data Transition Object)
public class TrustDTO {
   private Integer tNo = null;			// 신뢰도PK
   private Integer cfdNo = null;			// department FK
   private Integer annual_Income = null;		// 연봉
   private Integer InfoMood = null;		// 부서 분위기
   private Integer jobSat = null;			// 직업 만족도
   private Integer cafeteria = null;			// 직원 식당
   private Integer trafficConven = null;		// 교통편의성
   private Integer empWellfare = null;		// 직원복지
   
   public Integer getTNo() 
   {	return tNo;    }
   
   public void setTNo (Integer tNo)
   {	this.tNo = tNo;       }
   
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
   {	this.InfoMood = InfoMood;      }

   public Integer getJobSat() 
   {	return jobSat;       }

   public void setJobSat(Integer jobSat) 
   {	this.jobSat = jobSat;      }

   public Integer getCafeteria() 
   {	return cafeteria;      }

   public void setCafeteria(Integer cafeteria) 
   {	this.cafeteria = cafeteria;      }

   public Integer getTrafficConven() 
   {	return trafficConven;      }

   public void setTrafficConven(Integer trafficConven) 
   {	this.trafficConven = trafficConven;      }

   public Integer getEmpWellfare() 
   {	return empWellfare;      }

   public void setEmpWellfare(Integer empWellfare) 
   {	this.empWellfare = empWellfare;      }
}


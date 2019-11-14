
package service.dto;

// 사용자 입력 정보를 저장하기 위한 DTO(Data Transition Object)
public class InfoDTO {
   private Integer iNo = null;			// 사용자 입력 정보PK
   private Integer pNo = null;			// 이직자 FK
   private Integer wNo = null;			// 현직자 FK
   private Integer cfdNo = null;			// department 번호
   private Integer annual_Income = null;		// 연봉
   private Integer InfoMood = null;		// 부서 분위기
   private Integer jobSat = null;			// 직업 만족도
   private Integer cafeteria = null;			// 직원 식당
   private Integer trafficConven = null;		// 교통편의성
   private Integer empWellfare = null;		// 직원복지
   

   public Integer getINo() 
   {	return iNo;	}
   
   public void setINo (Integer iNo)
   {	this.iNo = iNo;    }
   
   public Integer getPNo() 
   {	return pNo; 	}
   
   public void setPNo (Integer pNo)
   {	this.pNo = pNo;      }

   public Integer getWNo() 
   {	return wNo;	}
   
   public void setWNo (Integer wNo)
   {	this.wNo = wNo;       }

   public Integer getCfdNO() 
   {	return cfdNo;		}
   
   public void setCfdNO(Integer cfdNo) 
   {	this.cfdNo = cfdNo;       }

   public Integer getAnnual_Income() 
   {	return this.annual_Income;       }

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
   {	return cafeteria;     }

   public void setCafeteria(Integer cafeteria) 
   {	this.cafeteria = cafeteria;       }

   public Integer getTrafficConven() 
   {	return trafficConven;      }

   public void setTrafficConven(Integer trafficConven) 
   {	this.trafficConven = trafficConven;      }

   public Integer getEmpWellfare() 
   {	return empWellfare;      }

   public void setEmpWellfare(Integer empWellfare) 
   {	this.empWellfare = empWellfare;       }
}

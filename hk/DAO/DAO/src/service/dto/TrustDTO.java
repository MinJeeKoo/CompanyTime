package service.dto;

// �ŷڵ��� ������ ������ �����ϱ� ���� DTO(Data Transition Object)
public class TrustDTO {
   private Integer tNo = null;			// �ŷڵ�PK
   private Integer cfdNo = null;			// department FK
   private Integer annual_Income = null;		// ����
   private Integer InfoMood = null;		// �μ� ������
   private Integer jobSat = null;			// ���� ������
   private Integer cafeteria = null;			// ���� �Ĵ�
   private Integer trafficConven = null;		// �������Ǽ�
   private Integer empWellfare = null;		// ��������
   
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


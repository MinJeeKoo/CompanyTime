
package service.dto;

// ����� �Է� ������ �����ϱ� ���� DTO(Data Transition Object)
public class InfoDTO {
   private Integer iNo = null;			// ����� �Է� ����PK
   private Integer pNo = null;			// ������ FK
   private Integer wNo = null;			// ������ FK
   private Integer cfdNo = null;			// department ��ȣ
   private Integer annual_Income = null;		// ����
   private Integer InfoMood = null;		// �μ� ������
   private Integer jobSat = null;			// ���� ������
   private Integer cafeteria = null;			// ���� �Ĵ�
   private Integer trafficConven = null;		// �������Ǽ�
   private Integer empWellfare = null;		// ��������
   

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
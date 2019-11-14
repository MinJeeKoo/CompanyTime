package persistence.dao;

import java.util.List;
import service.dto.TrustDTO;

public interface TrustDAO {
	public List<TrustDTO> getInfoList();		
	public Integer insertTrust(TrustDTO trust);	
	public Integer updateTrust(TrustDTO trust);	
	public Integer deleteTrust(Integer tCode);		
	public TrustDTO getTrustByName(String tName);		
	public TrustDTO getTrustByCode(String tCode);
	List<TrustDTO> getTrustList();		
}

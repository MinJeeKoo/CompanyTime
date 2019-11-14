package persistence.dao;

import java.util.List;
import service.dto.StatisticDTO;

public interface StatisticDAO {
	public List<StatisticDTO> getStatisticList();		
	public Integer insertStatistic(StatisticDTO statistic);	
	public Integer updateStatistic(StatisticDTO statistic);	
	public Integer deleteStatistic(Integer sCode);		
	public StatisticDTO getStatisticByName(String stName);		
	public StatisticDTO getStatisticByCode(String sCode);		
}

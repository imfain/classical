package fwj.classical.mozart.resource.com.buss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import fwj.classical.mozart.resource.com.entity.Holiday;
import fwj.classical.mozart.resource.com.repos.HolidayRepos;

@Component
public class HolidayBuss {
	
	@Autowired
	private HolidayRepos holidayRepository;

	@Cacheable(value = "HolidayBuss.queryAll")
	public List<Holiday> queryAll() {
		return holidayRepository.findAll();
	}

}

package fwj.classical.mozart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fwj.classical.mozart.forex.task.ForexPriceImporter;

@Component
public class MozartScheduler {
	
	@Autowired
	private ForexPriceImporter forexPriceImporter;
	
	/**
	 * 每天6点调度
	 */
	@Scheduled(cron = "0 0 6 * * ?")
	public void refreshForexPrice() {
		forexPriceImporter.execute();
	}

}

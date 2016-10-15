package fwj.classical.mozart;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MozartLaunch {
	
	protected static Logger log = Logger.getLogger(MozartLaunch.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		cxt.registerShutdownHook();
		log.info("started");
		while(true) {
			try {
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {
				cxt.close();
				return;
			}
		}
	}
}

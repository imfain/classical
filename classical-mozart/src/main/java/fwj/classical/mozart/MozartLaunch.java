package fwj.classical.mozart;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class MozartLaunch  extends AbstractBaseLaunch {

	@Override
	protected void execute() throws Exception {
		log.info("started");
		while(true) {
			try {
				TimeUnit.HOURS.sleep(1);
				log.info("alive");
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		launch(MozartLaunch.class);
	}
}

package fwj.classical.mozart.forex.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fwj.classical.mozart.forex.task.ForexPriceImporter;
import fwj.classical.mozart.manual.launch.AbstractBaseLaunch;

@Component
public class ForexPriceDownload extends AbstractBaseLaunch {
	
	
	@Autowired
	private ForexPriceImporter importer;

	@Override
	protected void execute() throws Exception {
		importer.execute();
	}

	public static void main(String[] args) {
		launch(ForexPriceDownload.class);
	}
}

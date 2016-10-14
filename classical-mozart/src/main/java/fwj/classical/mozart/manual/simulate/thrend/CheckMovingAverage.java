package fwj.classical.mozart.manual.simulate.thrend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fwj.classical.mozart.manual.enu.ProdEnum;
import fwj.classical.mozart.manual.launch.AbstractBaseLaunch;
import fwj.classical.mozart.resource.price.vo.Price;
import fwj.classical.mozart.resource.trend.buss.MovingAvgBuss;
import fwj.classical.mozart.resource.trend.indicator.EMA;
import fwj.classical.mozart.resource.trend.vo.ProdMA;

@Component
public class CheckMovingAverage extends AbstractBaseLaunch {

	@Autowired
	MovingAvgBuss movingAvgBuss;

	@Override
	protected void execute() throws Exception {
		// Calendar cal = Calendar.getInstance();
		// Date endDt = cal.getTime();
		// cal.add(Calendar.YEAR, -20);
		// Date startDt = cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = df.parse("2015-10-21");
		Date endDt = df.parse("2015-10-22");

		ProdMA prodMA = movingAvgBuss.calProdMovingAverage("RB", -1, new EMA(20));
		List<Price> prodPriceList = prodMA.getProdIndexLine().getPrices();
		List<Price> EMAList = prodMA.getMvAvgLineList().get(0).getPrices();
		

		
		
	}

	public static void main(String[] args) {
		launch(CheckMovingAverage.class);
	}
}

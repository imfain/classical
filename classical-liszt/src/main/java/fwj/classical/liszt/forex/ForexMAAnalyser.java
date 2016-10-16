package fwj.classical.liszt.forex;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

import fwj.classical.bach.common.vo.Price;
import fwj.classical.bach.forex.price.entity.ForexPrice;
import fwj.classical.bach.forex.price.repos.ForexPriceRepos;
import fwj.classical.liszt.AbstractBaseLaunch;
import fwj.classical.liszt.util.indicator.Indicators;
import fwj.classical.liszt.util.transformer.Transformers;
import fwj.classical.liszt.util.transformer.Wave;

@Component
public class ForexMAAnalyser extends AbstractBaseLaunch {

	@Autowired
	private ForexPriceRepos forexPriceRepos;

	@Override
	protected void execute() throws Exception {
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = yyyyMMdd.parse("2010-01-01");
		Date endDt = yyyyMMdd.parse("2016-12-31");

		List<ForexPrice> priceList = forexPriceRepos.findByCodeAndDtBetweenOrderByDtAsc("EURUSD", startDt, endDt);
		List<Price> maList = Indicators.ma(priceList, 20);
		List<Wave> waveList = Transformers.tfWaveList(maList);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<String> dataList = new ArrayList<>();
		for (Wave w : waveList) {
			String out = String.join(",", df.format(w.getSd()), df.format(w.getEd()), String.valueOf(w.getCnt()),
					w.getSp().toString(), w.getEp().toString(), w.getFrofitRate().toString());
			dataList.add(out);
		}
		Files.asCharSink(new File("F:/201610/1016.csv"), StandardCharsets.UTF_8).writeLines(dataList);
		
		

	}

	public static void main(String[] args) {
		launch(ForexMAAnalyser.class);
	}

}

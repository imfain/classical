package fwj.classical.mozart.forex.init;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.Resources;

import fwj.classical.mozart.forex.common.repos.ForexPairRepos;
import fwj.classical.mozart.forex.price.entity.ForexPrice;
import fwj.classical.mozart.forex.price.repos.ForexPriceRepos;
import fwj.classical.mozart.manual.launch.AbstractBaseLaunch;

@Component
public class ForexPriceDownload extends AbstractBaseLaunch {
	
	private static final String FOREX_URI = "http://vip.stock.finance.sina.com.cn/forex/api/jsonp.php/data=/NewForexService.getDayKLine?symbol=%s";

	@Autowired
	private ForexPairRepos forexPairRepos;
	
	@Autowired
	private ForexPriceRepos forexPriceRepos;

	@Override
	protected void execute() throws Exception {
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		
		forexPairRepos.findAll().stream().forEach(pair -> {
			try {
				log.info("Downloading forex of " + pair.getCode());
				String symbol = "fx_s" + pair.getCode().toLowerCase();
				String raw = Resources.toString(new URL(String.format(FOREX_URI, symbol)), StandardCharsets.UTF_8);
				Pattern p = Pattern.compile("new String\\(\"(.*)\"\\)");
				Matcher m = p.matcher(raw);
				if(m.find()) {
					String trimed = m.group(1);
					String[] dailyArr = trimed.split("\\|");
					for(String daily : dailyArr) {
						String[] eleArr = daily.split(",");
						ForexPrice price = new ForexPrice();
						price.setDt(yyyyMMdd.parse(eleArr[0]));
						price.setCode(pair.getCode());
						price.setOpen(new BigDecimal(eleArr[1]));
						price.setMin(new BigDecimal(eleArr[2]));
						price.setMax(new BigDecimal(eleArr[3]));
						price.setClose(new BigDecimal(eleArr[4]));
						forexPriceRepos.save(price);				
					}
					forexPriceRepos.flush();
				}
			} catch (Exception e) {
				log.error("Bad boy " + pair.getCode(), e);
			}
		});
		
	}

	public static void main(String[] args) {
		launch(ForexPriceDownload.class);
	}
}

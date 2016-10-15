package fwj.classical.mozart.forex.task;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.Resources;

import fwj.classical.bach.forex.common.repos.ForexPairRepos;
import fwj.classical.bach.forex.price.entity.ForexPrice;
import fwj.classical.bach.forex.price.repos.ForexPriceRepos;

@Component
public class ForexPriceImporter {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	private static final String FOREX_URI = "http://vip.stock.finance.sina.com.cn/forex/api/jsonp.php/data=/NewForexService.getDayKLine?symbol=%s";

	@Autowired
	private ForexPairRepos forexPairRepos;
	
	@Autowired
	private ForexPriceRepos forexPriceRepos;

	public void execute() {
		DateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		
		forexPairRepos.findAll().stream().forEach(pair -> {
			int days = 0;
			try {
				String symbol = "fx_s" + pair.getCode().toLowerCase();
				// 获取外汇价格远程数据
				String raw = Resources.toString(new URL(String.format(FOREX_URI, symbol)), StandardCharsets.UTF_8);
				Pattern p = Pattern.compile("new String\\(\"(.*)\"\\)");
				Matcher m = p.matcher(raw);
				if(m.find()) {
					
					// 保存的最新记录
					ForexPrice latest = forexPriceRepos.findTopByCodeOrderByDtDesc(pair.getCode());
					
					String trimed = m.group(1);
					String[] dailyArr = trimed.split("\\|");
					for(String daily : dailyArr) {
						String[] eleArr = daily.split(",");
						ForexPrice price = new ForexPrice();
						Date dt = yyyyMMdd.parse(eleArr[0]);
						if(latest == null || latest.getDt().compareTo(dt) <0) {
							// 增量更新
							price.setDt(dt);
							price.setCode(pair.getCode());
							price.setOpen(new BigDecimal(eleArr[1]));
							price.setMin(new BigDecimal(eleArr[2]));
							price.setMax(new BigDecimal(eleArr[3]));
							price.setClose(new BigDecimal(eleArr[4]));
							forexPriceRepos.save(price);
							days++;
						}
					}
					forexPriceRepos.flush();
				}
			} catch (Exception e) {
				log.error("Bad boy " + pair.getCode(), e);
			} finally {
				log.info("Downloading forex of " + pair.getCode() + " " + days + " days");
			}
		});
		
	}

}

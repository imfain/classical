package fwj.classical.mozart.forex.init;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fwj.classical.mozart.forex.common.entity.ForexPair;
import fwj.classical.mozart.forex.common.repos.ForexPairRepos;
import fwj.classical.mozart.manual.launch.AbstractBaseLaunch;

/**
 * 
 * 十大货币交易量： 美元（85%）、欧元（39%）、日元（19%）、英镑（13%）、澳元（8%）、
 * 瑞郎（6%）、加元（5%）、墨西哥比索（2.5%）、人民币（2.2%）、纽元（1.4%）。
 * 
 */
@Component
public class ForexPairImport extends AbstractBaseLaunch {

	@Autowired
	private ForexPairRepos forexPairRepos;
	
	@Override
	protected void execute() throws Exception {

		Stream.of( //美元
				new ForexPair("EURUSD", "欧美", 39 * 85), //
				new ForexPair("USDJPY", "美日", 19 * 85), //
				new ForexPair("GBPUSD", "镑美", 13 * 85), //
				new ForexPair("AUDUSD", "澳美", 8 * 85), //
				new ForexPair("USDCHF", "美瑞", 6 * 85), //
				new ForexPair("USDCAD", "美加", 5 * 85), //
				new ForexPair("NZDUSD", "纽美", 1 * 85), //
				// 日元
				new ForexPair("EURJPY", "欧日", 39 * 19), //
				new ForexPair("GBPJPY", "镑日", 13 * 19), //
				new ForexPair("AUDJPY", "澳日", 8 * 19), //
				new ForexPair("CADJPY", "加日", 5 * 19), //
				new ForexPair("NZDJPY", "纽日", 1 * 19), //
				// 欧元
				new ForexPair("EURGBP", "欧镑", 39 * 13), //
				new ForexPair("EURAUD", "欧澳", 39 * 8), //
				new ForexPair("EURCHF", "欧瑞", 39 * 6), //
				// 英镑
				new ForexPair("GBPAUD", "镑澳", 13 * 8), //
				new ForexPair("GBPCHF", "镑瑞", 13 * 6), //
				// 澳元
				new ForexPair("AUDNZD", "澳纽", 8 * 1)  //
		).forEach(ele -> {
			forexPairRepos.save(ele);
		});

		forexPairRepos.flush();
	}

	public static void main(String[] args) {
		launch(ForexPairImport.class);
	}

}

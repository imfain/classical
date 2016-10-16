package fwj.classical.liszt.util.indicator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fwj.classical.bach.common.vo.Price;
import fwj.classical.bach.common.vo.SimplePrice;

public class MAIndicator {

	public static List<Price> calculate(List<? extends Price> priceLine, int range) {
		if (priceLine.size() < range) {
			return Collections.emptyList();
		}
		
		List<Price> malist = new ArrayList<>();
		BigDecimal rangeD = new BigDecimal(range);
		BigDecimal sum = BigDecimal.ZERO;
		// 计算首个坐标
		for(int i=0; i<range; i++) {
			sum = sum.add(priceLine.get(i).getP());
		}
		Price firstMA = new SimplePrice(priceLine.get(range-1).getDt(),
				sum.divide(rangeD, 5, RoundingMode.DOWN));
		malist.add(firstMA);
		// 计算后续坐标
		for(int i=range; i<priceLine.size(); i++) {
			sum = sum.subtract(priceLine.get(i-range).getP()).add(priceLine.get(i).getP());
			Price ma = new SimplePrice(priceLine.get(i).getDt(),
					sum.divide(rangeD, 5, RoundingMode.DOWN));
			malist.add(ma);
		}
		return malist;
	}

}

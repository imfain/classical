package fwj.classical.liszt.util.indicator;

import java.util.List;

import fwj.classical.bach.common.vo.Price;

public class Indicators {
	
	static public List<Price> ma(List<? extends Price> priceLine, int range) {
		return MAIndicator.calculate(priceLine, range);
	}

}

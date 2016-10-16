package fwj.classical.liszt.util.transformer;

import java.util.List;

import fwj.classical.bach.common.vo.Price;

public class Transformers {
	
	public static List<Wave> tfWaveList(List<Price> priceList) {
		return WaveTF.tf(priceList);
	}

}

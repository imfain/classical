package fwj.classical.liszt.util.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fwj.classical.bach.common.vo.Price;

public class WaveTF {

	public static List<Wave> tf(List<Price> priceList) {
		List<Price> summitList = new ArrayList<>();
		for (int i = 0; i < priceList.size(); i++) {
			if (i == 0 || i == priceList.size() - 1) {
				summitList.add(priceList.get(i));
			} else {
				BigDecimal curr = priceList.get(i).getP();
				BigDecimal prev = priceList.get(i - 1).getP();
				BigDecimal next = priceList.get(i + 1).getP();
				if (curr.compareTo(prev) == curr.compareTo(next)) {
					summitList.add(priceList.get(i));
				}
			}
		}

		List<Wave> waveList = new ArrayList<>();
		for (int i = 0; i < summitList.size() - 1; i++) {
			Price start = summitList.get(i);
			Price end = summitList.get(i + 1);
			int startIndex = priceList.indexOf(start);
			int endIndex = priceList.indexOf(end);
			Wave wave = new Wave(start.getDt(), end.getDt(), start.getP(), end.getP(), (endIndex - startIndex));
			waveList.add(wave);
		}

		return waveList;
	}

}

package fwj.classical.bach.common.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SimplePrice implements Price {

	private Date dt;
	private BigDecimal p;

	public SimplePrice(Date dt, BigDecimal p) {
		this.dt = dt;
		this.p = p;
	}

	public Date getDt() {
		return dt;
	}

	public BigDecimal getP() {
		return p;
	}

}

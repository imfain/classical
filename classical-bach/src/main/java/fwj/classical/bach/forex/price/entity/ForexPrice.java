package fwj.classical.bach.forex.price.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "forex_price", uniqueConstraints = {
		@UniqueConstraint(name = "forex_price_uni", columnNames = { "code", "dt" }) })
public class ForexPrice extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = -6510605608176847660L;

	@Column(columnDefinition = "DATE")
	private Date dt;

	@Column(length = 6)
	private String code;

	@Column(precision = 10, scale = 5)
	private BigDecimal open;

	@Column(precision = 10, scale = 5)
	private BigDecimal close;

	@Column(precision = 10, scale = 5)
	private BigDecimal max;

	@Column(precision = 10, scale = 5)
	private BigDecimal min;

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}
}

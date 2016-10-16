package fwj.classical.liszt.util.transformer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Wave {
		private Date sd;
		private Date ed;
		private BigDecimal sp;
		private BigDecimal ep;
		private int cnt;

		public Wave(Date sd, Date ed, BigDecimal sp, BigDecimal ep, int cnt) {
			this.sd = sd;
			this.ed = ed;
			this.sp = sp;
			this.ep = ep;
			this.cnt = cnt;
		}

		public Date getSd() {
			return sd;
		}

		public Date getEd() {
			return ed;
		}

		public BigDecimal getSp() {
			return sp;
		}

		public BigDecimal getEp() {
			return ep;
		}

		public int getCnt() {
			return cnt;
		}
		
		public BigDecimal getFrofit() {
			return ep.subtract(sp);
		}
		
		public BigDecimal getFrofitRate() {
			return ep.subtract(sp).divide(sp, 5, RoundingMode.DOWN);
		}

	}
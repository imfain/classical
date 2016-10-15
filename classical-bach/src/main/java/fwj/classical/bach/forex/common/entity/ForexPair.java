package fwj.classical.bach.forex.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ForexPair extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 6184156813269516004L;

	@Column(length = 6)
	private String code;

	@Column(length = 10)
	private String name;

	private int weight;

	public ForexPair() {

	}

	public ForexPair(String code, String name, int weight) {
		this.code = code;
		this.name = name;
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}

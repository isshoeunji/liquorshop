package kosta.mvc.model.dto;

public class LiquorTable {
	private int liquorTableNo;
	private String liquorType;
	
	public LiquorTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LiquorTable(int liquorTableNo, String liquorType) {
		super();
		this.liquorTableNo = liquorTableNo;
		this.liquorType = liquorType;
	}

	public int getLiquorTableNo() {
		return liquorTableNo;
	}

	public void setLiquorTableNo(int liquorTableNo) {
		this.liquorTableNo = liquorTableNo;
	}

	public String getLiquorType() {
		return liquorType;
	}

	public void setLiquorType(String liquorType) {
		this.liquorType = liquorType;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("*종류 번호: ");
		builder.append(liquorTableNo);
		builder.append("\t*양주 종류: ");
		builder.append(liquorType);
		return builder.toString();
	}
	
	

}

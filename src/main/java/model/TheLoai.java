package model;

public class TheLoai {
	private String maTheLoai;
	private String tenTheLoại;

	public TheLoai() {
	}

	public TheLoai(String maTheLoai, String tenTheLoại) {
		this.maTheLoai = maTheLoai;
		this.tenTheLoại = tenTheLoại;
	}

	public String getMaTheLoai() {
		return maTheLoai;
	}

	public void setMaTheLoai(String maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoại() {
		return tenTheLoại;
	}

	public void setTenTheLoại(String tenTheLoại) {
		this.tenTheLoại = tenTheLoại;
	}

	@Override
	public String toString() {
		return "TheLoai [maTheLoai=" + maTheLoai + ", tenTheLoại=" + tenTheLoại + "]";
	}
	
	
	
}

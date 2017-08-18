package co.kr.sky.model;


public class DocumentInfo {
	//고지서에 출력할 내용
	private String[] header;
	private String[][] list;
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	public String[][] getList() {
		return list;
	}
	public void setList(String[][] list) {
		this.list = list;
	}
	
	
}

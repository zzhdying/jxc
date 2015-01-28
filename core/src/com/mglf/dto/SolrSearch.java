package com.mglf.dto;

public class SolrSearch {
	private String q;//Field:value
	private Integer start;//开始行
	private Integer rows;//行数
	private String cursorMark;//游标，第一次用*
	private String fq;
	private String sort;//排序 Field desc,Field asc
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getCursorMark() {
		return cursorMark;
	}
	public void setCursorMark(String cursorMark) {
		this.cursorMark = cursorMark;
	}
	public String getFq() {
		return fq;
	}
	public void setFq(String fq) {
		this.fq = fq;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public SolrSearch(String q, Integer start, Integer rows, String sort) {
		super();
		this.q = q;
		this.start = start;
		this.rows = rows;
		this.sort = sort;
	}
	public SolrSearch(String q, Integer start, Integer rows) {
		super();
		this.q = q;
		this.start = start;
		this.rows = rows;
	}
	public SolrSearch(String q, Integer rows, String cursorMark, String sort) {
		super();
		this.q = q;
		this.rows = rows;
		this.cursorMark = cursorMark;
		this.sort = sort;
	}
	public SolrSearch(String q, Integer rows, String cursorMark) {
		super();
		this.q = q;
		this.rows = rows;
		this.cursorMark = cursorMark;
	}
	
}

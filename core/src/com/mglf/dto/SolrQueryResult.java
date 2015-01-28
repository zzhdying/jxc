package com.mglf.dto;

import java.io.Serializable;
import java.util.List;

public class SolrQueryResult extends JsonResult implements Serializable {

	private static final long serialVersionUID = -1;

	private List list;

	private long count;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}

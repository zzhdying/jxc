package com.mglf.dto;

import java.io.Serializable;
import java.util.List;

public class MsgQueryResult extends JsonResult implements Serializable {

	private static final long serialVersionUID = -1;

	private List<MsgContent> list;

	private long count;

	public List<MsgContent> getList() {
		return list;
	}

	public void setList(List<MsgContent> list) {
		this.list = list;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}

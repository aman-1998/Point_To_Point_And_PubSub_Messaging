package personal.learning.jms.model;

import java.io.Serializable;

public class OrderHistoryReq implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String fromDate;
	
	private String toDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "InvoiceReq [username=" + username + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
}

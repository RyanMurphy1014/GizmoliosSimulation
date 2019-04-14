package TimeRecords;

public class InitialTimeRecord {
	
	private TimeDate arrival;
	private TimeDate request;
	
	public InitialTimeRecord() {
		super();
	}
	
	public InitialTimeRecord(TimeDate arrival, TimeDate request) {
		super();
		this.arrival = arrival;
		this.request = request;
	}
	
	public String toString() {
		
		return arrival.toString() + "," + request.toString();
	}

	public TimeDate getArrival() {
		return arrival;
	}

	public void setArrival(TimeDate arrival) {
		this.arrival = arrival;
	}

	public TimeDate getRequest() {
		return request;
	}

	public void setRequest(TimeDate request) {
		this.request = request;
	}


}

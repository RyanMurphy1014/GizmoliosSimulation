
public class TimeRecord {
	
	private TimeDate arrival;
	private TimeDate request;
	private TimeDate start;
	private TimeDate end;
	
	public TimeRecord() {
		super();
	}
	
	public TimeRecord(TimeDate arrival, TimeDate request, TimeDate start, TimeDate end) {
		super();
		this.arrival = arrival;
		this.request = request;
		this.start = start;
		this.end = end;
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

	public TimeDate getStart() {
		return start;
	}

	public void setStart(TimeDate start) {
		this.start = start;
	}

	public TimeDate getEnd() {
		return end;
	}

	public void setEnd(TimeDate end) {
		this.end = end;
	}

}

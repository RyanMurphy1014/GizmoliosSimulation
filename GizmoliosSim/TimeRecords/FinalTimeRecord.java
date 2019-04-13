package TimeRecords;

public class FinalTimeRecord {

	private TimeDate start;
	private TimeDate end;

	public FinalTimeRecord() {
		super();
	}

	public FinalTimeRecord(TimeDate str, TimeDate e) {
		super();
		this.start = str;
		this.end = e;
	}

	public String toString() {

		return this.start.toString() + "," + this.end.toString();
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

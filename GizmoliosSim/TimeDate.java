import java.time.LocalDate;
import java.time.LocalTime;

public class TimeDate {
	
	private LocalDate ld;
	private LocalTime lt;
	
	public TimeDate() {
		super();
	}

	public TimeDate(LocalDate ld, LocalTime lt) {
		super();
		this.ld = ld;
		this.lt = lt;
	}

	public LocalDate getLd() {
		return ld;
	}

	public void setLd(LocalDate ld) {
		this.ld = ld;
	}

	public LocalTime getLt() {
		return lt;
	}

	public void setLt(LocalTime lt) {
		this.lt = lt;
	}

}

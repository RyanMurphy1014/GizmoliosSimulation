import java.time.LocalDate;
import java.time.LocalTime;
public class TimeDate {
	
	
	private LocalDate ld;
	private LocalTime lt;
	
	public TimeDate() {
		super();
		this.ld = LocalDate.now();
		this.lt = LocalTime.now();
	}

	public TimeDate(LocalDate ld, LocalTime lt) {
		super();
		this.ld = ld;
		this.lt = lt;
	}
	
	public String toString() {
		
		return ld + " " + lt;
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

	public void now() {
		ld = LocalDate.now();
		lt = LocalTime.now();
	}
	
}

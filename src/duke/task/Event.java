package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        this(description, at, false);
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override

    public String saveFormat() {
        return "E" + "~" + super.saveFormat() + "~" + this.at;
    }

    public boolean hasSameDate(LocalDate theDate) {
        return at.toLocalDate().equals(theDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(this.DATE_FORMAT) + ")";
    }
}
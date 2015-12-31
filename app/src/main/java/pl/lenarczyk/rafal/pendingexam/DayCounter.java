package pl.lenarczyk.rafal.pendingexam;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DayCounter {

    private final DateTime now;
    private final DateTime end;


    public DayCounter() {
        now = DateTime.now();
        end = new DateTime(2016,3, 31, 12, 0);
    }

    public int days() {
        return Days.daysBetween(now, end).getDays();
    }
}

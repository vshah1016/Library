import java.util.HashMap;

public class Date {
    final int month;
    final int day;
    final int year;

    HashMap<Integer, Integer> monthAndDay;


    public Date(int month, int day, int year) {
        if (month < 1 || month > 12 || year < 0 || day < 1) throw new InvalidDate();
        this.month = month;
        this.day = day;
        this.year = year;

        boolean leap = year % 4 == 0;
        if (leap && year % 100 == 0) leap = false;
        if (year % 400 == 0) leap = true;

        boolean finalLeap = leap;
        monthAndDay = new HashMap<Integer, Integer>() {
            {
                put(1, 31);
                put(2, 28 + (finalLeap ? 1 : 0));
                put(3, 31);
                put(4, 30);
                put(5, 31);
                put(6, 30);
                put(7, 31);
                put(8, 31);
                put(9, 30);
                put(10, 31);
                put(11, 30);
                put(12, 31);
            }
        };

        if (day > monthAndDay.get(month)) throw new InvalidDate();
    }

    int daysGoneBy() {
        int monthdays = day;
        for (int i = 1; i < month; i++) {
            monthdays += monthAndDay.get(i);
        }
        return monthdays;
    }

    boolean isAfter(Date date) {
        if (date.year < year) return true;
        else if (year == date.year) return daysGoneBy() >= date.daysGoneBy();
        return false;
    }

    int isAfterBy(Date date) {
        if (!this.isAfter(date)) return -1;
        int thisAfterBy = daysGoneBy();
        for (int i = 0; i < year; i++) {
            boolean leap = year % 4 == 0;
            if (leap && year % 100 == 0) leap = false;
            if (year % 400 == 0) leap = true;
            if (leap) thisAfterBy += 366;
            else thisAfterBy += 365;
        }
        int thatAfterBy = date.daysGoneBy();
        for (int i = 0; i < date.year; i++) {
            boolean leap = date.year % 4 == 0;
            if (leap && date.year % 100 == 0) leap = false;
            if (date.year % 400 == 0) leap = true;
            if (leap) thatAfterBy += 366;
            else thatAfterBy += 365;
        }
        return thisAfterBy - thatAfterBy;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}

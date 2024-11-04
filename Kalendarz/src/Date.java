import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        setDate(year, month, day);
    }

    public Date(String year, String month, String day) {
        setDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    public static Date parse(String dateStr) {
        String[] parts = dateStr.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-mm-yyyy");
        }
        return new Date(parts[2], parts[1], parts[0]);
    }

    private void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getCurrentDate() {
        return year + "-" + month + "-" + day;
    }

    public void moveForwardOneWeek() {
        day += 7;
        adjustDate();
    }

    public void moveBackwardOneWeek() {
        day -= 14;
        adjustDate();
    }

    public String dayOfWeekUsingComparison() {
        Date referenceDate = new Date(2020, 11, 30);
        String[] daysOfWeek = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
        int dayIndex = (daysDifference(referenceDate, this) % 7 + 7) % 7;
        return daysOfWeek[dayIndex];
    }

    public String dayOfWeekUsingZeller() {
        int d = day;
        int m = month;
        int y = year;

        if (m < 3) {
            m += 12;
            y -= 1;
        }

        int K = y % 100;
        int J = y / 100;

        int h = (d + (13 * (m + 1)) / 5 + K + K / 4 + J / 4 - 2 * J) % 7;

        if (h < 0)
            h += 7;

        String[] daysOfWeek = {"Sobota", "Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek"};
        return daysOfWeek[h];
    }

    public String formatDate(String format) {
        String[] months = {"stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"};
        String[] daysOfWeek = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
        String[] daysOfWeekShort = {"pon.", "wt.", "śr.", "czw.", "pt.", "sob.", "ndz."};

        String dayOfWeek = dayOfWeekUsingComparison();
        int dayOfWeekIndex = (daysDifference(new Date(2020, 11, 30), this) % 7 + 7) % 7;

        switch (format) {
            case "fullWithDay":
                return dayOfWeek + ", " + day + " " + months[month - 1] + " " + year;
            case "full":
                return day + " " + months[month - 1] + " " + year;
            case "numeric":
                return String.format("%02d.%02d.%04d", day, month, year);
            case "shortWithDay":
                return daysOfWeekShort[dayOfWeekIndex] + ", " + day + "-" + months[month - 1].substring(0, 3) + "-" + year;
            default:
                return getCurrentDate();
        }
    }

    private void adjustDate() {
        int daysInMonth = daysInMonth(month, year);
        while (day > daysInMonth) {
            day -= daysInMonth;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            daysInMonth = daysInMonth(month, year);
        }
        while (day < 1) {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            daysInMonth = daysInMonth(month, year);
            day += daysInMonth;
        }
    }

    private int daysInMonth(int month, int year) {
        int[] daysInMonths = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonths[month - 1];
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private int daysDifference(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(date1.year, date1.month - 1, date1.day);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(date2.year, date2.month - 1, date2.day);
        long millis1 = calendar1.getTimeInMillis();
        long millis2 = calendar2.getTimeInMillis();
        long difference = millis2 - millis1;
        return (int) (difference / (24 * 60 * 60 * 1000));
    }

    @Override
    public int compareTo(Date otherDate) {
        Date referenceDate = new Date(2020, 11, 30);
        int dayDifference = daysDifference(referenceDate, otherDate);
        return dayDifference % 7;
    }
}

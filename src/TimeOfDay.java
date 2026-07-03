public class TimeOfDay implements Comparable<TimeOfDay> {

    //часы (от 0 до 23)
    private int hours;
    //минуты (от 0 до 59)
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay t) {
        if (this.hours != t.hours) {
            return this.hours - t.hours;
        } else if (this.minutes != t.minutes) {
            return this.minutes - t.minutes;
        }
        return 0;
    }
}
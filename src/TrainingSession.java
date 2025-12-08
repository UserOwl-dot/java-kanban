import java.util.Objects;

public class TrainingSession {

    //группа
    private Group group;
    //тренер
    private Coach coach;
    //день недели
    private DayOfWeek dayOfWeek;
    //время начала занятия
    private TimeOfDay timeOfDay;

    public TrainingSession() {
    }

    public TrainingSession(Group group, Coach coach, DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        this.group = group;
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    public Group getGroup() {
        return group;
    }

    public Coach getCoach() {
        return coach;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingSession that = (TrainingSession) o;
        return Objects.equals(getGroup(), that.getGroup()) && Objects.equals(getCoach(), that.getCoach()) && getDayOfWeek() == that.getDayOfWeek() && Objects.equals(getTimeOfDay(), that.getTimeOfDay());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getGroup());
        result = 31 * result + Objects.hashCode(getCoach());
        result = 31 * result + Objects.hashCode(getDayOfWeek());
        result = 31 * result + Objects.hashCode(getTimeOfDay());
        return result;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "group=" + group +
                ", coach=" + coach +
                ", dayOfWeek=" + dayOfWeek +
                ", timeOfDay=" + timeOfDay +
                '}';
    }
}
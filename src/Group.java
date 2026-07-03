import java.util.Objects;

public class Group {
    //название группы
    private String title;
    //тип (взрослая или детская)
    private Age age;
    //длительность (в минутах)
    private int duration;

    public Group(String title, Age age, int duration) {
        this.title = title;
        this.age = age;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public Age getAge() {
        return age;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;
        return getDuration() == group.getDuration() && Objects.equals(getTitle(), group.getTitle()) && getAge() == group.getAge();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getAge());
        result = 31 * result + getDuration();
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "title='" + title + '\'' +
                ", age=" + age +
                ", duration=" + duration +
                '}';
    }
}
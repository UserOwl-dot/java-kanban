public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private final Coach coach;
    private final int counterOfTrainings;
    
    public CounterOfTrainings(Coach coach, int counterOfTrainings) {
        this.coach = coach;
        this.counterOfTrainings = counterOfTrainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterOfTrainings that = (CounterOfTrainings) o;
        return counterOfTrainings == that.counterOfTrainings && coach.equals(that.coach);
    }

    @Override
    public int hashCode() {
        int result = coach.hashCode();
        result = 31 * result + counterOfTrainings;
        return result;
    }

    @Override
    public int compareTo(CounterOfTrainings c) {
        if (this.counterOfTrainings < c.counterOfTrainings) {
            return 1;
        } else if (this.counterOfTrainings > c.counterOfTrainings) {
            return  -1;
        }
        return 0;
    }

    public int getCounterOfTrainings() {
        return counterOfTrainings;
    }

    @Override
    public String toString() {
        return "CounterOfTrainings{" +
                "coach=" + coach.getMiddleName() +
                ", counterOfTrainings=" + counterOfTrainings +
                '}';
    }
}

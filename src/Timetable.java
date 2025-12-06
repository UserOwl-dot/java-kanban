import com.sun.source.tree.Tree;

import java.util.*;
import java.util.concurrent.TransferQueue;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TreeMap<TimeOfDay, TrainingSession> treeMap = timetable.getOrDefault(trainingSession.getDayOfWeek(), new TreeMap<>());
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();
        treeMap.put(time, trainingSession);
        timetable.put(day, treeMap);
        //сохраняем занятие в расписании
    }

    public TreeMap<TimeOfDay, TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, TrainingSession> trainingSessionsForDay = timetable.getOrDefault(dayOfWeek, new TreeMap<>());
        return  trainingSessionsForDay;
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public TrainingSession getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        Map<TimeOfDay, TrainingSession> trainingSessionsForDay = timetable.getOrDefault(dayOfWeek, new TreeMap<>());
        return trainingSessionsForDay.getOrDefault(timeOfDay, null);
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public List<CounterOfTrainings> getCountByCoaches() {

        Map<Coach, Integer> countTrain = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            TreeMap<TimeOfDay, TrainingSession> trainingSessionsForDay = getTrainingSessionsForDay(day);
            for (TrainingSession training : trainingSessionsForDay.values()) {
                Coach coach = training.getCoach();
                countTrain.put(coach, countTrain.getOrDefault(coach, 0) + 1);
            }
        }

        List<CounterOfTrainings> counterOfTrainings = new ArrayList<>();

        for (Map.Entry<Coach, Integer> counter : countTrain.entrySet()) {
            counterOfTrainings.add(new CounterOfTrainings(counter.getKey(), counter.getValue()));
        }

        Comparator<CounterOfTrainings> comparatorCountOfTrainings = new Comparator<CounterOfTrainings>() {
            @Override
            public int compare(CounterOfTrainings t1, CounterOfTrainings t2) {
                if (t1.getCounterOfTrainings() < t2.getCounterOfTrainings()) {
                    return 1;
                } else if (t1.getCounterOfTrainings() > t2.getCounterOfTrainings()) {
                    return  -1;
                }
                return 0;
            }
        };

        counterOfTrainings.sort(comparatorCountOfTrainings);

        return counterOfTrainings;
    }

}

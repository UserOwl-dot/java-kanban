import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.TreeMap;

public class TimetableTest {

    @Test
    public void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Assertions.assertEquals(
                1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size()
        );
        //Проверить, что за понедельник вернулось одно занятие

        Assertions.assertEquals(
                0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size()
        );
        //Проверить, что за вторник не вернулось занятий
    }

    @Test
    public void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        Assertions.assertEquals(
                1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size()
        );
        // Проверить, что за понедельник вернулось одно занятие

        TreeMap<TimeOfDay, TrainingSession> timetableForThursday = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);

        boolean twoClasses = timetableForThursday.firstKey().getHours() == 13 &&
                timetableForThursday.lastKey().getHours() == 20;

        Assertions.assertTrue(twoClasses);
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00

        Assertions.assertEquals(
                0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size()
        );
        // Проверить, что за вторник не вернулось занятий
    }

    @Test
    public void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        TrainingSession expectedNotNull = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        Assertions.assertNotNull(expectedNotNull);
        //Проверить, что за понедельник в 13:00 вернулось одно занятие

        TrainingSession expectedNull = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        Assertions.assertNull(expectedNull);
        //Проверить, что за понедельник в 14:00 не вернулось занятий
    }

    @Test
    public void testGetCountOfTrainingsSort() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Ильич", "Пётр", "Витальевич");
        Coach coach3 = new Coach("Плюшев", "Виктор", "Анатольевич");
        Coach coach4 = new Coach("Прагин", "Дмитрий", "Юрьевич");

        Group groupForChild = new Group("Акробатика для детей", Age.CHILD, 60);
        Group groupForAdult = new Group("Акробатика для взрослых", Age.ADULT, 60);

        TrainingSession mondayChildTrainingSession = new TrainingSession(groupForChild, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupForChild, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayAdultTrainingSession = new TrainingSession(groupForAdult, coach2,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));
        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupForAdult, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(14, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupForAdult, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(14, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupForChild, coach4,
                DayOfWeek.SATURDAY, new TimeOfDay(14, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(saturdayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        int buyCoach1Count = timetable.getCountByCoaches().getFirst().getCounterOfTrainings();
        int expectedCoach1Count = 2;
        Assertions.assertEquals(expectedCoach1Count, buyCoach1Count);
        // Проверить, что у первого в списке кол-во занятий 2

        int buyCoach2Count = timetable.getCountByCoaches().getLast().getCounterOfTrainings();
        int expectedCoach2Count = 1;
        Assertions.assertEquals(expectedCoach2Count, buyCoach2Count);
        // Проверить, что у последнего в списке кол-во занятий 1

        int buySizeCoachesCount = timetable.getCountByCoaches().size();
        int expectedSizeCoachesCount = 4;
        Assertions.assertEquals(expectedSizeCoachesCount, buySizeCoachesCount);
        // Проверить, что кол-во тренеров в списке 4
    }
}

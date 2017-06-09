package habitskeeper.service;

import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;
import habitskeeper.pojo.DayRecord;

public interface HabitService {
	ReturnPojo addUser(User user);

	ReturnPojo addHabit(Habit habit);

	ReturnPojo getHabitList(String userId);

	ReturnPojo addHabitDayRecord(DayRecord dayRecord);

	ReturnPojo getHabitById(Integer habitId);

	ReturnPojo getDayRecordListInMonth(Integer habitId, String yearmonth);
}



package habitskeeper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.DayRecord;

public interface HabitMapper {
	Integer addUser(User user);

	Integer addHabit(Habit habit);

	List<Habit> getHabitList(String userId);

	Integer addHabitDayRecord(DayRecord dayRecord);

	List<DayRecord> getCompleteDayInMonth(@Param("habitId")Integer habitId, @Param("yearMonth")String yearMonth);

	Integer updateHabit(Habit habit);

	DayRecord getHabitDayRecordInDay(@Param("habitId")Integer habitId, @Param("date")String date);

	Habit getHabitById(@Param("habitId")Integer habitId);

	List<DayRecord> getDayRecordListInMonth(@Param("habitId")Integer habitId, @Param("yearMonth")String yearMonth);
	
}

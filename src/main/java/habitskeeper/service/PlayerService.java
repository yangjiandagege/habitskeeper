package habitskeeper.service;

import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;
import habitskeeper.pojo.DayRecord;

public interface PlayerService {
	ReturnPojo updateUser(User user);
}


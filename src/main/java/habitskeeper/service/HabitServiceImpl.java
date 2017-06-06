package habitskeeper.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import habitskeeper.dao.HabitMapper;
import habitskeeper.pojo.DayRecord;
import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;
import habitskeeper.utils.TimeUtils;

@Service("habitService")
public class HabitServiceImpl implements HabitService{
	@Autowired
	private HabitMapper habitMapper;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public static final String RETURN_CODE_SUCCESS = "200";
	public static final String RETURN_CODE_DATA_NULL = "201";
	public static final String RETURN_CODE_OPERATE_DB_FAIL = "202";
	
	@Override
	public ReturnPojo addUser(User user) {
		ReturnPojo rp = new ReturnPojo();
		if(1 == habitMapper.addUser(user)){
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			rp.setReturnMsg("操作成功！");
		}else{
			rp.setReturnCode(RETURN_CODE_OPERATE_DB_FAIL);
			rp.setReturnMsg("数据库操作失败！");
		}
		return rp;
	}

	@Override
	public ReturnPojo addHabit(Habit habit) {
		ReturnPojo rp = new ReturnPojo();
		if(1 == habitMapper.addHabit(habit)){
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			rp.setReturnMsg("操作成功！");
		}else{
			rp.setReturnCode(RETURN_CODE_OPERATE_DB_FAIL);
			rp.setReturnMsg("数据库操作失败！");
		}
		return rp;
	}

	@Override
	public ReturnPojo getHabitList(String userId) {
		ReturnPojo rp = new ReturnPojo();
		List<Habit> habitList = habitMapper.getHabitList(userId);
		if(habitList != null && habitList.size() > 0){
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			rp.setReturnMsg("操作成功！");
			rp.setResult(habitList);
		}else{
			rp.setReturnCode(RETURN_CODE_DATA_NULL);
			rp.setReturnMsg("查询的数据为空！");
		}
		return rp;
	}

	@Override
	public ReturnPojo addHabitDayRecord(DayRecord dayRecord) {
		ReturnPojo rp = new ReturnPojo();
		//插入日记录
		if(1 == habitMapper.addHabitDayRecord(dayRecord)){
			//更新该月目标完成率
			//获取year、month
			String yearMonth = null;
			//根据year、month来获取改月份最大天数
			int maxDayInMonth = TimeUtils.getMaxDayByYearMonth(2017, 5);
			//根据year、month统计该月已完成的天数
			List<DayRecord> dayRecordList = habitMapper.getCompleteDayInMonth(dayRecord.getHabitId(), yearMonth);
			Long curPercent = (long) (dayRecordList.size()/maxDayInMonth);
			habitMapper.updateCurHabitMonthPercent(dayRecord.getHabitId(), curPercent);
			
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			rp.setReturnMsg("操作成功！");
		}else{
			rp.setReturnCode("RETURN_CODE_OPERATE_DB_FAIL");
			rp.setReturnMsg("数据库操作失败！");
		}

		return rp;
	}
	
}

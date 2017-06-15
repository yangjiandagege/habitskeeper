package habitskeeper.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public static final String RETURN_CODE_RECORD_IS_EXIST = "203";
	public static final String RETURN_CODE_OPERATE_IS_NOT_ALLOW = "204";
	
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
		//防止重复插入
		if(habitMapper.getHabitDayRecordInDay(dayRecord.getHabitId(), dayRecord.getDate()) != null){
			rp.setReturnCode(RETURN_CODE_RECORD_IS_EXIST);
			rp.setReturnMsg("数据已经存在！");
			return rp;
		}

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date curDate = new Date();
			Date d1;
			Date d2;
			
			d1 = df.parse(dayRecord.getDate());
			if(d1.getTime() > curDate.getTime()){
				rp.setReturnCode(RETURN_CODE_OPERATE_IS_NOT_ALLOW);
				rp.setReturnMsg("对不起，该操作不被允许操作！");
				return rp;
			}
	
			//插入日记录
			if(1 == habitMapper.addHabitDayRecord(dayRecord)){
				//更新该月目标完成率
				//获取year、month
				String yearMonth = TimeUtils.convertTimeFormat(dayRecord.getDate(), "yyyy-MM-dd", "yyyy-MM");
				int year = Integer.valueOf(TimeUtils.convertTimeFormat(dayRecord.getDate(), "yyyy-MM-dd", "yyyy"));
				int month = Integer.valueOf(TimeUtils.convertTimeFormat(dayRecord.getDate(), "yyyy-MM-dd", "MM"));
				logger.info("yearMonth = "+yearMonth+"  year = "+year+"  month = "+month);
				//根据year、month来获取改月份最大天数
				int maxDayInMonth = TimeUtils.getMaxDayByYearMonth(year, month);
				logger.info("maxDayInMonth = "+maxDayInMonth);
				
				//根据year、month统计该月已完成的天数
				List<DayRecord> dayRecordList = habitMapper.getCompleteDayInMonth(dayRecord.getHabitId(), yearMonth);
				Double curPercent = (double) 0;
				if(0 < dayRecordList.size()){
					curPercent = (double)dayRecordList.size()/(double)maxDayInMonth;
					logger.info("dayRecordList.size() = "+dayRecordList.size());
					logger.info("curPercent = "+curPercent + " maxDayInMonth = "+maxDayInMonth);
				}
				Habit habit = new Habit();
				habit.setHabitId(dayRecord.getHabitId());
				
				if(yearMonth.equals(TimeUtils.getCurrYearMonth())){
					habit.setCurMonthPercent(curPercent); //修改当前完成率
					habitMapper.updateHabit(habit);
				}
				
				Habit myHabit = habitMapper.getHabitById(dayRecord.getHabitId());
				if(myHabit.getLastEditDate() != null){
					d2 = df.parse(myHabit.getLastEditDate());
				}else{
					d2 = curDate;
				}
				logger.info("d2.getTime() = "+d2.getTime() + " d1.getTime() = "+d1.getTime());
				if(d2.getTime() <= d1.getTime()){
					habit.setLastCompleteState(dayRecord.getIsComplete());  //修改当前完成状态
					habit.setLastEditDate(dayRecord.getDate());  //修改最近完成日期
					habitMapper.updateHabit(habit);
				}
				
				rp.setReturnCode(RETURN_CODE_SUCCESS);
				rp.setReturnMsg("操作成功！");
			}else{
				rp.setReturnCode(RETURN_CODE_OPERATE_DB_FAIL);
				rp.setReturnMsg("数据库操作失败！");
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rp;
	}

	@Override
	public ReturnPojo getHabitById(Integer habitId) {
		ReturnPojo rp = new ReturnPojo();
		Habit habit = habitMapper.getHabitById(habitId);
		if(habit != null){
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			rp.setReturnMsg("操作成功！");
			rp.setResult(habit);
		}else{
			rp.setReturnCode(RETURN_CODE_DATA_NULL);
			rp.setReturnMsg("查询的数据为空！");
		}
		return rp;
	}

	@Override
	public ReturnPojo getDayRecordListInMonth(Integer habitId, String yearmonth) {
		ReturnPojo rp = new ReturnPojo();
		List<DayRecord> dayRecordList = habitMapper.getDayRecordListInMonth(habitId, yearmonth);

		if(dayRecordList != null && dayRecordList.size() > 0){
			rp.setReturnCode(RETURN_CODE_SUCCESS);
			int year = Integer.valueOf(TimeUtils.convertTimeFormat(yearmonth, "yyyy-MM", "yyyy"));
			int month = Integer.valueOf(TimeUtils.convertTimeFormat(yearmonth, "yyyy-MM", "MM"));
			
			//根据year、month来获取改月份最大天数
			int maxDayInMonth = TimeUtils.getMaxDayByYearMonth(year, month);
			Double curPercent = (double)dayRecordList.size()/(double)maxDayInMonth;
			logger.info("curPercent = "+curPercent);
			rp.setReturnMsg(""+Math.round(curPercent*100));
			logger.info("returnMsg = "+Math.round(curPercent*100));
			rp.setResult(dayRecordList);
		}else{
			rp.setReturnCode(RETURN_CODE_DATA_NULL);
			rp.setReturnMsg("查询的数据为空！");
		}
		return rp;
	}
	
}

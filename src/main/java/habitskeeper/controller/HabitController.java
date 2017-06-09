package habitskeeper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import habitskeeper.service.HabitService;
import habitskeeper.utils.TimeUtils;
import habitskeeper.pojo.DayRecord;
import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;

@Controller
public class HabitController extends BaseController{
	@Autowired
	private HabitService habitService;
	
    @RequestMapping("")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/adduser")
    public void addUser(User user, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != user
    			&& null != user.getUserId()
    			&& null != user.getAvatarUrl()
    			&& null != user.getCity()
    			&& null != user.getCountry()
    			&& null != user.getGender()
    			&& null != user.getLanguage()
    			&& null != user.getNickName()
    			&& null != user.getProvince()){
    		user.setCreateTime(TimeUtils.getCurrTime());
    		rp = this.habitService.addUser(user);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }
    
    @RequestMapping("/addhabit")
    public void addHabit(Habit habit, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != habit
    			&& null != habit.getUserId()
    			&& null != habit.getHabitName()
    			&& null != habit.getTargetPercent()){
    		habit.setStartDate(TimeUtils.getCurrDate());
    		habit.setCreateTime(TimeUtils.getCurrTime());
    		habit.setCurMonthPercent(Double.valueOf(0));
    		rp = this.habitService.addHabit(habit);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }

    @RequestMapping("/addhabitdayrecord")
    public void addHabitDayRecord(DayRecord dayRecord, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != dayRecord
    			&& null != dayRecord.getHabitId()
    			&& null != dayRecord.getIsComplete()
    			&& null != dayRecord.getDate()
    			&& null != dayRecord.getRemark()){
    		dayRecord.setCreateTime(TimeUtils.getCurrTime());
    		rp = this.habitService.addHabitDayRecord(dayRecord);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }

    @RequestMapping("/gethabitlist")
    public void getHabitList(String userId, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != userId){
    		rp = this.habitService.getHabitList(userId);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }
    
    @RequestMapping("/gethabitbyid")
    public void getHabitById(Integer habitId, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != habitId){
    		rp = this.habitService.getHabitById(habitId);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }
    
    @RequestMapping("/getdayrecordlistinmonth")
    public void getDayRecordListInMonth(Integer habitId, String yearmonth, HttpServletRequest request, HttpServletResponse response) {
    	ReturnPojo rp = new ReturnPojo();
    	if(null != habitId
    			&& null != yearmonth){
    		rp = this.habitService.getDayRecordListInMonth(habitId, yearmonth);
    	}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("参数异常！");
			rp.setResult("fail");
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }
}
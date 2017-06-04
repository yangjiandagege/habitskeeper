package habitskeeper.pojo;

import java.io.Serializable;

public class Habit implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Integer habitId;
	private String 	userId;
	private String 	habitName;
	private String 	startDate;
	private String	endDate;
	private Long 	targetPercent;
	private Long 	curMonthPercent;
	private Integer isCompleteToday;
	private String 	createTime;
	public Integer getHabitId() {
		return habitId;
	}
	public void setHabitId(Integer habitId) {
		this.habitId = habitId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHabitName() {
		return habitName;
	}
	public void setHabitName(String habitName) {
		this.habitName = habitName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getTargetPercent() {
		return targetPercent;
	}
	public void setTargetPercent(Long targetPercent) {
		this.targetPercent = targetPercent;
	}
	public Long getCurMonthPercent() {
		return curMonthPercent;
	}
	public void setCurMonthPercent(Long curMonthPercent) {
		this.curMonthPercent = curMonthPercent;
	}
	public Integer getIsCompleteToday() {
		return isCompleteToday;
	}
	public void setIsCompleteToday(Integer isCompleteToday) {
		this.isCompleteToday = isCompleteToday;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}

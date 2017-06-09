package habitskeeper.pojo;

import java.io.Serializable;

public class Habit implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Integer habitId;
	private String 	userId;
	private String 	habitName;
	private String 	startDate;
	private String	lastEditDate;
	private Integer	lastCompleteState;
	private Double 	targetPercent;
	private Double 	curMonthPercent;
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
	public Double getTargetPercent() {
		return targetPercent;
	}
	public void setTargetPercent(Double targetPercent) {
		this.targetPercent = targetPercent;
	}
	public Double getCurMonthPercent() {
		return curMonthPercent;
	}
	public void setCurMonthPercent(Double curMonthPercent) {
		this.curMonthPercent = curMonthPercent;
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public Integer getLastCompleteState() {
		return lastCompleteState;
	}
	public void setLastCompleteState(Integer lastCompleteState) {
		this.lastCompleteState = lastCompleteState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}

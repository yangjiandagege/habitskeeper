package habitskeeper.pojo;

import java.io.Serializable;

public class MonthRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer monthRecordId;
    private Integer	habitId;
    private String	yearMonth;
    private Double	monthPercent;
    private Double 	targetPercent;
    private String	startDate;
    private String	endDate;
    private String 	createTime;
	public Integer getMonthRecordId() {
		return monthRecordId;
	}
	public void setMonthRecordId(Integer monthRecordId) {
		this.monthRecordId = monthRecordId;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public Double getMonthPercent() {
		return monthPercent;
	}
	public void setMonthPercent(Double monthPercent) {
		this.monthPercent = monthPercent;
	}
	public Double getTargetPercent() {
		return targetPercent;
	}
	public void setTargetPercent(Double targetPercent) {
		this.targetPercent = targetPercent;
	}
	public Integer getHabitId() {
		return habitId;
	}
	public void setHabitId(Integer habitId) {
		this.habitId = habitId;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
    
}

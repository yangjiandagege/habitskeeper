package habitskeeper.pojo;

import java.io.Serializable;

public class DayRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer dayRecordId;
    private Integer	habitId;
    private String	date;
    private Integer isComplete;
    private String	remark;
    private String 	createTime;
    
	public Integer getDayRecordId() {
		return dayRecordId;
	}
	public void setDayRecordId(Integer dayRecordId) {
		this.dayRecordId = dayRecordId;
	}
	public Integer getHabitId() {
		return habitId;
	}
	public void setHabitId(Integer habitId) {
		this.habitId = habitId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
}

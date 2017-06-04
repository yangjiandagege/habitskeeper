package habitskeeper.service;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import habitskeeper.dao.PlayerMapper;
import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;
import habitskeeper.pojo.DayRecord;
import habitskeeper.utils.TimeUtils;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService{
	@Autowired
	private PlayerMapper playerMapper;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	
	@Override
	public ReturnPojo updateUser(User user) {
		ReturnPojo rp = new ReturnPojo();
		user.setCreateTime(TimeUtils.getCurrTime());
		if(1 == playerMapper.updatePlayer(user)){
			rp.setReturnCode("200");
			rp.setReturnMsg("操作成功！");
			rp.setResult("success");
		}else{
			rp.setReturnCode("201");
			rp.setReturnMsg("操作失败！");
			rp.setResult("fail");
		}
		
		return rp;
	}
}

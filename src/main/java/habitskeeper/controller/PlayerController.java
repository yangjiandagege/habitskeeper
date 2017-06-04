package habitskeeper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import habitskeeper.service.PlayerService;
import habitskeeper.pojo.User;
import habitskeeper.pojo.ReturnPojo;

@Controller
public class PlayerController extends BaseController{
	@Autowired
	private PlayerService playerService;
	
    @RequestMapping("")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/updateuser")
    public void updatePlayer(User user, HttpServletRequest request, HttpServletResponse response) {
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
    		rp = this.playerService.updateUser(user);
    	}
        writeJsonToResponse(response, JSONObject.toJSON(rp).toString());
    }
}
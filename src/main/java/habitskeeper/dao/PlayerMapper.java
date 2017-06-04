package habitskeeper.dao;

import java.util.List;

import habitskeeper.pojo.Habit;
import habitskeeper.pojo.User;
import habitskeeper.pojo.DayRecord;

public interface PlayerMapper {
	Integer updatePlayer(User player);
	
	Integer updatePlayerLastGameId(User player);
	
	Integer createGame(Habit game);
	
	Integer getMaxIdFormGame();
	
	Habit getGameById(Integer gameId);
	
	Habit getGameByInviteCode(String inviteCode);
	
	Integer addRole(DayRecord role);

	Integer getMaxIdFormRole();

	Integer updateRole(DayRecord role);
	
	List<DayRecord> getUnallocatedRoleListInGame(Integer gameId);

	List<DayRecord> getRoleListInGame(Integer gameId);
	
	//获得我在本局游戏中的角色
	DayRecord getMyRoleInGame(DayRecord role);
	
	//更新游戏状态
	Integer updateGameState(Habit game);
	
	//获取游戏状态
	Integer getGameState(Integer gameId);
	
	//杀死出局或投票出局
	Integer updateRoleDeathState(DayRecord role);
	
	//获取某一方还活着的角色
	List<DayRecord> getAliveRoleInGameByRoleType(DayRecord role);

	Integer updateGameResult(Habit game);

	Integer updateRoleListVictorySide(DayRecord role);

	User getPlayerById(String playerId);

	List<DayRecord> getMyGameRecords(String playerId);
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Silver extends Entity{

	GamePanel gp;
	
	public static final String objName = "Silver Coin";
	
	public OBJ_Coin_Silver(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 1;
		down1 = setup("/objects/coin_silver",gp.tileSize,gp.tileSize);
		
	}
	public boolean use(Entity entity) {
		
		gp.playSE(1);
		gp.ui.addMessage("+" + value + " Coin");
		gp.player.coin += value;
		return true;
	}
}


package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BattleAxe extends Entity{
	
	public static final String objName = "Battle Axe";

	public OBJ_BattleAxe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = objName;
		down1 = setup("/objects/battle_axe", gp.tileSize, gp.tileSize);
		attackValue = 3;
		attackArea.width = 32;
		attackArea.height = 30;
		description = "[" + name + "]\nThis axe cleaves any\nfoes with thunderous\nmight!\n\n+3 Attack";
		price = 80;
		knockBackPower = 5;
		motion1_duration = 15;
		motion2_duration = 35;
	}

}

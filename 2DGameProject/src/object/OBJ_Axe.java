package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
	public static final String objName = "Woodcutter's Axe";

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = objName;
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 32;
		attackArea.height = 30;
		description = "[" + name + "]\nIt's rusty, but it still\nworks. Can be used to\ncut down dead trees\n\n+2 Attack";
		price = 10;
		knockBackPower = 5;
		motion1_duration = 15;
		motion2_duration = 35;
	}

}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chalice extends Entity{
	
	GamePanel gp;
	public static final String objName = "Chalice";

	public OBJ_Chalice(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		down1 = setup("/objects/chalice", gp.tileSize, gp.tileSize);
		
		setDialogue();
		
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You found the holy grail!";
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
		return true;
	}

}

package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	public Font arial_30, arial_40, arial_80B, maruMonica;
//	BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1: second screen, etc
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	int counter = 0;
	public Entity npc;
	int charIndex = 0;
	String combinedText = "";
	
//	double playTime;
//	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
		try {
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		arial_30 = new Font("Arial", Font.PLAIN, 30);
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
		
		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity crystal = new OBJ_ManaCrystal(gp);
		crystal_full = crystal.image;
		crystal_blank = crystal.image2;
		Entity goldCoin = new OBJ_Coin_Gold(gp);
		coin = goldCoin.down1;
		
	}
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
	}
	public void draw(Graphics2D g2) {
		
			this.g2 = g2;
			
			g2.setFont(maruMonica);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setColor(Color.white);
			
			// TITLE STATE
			
			if (gp.gameState == gp.titleState) {
				drawTitleScreen();
			}
			
			// PLAY STATE		
			if(gp.gameState == gp.playState) {
				drawPlayerLife();
				drawMonsterLife();
				drawMessage();
			}
			
			// PAUSE STATE
			if (gp.gameState == gp.pauseState) {
				drawPlayerLife();
				drawPauseScreen();
			}
			
			// DIALOGUE STATE
			if(gp.gameState == gp.dialogueState) {
				drawDialogueScreen();
			}
			// CHARACTER STATE
			if(gp.gameState == gp.characterState) {
				drawCharacterScreen();
				drawInventory(gp.player,true);
			}
			
			// OPTIONS STATE
			if(gp.gameState == gp.optionsState) {
				drawOptionScreen();
			}
			
			// GAME OVER STATE
			if(gp.gameState == gp.gameOverState) {
				drawGameOverScreen();
			}
			
			// TRANSITION STATE
			if(gp.gameState == gp.transitionState) {
				drawTransition();
			}
			// TRADE STATE
			if(gp.gameState == gp.tradeState) {
				drawTradeScreen();
			}
			// SLEEP STATE
			if(gp.gameState == gp.sleepState) {
				drawSleepScreen();
			}
				
	}
	public void drawPlayerLife() { // AND MANA CRYSTAL
		
		
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/3;
		int i = 0;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET VALUES
		x = gp.tileSize/2;  
		y = gp.tileSize/3;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half,  x,  y,  null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		// DRAW MAX MANA
		x = (gp.tileSize/2) - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.maxMana) {
			g2.drawImage(crystal_blank, x, y, null);
			i++;
			x += 35;
			
		}
		
		// DRAW MANA
		x = (gp.tileSize/2) - 5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(crystal_full, x, y, null);
			i++;
			x += 35;
			
		}
			
	}
	public void drawMonsterLife() {
		
		// MONSTER HP BAR
		
		for(int i = 0; i < gp.monster[1].length; i++) {
			
			Entity monster = gp.monster[gp.currentMap][i];
			
			if(monster != null && monster.inCamera() == true) {
				
				if(monster.hpBarOn == true && monster.boss == false) {
					
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					 hpBarValue = Math.max(0, hpBarValue);
						
					g2.setColor(new Color(35, 35 ,35));
					g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-10, gp.tileSize+2, 9);
						
					g2.setColor(new Color(255,0,30));
					g2.fillRect(monster.getScreenX(), monster.getScreenY() - 9, (int)hpBarValue, 7);		
					
					monster.hpBarCounter++;
					
					if(monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				else if(monster.boss == true) {
					
					double oneScale = (double)gp.tileSize*8/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					
					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.tileSize*12;
					
					 hpBarValue = Math.max(0, hpBarValue);
						
					g2.setColor(new Color(35, 35 ,35));
					g2.fillRect(x-1, y-1, gp.tileSize*8 + 2, 22);
						
					g2.setColor(new Color(255,0,30));
					g2.fillRect(x, y, (int)hpBarValue, 20);	
					
					g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, x + 4, y-10);
				}
			}
		}
	}
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i ++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; // messageCounter
				messageCounter.set(i, counter); // set the counter to the array
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
		
	}
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			// TITLE BACKGROUND COLOUR 
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
			String text = "Arcane Ascendance";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*2;
			
			// TEXT SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			// TEXT COLOUR
			g2.setColor(Color.white);
			g2.drawString(text,  x,  y);
			
		    // MENU
			
			// NEW GAME
		    g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		    text = "NEW GAME";
		    x = getXforCenteredText(text);
		    y += gp.tileSize * 2;
		    g2.drawString(text,  x, y);
		    if(commandNum == 0) {
		    	g2.drawString(">", x-gp.tileSize, y);
		    }
		    
		    // LOAD GAME
		    g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		    text = "LOAD GAME";
		    x = getXforCenteredText(text);
		    y += gp.tileSize;
		    g2.drawString(text,  x, y);
		    if(commandNum == 1) {
		    	g2.drawString(">", x-gp.tileSize, y);
		    }
		    
		    // QUIT
		    g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
		    text = "QUIT";
		    x = getXforCenteredText(text);
		    y += gp.tileSize;
		    g2.drawString(text,  x, y);
		    if(commandNum == 2) {
		    	g2.drawString(">", x-gp.tileSize, y);
		    }
			
			//CHARACTER IMAGE 
			/*
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			*/
			
			// CASTLE IMAGE
			
			x = gp.screenWidth/2 - (gp.tileSize*10)/2;
			y += gp.tileSize/2 - (gp.tileSize * 2)/2.5;
			
		    try {
		        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/castle2.png"));
		        g2.drawImage(image, x, y, gp.tileSize * 10, gp.tileSize * 10, null);  // Adjust the dimensions as needed
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			
		}
		else if(titleScreenState == 1) {
			
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// CONTROLS SCREEN
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(34F));
			
			String text = "Controls";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*2;
			g2.drawString(text,  x,  y);
			
			text = "'WASD' - Move";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text,  x,  y);
			
			text = "'Enter' - Attack/Interact";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "'SPACE' - Guard/Parry";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "'F' - Shoot Projectile";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "'C' - Inventory/Stats";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "'M' - Map";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "'ESC' - Options/Pause";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,  x,  y);
			
			text = "Continue";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text,  x,  y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*1;
			g2.drawString(text,  x,  y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}				
		}    
	}
	public void drawDialogueScreen() {
		
		// WINDOW
		int x = gp.tileSize*3;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*6);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,36));
		x += gp.tileSize;
		y += gp.tileSize;
		
		if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
			
//			currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if(charIndex < characters.length) {
				
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
			}
			
			if(gp.keyH.enterPressed == true) {
				
				charIndex = 0;
				combinedText = "";
				
				if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
					
					npc.dialogueIndex++;
					gp.keyH.enterPressed = false;
				}
			}
		}
		else { // If no text is in the array
			npc.dialogueIndex = 0;
			
			if(gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
			}
			if(gp.gameState == gp.cutsceneState) {
				gp.csManager.scenePhase++;
			}
		}
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}	
	}
	public void drawCharacterScreen() {
		
		// CREATE STAT FRAME
		final int frameX = gp.tileSize * 1;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*7;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// STAT TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Level: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("HP: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Mana: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Strength: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Resistance: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Attack: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Defense: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("EXP: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Next Level EXP: ", textX, textY);
		textY +=lineHeight;
		g2.drawString("Gold: ", textX, textY);
		textY +=lineHeight + 10;
		g2.drawString("Current Weapon: ", textX, textY);
		textY +=lineHeight + 15;
		g2.drawString("Current Shield: ", textX, textY);
		textY +=lineHeight;
		
		// STAT VALUES
		int tailX = (frameX + frameWidth) - 30;
		
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignedToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY +=lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-23, null);
		textY += gp.tileSize;
		
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-23, null);
		textY += gp.tileSize;
	}

	public void drawInventory(Entity entity, boolean cursor) {

		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			
			frameX = gp.tileSize*17;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize*9;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		// INVENTORY FRAME

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// INVENTORY SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize+3;
			
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i < entity.inventory.size(); i++) {
				
			// EQUIP CURSOR
			if(entity.inventory.get(i) == entity.currentWeapon ||
				entity.inventory.get(i) == entity.currentShield ||
				entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
				
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			// DISPLAY AMOUNT
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				
				g2.setFont(g2.getFont().deriveFont(30f));
				int amountX;
				int amountY;
				
				String s ="" + entity.inventory.get(i).amount;
				amountX = getXforAlignedToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				// SHADOW
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				
				// NUMBER
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}
				
			slotX += slotSize;
				
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
			
		// CURSOR
		if(cursor == true) {
			
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
				
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
				
			// ITEM DESCRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*5;
				
				
			// DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
				
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
				
			if(itemIndex < entity.inventory.size()) {
					
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
					
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 32; 
				}
			}
		}			
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0, 0, 220));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120f));
		
		text = "You died!";
		
		// Text Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		// Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Respawn";
		x = getXforCenteredText(text);
		y += gp.tileSize*5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		// Quit/Back to title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}
	public void drawOptionScreen() {
			
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
			
		//SUB WINDOW
		int frameX = gp.tileSize*8;
		int frameY = gp.tileSize*2;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
			
		switch(subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreeNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_endGameConfirmation(frameX, frameY); break;
		}	
		gp.keyH.enterPressed = false;
	}
		
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		// FULL SCREEN ON/OFF
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		
		// MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
		}
		
		// SE VOLUME
		textY += gp.tileSize;
		g2.drawString("Sound Effect", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX-25, textY);
		}
		
		// CONTROL
		textY += gp.tileSize;
		g2.drawString("Controls", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		// END GAME
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// BACK
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
			}
		}
		
		// FULL SCREEN CHECK BOX
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		// MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24); // 120/5 = 24
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SOUND EFFECT VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
	}
	public void options_fullScreeNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "The change will take \neffect after you restart \nthe game.";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// FULL SCREEN BACK
		textY = frameY +gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
		
	}
	public void options_control(int frameX, int frameY) {
		int textX;
		int textY;
		
		// TITLE
		String text = "Controls";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY); textY += gp.tileSize;
		g2.drawString("Interact/Attack", textX, textY); textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
		g2.drawString("Guard/Parry", textX, textY); textY += gp.tileSize;
		g2.drawString("Inventory/Stats", textX, textY); textY += gp.tileSize;
		g2.drawString("Options/Pause", textX, textY); textY += gp.tileSize;
		g2.drawString("Map", textX, textY); textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*6;
		textY = frameY + gp.tileSize*2;
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("F", textX, textY); textY += gp.tileSize;
		g2.drawString("SPACE", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		g2.drawString("M", textX, textY); textY += gp.tileSize;
		
		// BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize*3;
		
		currentDialogue = "Quit game and return to \ntitle screen?";
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		// YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.ui.titleScreenState = 0;
				gp.stopMusic();
				gp.playMusic(10);
				gp.resetGame(true);
			}
		}
		
		// NO
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
	}
	public void drawTransition() {
		
		counter++;
		g2.setColor(new Color(0,0,0,counter*5));
		g2.fillRect(0,  0, gp.screenWidth, gp.screenHeight);
		
		if(counter == 50) { // Transition
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void trade_select() {
		
		npc.dialogueSet = 0;
		drawDialogueScreen();
		
		//DRAW WINDOW
		int x = gp.tileSize * 19;
		int y = gp.tileSize * 4;
		int width = gp.tileSize *3;
		int height = (int)(gp.tileSize*3.5);
		drawSubWindow(x, y, width, height);
		
		// DRAW TEXTS
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-24, y);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				npc.startDialogue(npc, 1);
			}
		}
	}
	public void trade_buy() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, false);
		// DRAW MERCHANT INVENTORY
		drawInventory(npc, true);
		
		// DRAW HINT WINDOW
		int x = gp.tileSize*9;
		int y = gp.tileSize*11;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[ESC] - Back", x+24, y+60);
		
		// PLAYER GOLD WINDOW
		x = gp.tileSize*17;
		y = gp.tileSize*11;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("Your Gold: " + gp.player.coin, x+24, y+60);
		
		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.tileSize*12.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignedToRightText(text, gp.tileSize*14+30);
			g2.drawString(text, x, y+34);
			
			// BUY ITEM
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					npc.startDialogue(npc, 2);
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						npc.startDialogue(npc, 3);
					}
				}
			}
		}
	}
	public void trade_sell() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int width;
		int height;
		
		// DRAW HINT WINDOW
		x = gp.tileSize*9;
		y = gp.tileSize*11;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[ESC] - Back", x+24, y+60);
		
		// PLAYER GOLD WINDOW
		x = gp.tileSize*17;
		y = gp.tileSize*11;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("Your Gold: " + gp.player.coin, x+24, y+60);
		
		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			
			x = (int)(gp.tileSize*20.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignedToRightText(text, gp.tileSize*22+30);
			g2.drawString(text, x, y+34);
			
			// SELL ITEM
			if(gp.keyH.enterPressed == true) {
				
				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 4);
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}	
	}
	public void drawSleepScreen() {
		
		counter++;
		
		if(counter < 180) {
			gp.eManager.lighting.filterAlpha += 0.01f;
			if(gp.eManager.lighting.filterAlpha > 1f) {
				gp.eManager.lighting.filterAlpha = 1f;
			}
		}
		if(counter >= 180) {
			gp.eManager.lighting.filterAlpha -= 0.01f;
			if(gp.eManager.lighting.filterAlpha <= 0f) {
				gp.eManager.lighting.filterAlpha = 0f;
				counter = 0;
				gp.eManager.lighting.dayState = gp.eManager.lighting.day;
				gp.eManager.lighting.dayCounter = 0;
				gp.gameState = gp.playState;
				gp.player.getImage();
			}
		}
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int getXforAlignedToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}

}

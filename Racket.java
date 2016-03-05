import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
	private final static int PADDLE_WIDTH = 10;
	private final static int PADDLE_HEIGHT = 60;
	private final static int PADDLE_SPEED = 5;

	private int playerNum;
	private Color color;
	private int minHeight, maxHeight;
	private int upKey, downKey;
	private int x;
	private int y, ya;
	
	public Racket(int playerNum, int gameHeight, int upKey, int downKey, int x){
		this.playerNum = playerNum;
		this.color = (playerNum == 1 ? Color.ORANGE : Color.BLUE);
		this.minHeight = 0;
		this.maxHeight = gameHeight - PADDLE_HEIGHT - (PADDLE_HEIGHT/2);
		this.upKey = upKey;
		this.downKey = downKey;
		this.x = x;
		this.y = gameHeight / 2;
		this.ya = 0;
	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
	
	public void update() {
		if(goingUpAndBelowTop()
				|| goingDownAndAboveBottom()){
			this.y += this.ya;
		}
	}
	
	private boolean goingUpAndBelowTop(){
		return this.ya == -PADDLE_SPEED && this.y >= this.minHeight;
	}
	
	private boolean goingDownAndAboveBottom(){
		return this.ya == PADDLE_SPEED && this.y <= this.maxHeight;
	}
	 
	public void pressed(int keyCode){
		if(keyCode == this.upKey){
			this.ya = -PADDLE_SPEED;
		} else if(keyCode == this.downKey){
			this.ya = PADDLE_SPEED;
		}
	}
	
	public void released(int keyCode){
		if(keyCode == this.upKey || keyCode == this.downKey){
			this.ya = 0;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
}

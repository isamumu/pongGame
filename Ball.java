import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	private final static int BALL_WIDTH = 30;
	private final static int BALL_HEIGHT = 30;
	private final static int BALL_SPEED = 3;
	
	private Color color;
	private int gameWidth, gameHeight;
	private int minHeight, maxHeight;
	private int x, xa;
	private int y, ya;
	private Racket player1, player2;
	private PongPanel gamePanel;
	
	private Random random = new Random();
	
	public Ball(Color color, int gameWidth, int gameHeight, Racket player1, Racket player2, PongPanel gamePanel){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.color = color;
		this.minHeight = 0;
		this.maxHeight = gameHeight - BALL_HEIGHT  - (BALL_HEIGHT/2);
		this.xa = BALL_SPEED;
		this.ya = BALL_SPEED;
		this.player1 = player1;
		this.player2 = player2;
		this.gamePanel = gamePanel;
		
		resetToMiddle();
	}

	private void resetToMiddle() {
		this.x = this.gameWidth / 2;
		this.xa = -xa;
		this.y = random.nextInt(this.gameHeight - (2 * BALL_HEIGHT) + 1);
	}

	public void update(){
		updateLocation();
		checkCollisionWithSides();
		checkCollisionWithRacquets();
	}

	private void checkCollisionWithRacquets() {
		if(player1.getBounds().intersects(this.getBounds())
				|| player2.getBounds().intersects(this.getBounds())){
			this.xa *= -1;
		}
		
	}

	private void checkCollisionWithSides() {
		if(topOrbottom()){
			this.ya *= -1;
		} else if(pastLeft()){
			gamePanel.increaseScore(player2.getPlayerNum());
			resetToMiddle();
		}
		else if(pastRight()){
			gamePanel.increaseScore(player1.getPlayerNum());
			resetToMiddle();
		}
		
	}

	private boolean pastRight() {
		return this.x + this.BALL_WIDTH > this.gameWidth;
	}

	private boolean pastLeft() {
		return this.x < 0;
	}

	private boolean topOrbottom() {
		return this.y <= this.minHeight || this.y >= this.maxHeight;
	}

	private void updateLocation() {
		this.x += this.xa;
		this.y += this.ya;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, BALL_WIDTH, BALL_HEIGHT);
	}
	
	public void paint(Graphics g) {
		g.fillRect(this.x, this.y, BALL_WIDTH, BALL_HEIGHT);
	}
}

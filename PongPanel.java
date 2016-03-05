import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PongPanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private final static int GAME_SPEED = 5;
	private final static int WINNING_SCORE = 10;
	
	private Pong game;
	private Ball ball;
	private Racket player1, player2;
	private int score1, score2;

	public PongPanel(Pong game) {
		this.game = game;
		
		setBackground(Color.WHITE);
		
		// Create players and ball
		//Ball(Color color, int gameWidth, int gameHeight, Racket player1, Racket player2, PongPanel gamePanel)
		this.player1 = new Racket(1, game.getHeight(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth() - 36);
		this.player2 = new Racket(2, game.getHeight(), KeyEvent.VK_W, KeyEvent.VK_S, 20);
		this.ball = new Ball(Color.MAGENTA, this.game.getWidth(), this.game.getHeight(), this.player1, this.player2, this);
		
		Timer timer = new Timer(GAME_SPEED, this);
		timer.start();
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void increaseScore(int playerNum){
		if(playerNum == this.player1.getPlayerNum()) {
			this.score1++;
		} else if(playerNum == this.player2.getPlayerNum()){
			this.score2++;
		}
		
		repaint();
		
		if(this.score1 == WINNING_SCORE){
			JOptionPane.showMessageDialog(null, "Player 1 won", "Pong", JOptionPane.PLAIN_MESSAGE);
			resetScore();
		} else if(this.score2 == WINNING_SCORE){
			JOptionPane.showMessageDialog(null, "Player 2 won", "Pong", JOptionPane.PLAIN_MESSAGE);
			resetScore();
		}
	}
	
	public Racket getPlayer(int playerNum) {
		if(playerNum == this.player1.getPlayerNum()) {
			return this.player1;
		} else if(playerNum == this.player2.getPlayerNum()){
			return this.player2;
		} else {
			return null;
		}
	}
	
	private void resetScore() {
		this.score1 = 0;
		this.score2 = 0;
		repaint();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.player1.paint(g);
		this.player2.paint(g);
		this.ball.paint(g);
		
		g.drawString(this.score1 + " : " + this.score2, this.game.getWidth() / 2, 20);
	};
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.player1.pressed(e.getKeyCode());
		this.player2.pressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.player1.released(e.getKeyCode());
		this.player2.released(e.getKeyCode());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.player1.update();
		this.player2.update();
		this.ball.update();
		
		repaint();
		
	}

}

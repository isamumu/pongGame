import javax.swing.JFrame;

public class Pong extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private PongPanel panel;
	
	private final static int PLAYING_AREA_WIDTH = 1200;
	private final static int PLAYING_AREA_HEIGHT = 750;
	
	private void start() {
		setSize(PLAYING_AREA_WIDTH, PLAYING_AREA_HEIGHT);
		setTitle("Pong Game");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new PongPanel(this);
		add(this.panel);
		
		setVisible(true);
	}
	
	public PongPanel getPanel() {
		return this.panel;
	}
	
	
	public static void main(String[]args){
		Pong game = new Pong();
		game.start();
	}
	
}

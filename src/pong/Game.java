package pong;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


import javax.swing.*;


public class Game extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240;
	public static int HEIGHT = 180;
	public static int SCALE =4;

	public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static JFrame frame;
	public static Player player;
	public static Inimigo inimigo;
	public static Ball ball;

	public BufferedImage simb_player;
	public BufferedImage simb_inimigo;
	private Spritesheet sheet_jogador;
	private Spritesheet sheet_enemy;
	
	public Game() {
		
		sheet_enemy = new Spritesheet("/cockie 2.png");
		simb_inimigo = sheet_enemy.getSprite(0,0,16,16);
		sheet_jogador = new Spritesheet("/cockie.png");
		
		
		simb_player = sheet_jogador.getSprite(0,0,16,16);
		
		this.setPreferredSize(new Dimension (WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
	
		player = new Player(100,HEIGHT-14,0);
		inimigo = new Inimigo (100, 12,0);
		ball = new Ball (WIDTH/2, HEIGHT/2 -1);
	}
	
	
	public static void main (String [] args) {
		Inicializar();

	}
	public static void Inicializar () {
		JLayeredPane layerjanela = new JLayeredPane();
		layerjanela.setBounds(0,0, WIDTH*SCALE, HEIGHT*SCALE);
		
		frame = new JFrame("Pong");
		Game game = new Game();
	
	        
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
		new Thread(game).start();
		
	}


	public void tick() {
		player.tick();
		inimigo.tick();
		ball.tick();
	}
	
	public void render () {
	
		BufferStrategy bs = this.getBufferStrategy();
	
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = layer.getGraphics();
		g.fillRect(0 ,0 , Game.WIDTH, Game.HEIGHT/2);
		g.setColor(new Color (109, 76, 65));
		
		//g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT/2);
		
		g.setColor(new Color (250, 185, 185));
		g.fillRect(0, HEIGHT/2, WIDTH, HEIGHT);
		player.render(g);
		inimigo.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		g.setFont(new Font("Press Start 2P", Font.BOLD,18));
		
		g.setColor(new Color ( 250, 185, 185));
		g.drawString("Inimigo: "+ Game.inimigo.score, 45, 22);
		g.drawImage(simb_inimigo, 9, 0, 32,32,null);
		
		g.setColor(new Color (109, 76, 65));
		g.drawString("Player: "+ Game.player.score, 45, HEIGHT*SCALE- 10);
		g.drawImage(simb_player, 9, HEIGHT*SCALE - 32, 32,32, null);
		
		
		
		
		
		bs.show();
	}
	
	public void run () {
		while (true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.rigth = true;
		} else if (e.getKeyCode()== KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
//		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			player.up = true;
//		} else if (e.getKeyCode()== KeyEvent.VK_DOWN) {
//			player.down = true;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.rigth = false;
		} else if (e.getKeyCode()== KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if (e.getKeyCode()== KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}
	
	
	
}

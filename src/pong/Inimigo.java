package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Inimigo {
	public boolean rigth, left, up, down;
	public double x,y;
	public int spd = 5;
	public int width;
	public int height;
	public static int score;
	
	public Inimigo (int x, int y, int score) {
		this.x=x;
		this.y=y;
		this.score = score;
		width = 40;
		height = 5;
	}
	public void tick() {
		x += (Game.ball.x - x - 6) * 0.2;

		if (x+width > Game.WIDTH) {
			x=Game.WIDTH - width; 
			
		} else if (x< 0) {
			x=0;
		}
		
//		if (y > 30) {
//			y = 30;
//			
//		} else if (y + height < 0) {
//			y =  height;
//		}
//		
		
	}
	
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		Inimigo.score = score;
	}
	public void render (Graphics g) {
		g.setColor(new Color( 250, 185, 185));
		g.fillRect((int)x ,(int) y , width, height);
		
			}
	
}

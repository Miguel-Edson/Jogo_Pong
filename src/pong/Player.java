package pong;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Graphics;

import javax.swing.JLabel;


public class Player {
	
	public boolean rigth, left, up, down;
	public int x,y;
	public int speed = 3;
	public int width;
	public int height;
	public static int score;
	
	public Player(int x, int y, int score) {
		this.x=x;
		this.y=y;
		this.score = score;
		width = 40;
		height = 5;
		
	}
	public void tick() {
		
		if (x+width > Game.WIDTH) {
			x=Game.WIDTH - width; 
			
		} else if (x< 0) {
			x=0;
		}

		
		if (y < 110) {
			y = 110;
			
		} else if (y + height > Game.HEIGHT) {
			
			y = Game.HEIGHT - height;
		}
		
		if(rigth) {
			x+=speed;
		} else if (left) {
			x-=speed;
		}
		
		if(up) {
			y-=speed;
		} else if (down) {
			y+=speed;
		}
		
		
		
	}
	
	public void render (Graphics g) {
		g.setColor(new Color(109, 76, 65));
		g.fillRect(x , y , width, height);
		
			}
	
		
	
}

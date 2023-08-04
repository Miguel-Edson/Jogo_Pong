package pong;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Ball {
	
	public double dx,dy;
	public double x,y;
	public double spd = 2.2;
	public int width;
	public int height;
	public int r,v,b;
	
	public Ball (int x, int y) {
		this.x=x;
		this.y=y;
		width = 4;
		height = 4;
		Randomizar_Direção();
		
	}
	
	
	private void Randomizar_Direção() {
		
		int angle = new Random().nextInt(360);
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		spd = 2.2;
		Game.player.x= (Game.WIDTH - Game.player.width)/2;
	}


	public void tick() {
		
		if(x+(dx*spd)+width >= Game.WIDTH) {
			dx *= -1;
			spd += 0.3;
			
		}else if (x+(dx*spd) < 0) {
			dx*= -1;
			spd += 0.3;
		}
		
		if(y+(dy*spd)+width >= Game.HEIGHT) {
			this.x = (Game.WIDTH /2) - 3;
			this.y = (Game.HEIGHT /2) -3;
			Randomizar_Direção();
			Inimigo.score ++;
			System.out.println("Score Inimigo: "+ Inimigo.score);
			
		}else if (y+(dy*spd) < 0) {
			this.x = (Game.WIDTH /2) - 3;
			this.y = (Game.HEIGHT /2) -3;
			Randomizar_Direção();
			Player.score ++;
			System.out.println("Score Player: "+ Player.score);
		}
		
		Rectangle bounds = new Rectangle ((int) (x+(dx*spd)),(int)(y+(dy*spd)), width,height);	
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width,Game.player.height);
		Rectangle boundsInimigo = new Rectangle((int)Game.inimigo.x, (int)Game.inimigo.y, Game.inimigo.width,Game.inimigo.height);
		
		if(bounds.intersects(boundsPlayer)) {
			spd += 0.3;
			dy *= -1;
		} else if(bounds.intersects(boundsInimigo)) {
			spd += 0.3;
			dy *=-1;
		}
		
		x += dx*spd;
		y += dy*spd;
		
		if (y>=Game.HEIGHT/2) {
			r= 109;
			v= 76;
			b= 65;
			
		}else if (y<= Game.HEIGHT/2) {
			
			r= 250;
			v= 185;
			b= 185;
			// (109, 76, 65)
		}
		
	}
	
	
	public void render (Graphics g) {
		g.setColor(new Color(r, v, b));
		g.fillRect((int)x ,(int) y , width, height);
	
		
			}
	
	
	
}

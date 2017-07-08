package tarena;

import java.util.Random;

import util.MyImage;

public class Bullet extends FlyingObject{
//	int speed = 3;
	int xSpeed;
	int ySpeed;
	int direct;
	Bullet(int x,int y){
		Random rand = new Random();
		image = MyImage.loadImage("bullet.png");
		width = image.getWidth();
		height = image.getHeight();
		this.x = x - width/2;
		this.y = y;
		xSpeed = rand.nextInt(9);
		ySpeed = rand.nextInt(5)+1;
		direct = rand.nextInt(2);
	}
	
	void step(){
		y = y - ySpeed;
		if(x <= 0){
			direct = 1;
		}else if( x >= 400 - width){
			direct = 0;
		}
		if(direct == 0){
			x = x - xSpeed;
		}else if(direct == 1){
			x = x + xSpeed;
		}
	}
	
//	void step() {
//		y = y - speed;
//	}
	
}

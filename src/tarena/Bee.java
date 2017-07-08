package tarena;

import java.util.Random;

import util.MyImage;

public class Bee extends FlyingObject{
	int xSpeed;
	int ySpeed;
	int direct;//0×ó1ÓÒ
	Bee(){
		Random rand = new Random();
		image = MyImage.loadImage("bee.png");
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		x = rand.nextInt( 400-width );
		xSpeed = rand.nextInt(10) + 1;
		ySpeed = rand.nextInt(5) + 1;
		direct = rand.nextInt(2);
	}
	
	void step(){
		y = y + ySpeed;
		
		if(x <= 0){
			direct = 1;
		}else if( x >=400-width ){
			direct = 0;
		}
		
		if(direct == 0){//×ó
			x = x - xSpeed;
		}else if(direct == 1){//ÓÒ
			x = x + xSpeed;
		}
	}
	
}

package tarena;

import java.util.Random;

import util.MyImage;


public class Airplane extends FlyingObject{
	
	int speed = 3;
	Airplane(){
		Random rand = new Random();
		image = MyImage.loadImage("airplane.png");
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		x = rand.nextInt( 400-width );
	}
	
	void step(){
		y = y + speed;
	}
	
}

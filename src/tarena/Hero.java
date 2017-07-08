package tarena;

import java.util.ArrayList;
import java.util.List;

import util.MyImage;
public class Hero extends FlyingObject{
	/*
	 * 20 1倍火力
	 * 40 2倍火力
	 * 60 3倍火力
	 * 80 4倍火力
	 * ......
	 * 120 5倍火力
	 */
	int fire = 100;
	
	List<Bullet> biubiu(){
		List<Bullet> bullets = new ArrayList<Bullet>();
		int num = fire / 20;//获取子弹颗数
		num = num < 5 ? num:5;//限制最大5颗子弹
		int w = width / (num + 1);//得到每段机身宽
		for(int i=0;i<num;i++){
			int bx = x + w*(i+1);
			int by = y - 20;
			//创建一颗子弹
			Bullet b = new Bullet(bx,by);
			bullets.add(b);
		}
		return bullets;
	}
	
	Hero(){
		x = 150;
		y = 400;
		image = MyImage.loadImage("hero0.png");
		width = image.getWidth();
		height = image.getHeight();
	}

	
	
	void step() {
		
	}
}

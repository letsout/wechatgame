package tarena;

import java.util.ArrayList;
import java.util.List;

import util.MyImage;
public class Hero extends FlyingObject{
	/*
	 * 20 1������
	 * 40 2������
	 * 60 3������
	 * 80 4������
	 * ......
	 * 120 5������
	 */
	int fire = 100;
	
	List<Bullet> biubiu(){
		List<Bullet> bullets = new ArrayList<Bullet>();
		int num = fire / 20;//��ȡ�ӵ�����
		num = num < 5 ? num:5;//�������5���ӵ�
		int w = width / (num + 1);//�õ�ÿ�λ����
		for(int i=0;i<num;i++){
			int bx = x + w*(i+1);
			int by = y - 20;
			//����һ���ӵ�
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

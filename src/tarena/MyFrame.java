package tarena;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.MyImage;

class MyPanel extends JPanel {
	Hero hero = new Hero();// 面板上的英雄机
	//Airplane air = new Airplane();// 面板上的一架小飞机
	List<FlyingObject> flyings = new ArrayList<FlyingObject>();//面板上敌机集合
	List<Bullet> all = new ArrayList<Bullet>();//面板上子弹总和
	MyPanel() {
		// 初始化鼠标监听
		class MyMouseAdapter extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				status = 1;// 0->1 开始游戏
			}

			public void mouseMoved(MouseEvent e) {
				if (status == 1) {
					// 让英雄机跟着鼠标动
					int x = e.getX();
					int y = e.getY();
					hero.x = x - hero.width / 2;
					hero.y = y - hero.height / 2;
				}
			}
		}
		MyMouseAdapter adapter = new MyMouseAdapter();
		addMouseListener(adapter);// 添加鼠标点击监听
		addMouseMotionListener(adapter);// 添加鼠标滑动监听
		// 初始化定时器
		Timer timer = new Timer();
		class MyTimerTask extends TimerTask {
			Random rand = new Random();
			void addFlying(){
				//小飞机:小蜜蜂 19:1 思路:产生0-20的随机数 0创建蜜蜂 1-19之间创建小飞机
				int type = rand.nextInt(21);
				switch( type ){
					case 0:
						Bee bee = new Bee();
						flyings.add(bee);
						;break;
					default:
						Airplane air = new Airplane();
						flyings.add(air);
						;break;
				}
			}
			void step(){
				//敌机走步
				for(int i=0;i<flyings.size();i++){
					FlyingObject flying = flyings.get(i);
					flying.step();
				}
				//子弹走步
				for(int i=0;i<all.size();i++){
					Bullet b = all.get(i);
					b.step();
				}
			}
			
			int index = 0;
			public void run() {
				if (status == 1) {
					if(index % 30 ==0){
						addFlying();
					}
					step();
					List<Bullet> bs = hero.biubiu();
					all.addAll(bs);
					index ++;
				}
				repaint();// 重绘
			}
		}
		MyTimerTask task = new MyTimerTask();
		timer.schedule(task, 10, 10);
	}

	void drawBackground(Graphics g) {
		BufferedImage image = MyImage.loadImage("background.png");
		g.drawImage(image, 0, 0, null);
	}

	void drawScoreAndLife(Graphics g) {
		g.drawString("SCORE:0", 10, 20);
		g.drawString("LIFE:3", 10, 35);
	}

	// 绘制敌机
	void drawFlying(Graphics g) {
		//绘制敌机
		for(int i=0;i<flyings.size();i++){//循环遍历小飞机
			FlyingObject flying = flyings.get( i );//取出小飞机集合中的每一个小飞机
			g.drawImage(flying.image, flying.x, flying.y, null);//绘制每一架小飞机
		}
		//绘制子弹
		for(int i=0;i<all.size();i++){//循环遍历子弹
			Bullet b = all.get(i);//取出子弹集合中的每一颗子弹
			g.drawImage(b.image, b.x, b.y, null);//绘制每一颗子弹
		}
	}

	void drawHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	int status = 0;// 游戏状态值 0 ready 1 running 2 pause 3 gameover

	void drawState(Graphics g) {
		switch (status) {
		case 0:
			BufferedImage start = MyImage.loadImage("start.png");
			g.drawImage(start, 0, 0, null);
			;
			break;
		case 2:
			BufferedImage pause = MyImage.loadImage("pause.png");
			g.drawImage(pause, 0, 0, null);
			;
			break;
		case 3:
			BufferedImage gameover = MyImage.loadImage("gameover.png");
			g.drawImage(gameover, 0, 0, null);
			;
			break;
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawBackground(g);
		drawScoreAndLife(g);
		drawHero(g);
		drawFlying(g);
		drawState(g);
	}
}

public class MyFrame {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JPanel p = new MyPanel();
		f.add(p);
		f.setSize(400, 654);
		f.setLocationRelativeTo(null);
		f.setTitle("飞机大战");
		f.setDefaultCloseOperation(3);
		f.setResizable(false);
		f.setVisible(true);
	}
}

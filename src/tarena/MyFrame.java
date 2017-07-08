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
	Hero hero = new Hero();// ����ϵ�Ӣ�ۻ�
	//Airplane air = new Airplane();// ����ϵ�һ��С�ɻ�
	List<FlyingObject> flyings = new ArrayList<FlyingObject>();//����ϵл�����
	List<Bullet> all = new ArrayList<Bullet>();//������ӵ��ܺ�
	MyPanel() {
		// ��ʼ��������
		class MyMouseAdapter extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				status = 1;// 0->1 ��ʼ��Ϸ
			}

			public void mouseMoved(MouseEvent e) {
				if (status == 1) {
					// ��Ӣ�ۻ�������궯
					int x = e.getX();
					int y = e.getY();
					hero.x = x - hero.width / 2;
					hero.y = y - hero.height / 2;
				}
			}
		}
		MyMouseAdapter adapter = new MyMouseAdapter();
		addMouseListener(adapter);// ������������
		addMouseMotionListener(adapter);// �����껬������
		// ��ʼ����ʱ��
		Timer timer = new Timer();
		class MyTimerTask extends TimerTask {
			Random rand = new Random();
			void addFlying(){
				//С�ɻ�:С�۷� 19:1 ˼·:����0-20������� 0�����۷� 1-19֮�䴴��С�ɻ�
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
				//�л��߲�
				for(int i=0;i<flyings.size();i++){
					FlyingObject flying = flyings.get(i);
					flying.step();
				}
				//�ӵ��߲�
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
				repaint();// �ػ�
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

	// ���Ƶл�
	void drawFlying(Graphics g) {
		//���Ƶл�
		for(int i=0;i<flyings.size();i++){//ѭ������С�ɻ�
			FlyingObject flying = flyings.get( i );//ȡ��С�ɻ������е�ÿһ��С�ɻ�
			g.drawImage(flying.image, flying.x, flying.y, null);//����ÿһ��С�ɻ�
		}
		//�����ӵ�
		for(int i=0;i<all.size();i++){//ѭ�������ӵ�
			Bullet b = all.get(i);//ȡ���ӵ������е�ÿһ���ӵ�
			g.drawImage(b.image, b.x, b.y, null);//����ÿһ���ӵ�
		}
	}

	void drawHero(Graphics g) {
		g.drawImage(hero.image, hero.x, hero.y, null);
	}

	int status = 0;// ��Ϸ״ֵ̬ 0 ready 1 running 2 pause 3 gameover

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
		f.setTitle("�ɻ���ս");
		f.setDefaultCloseOperation(3);
		f.setResizable(false);
		f.setVisible(true);
	}
}

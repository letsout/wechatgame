package tarena;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	int x;//������
	int y;//������
	int width;//��
	int height;//��
	BufferedImage image;//ͼƬ
	
	abstract void step();
	
}

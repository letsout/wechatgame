package tarena;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	int x;//ºá×ø±ê
	int y;//×Ý×ø±ê
	int width;//¿í
	int height;//¸ß
	BufferedImage image;//Í¼Æ¬
	
	abstract void step();
	
}

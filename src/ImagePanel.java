import java.awt.*;
import javax.swing.*;
/*Victor Dang and Jinni Xia
 * 27 May 2015
 * Used to display an image (on the homescreen of the game)
 */

public class ImagePanel extends JPanel {
	
	private ImageIcon image;
	
	public ImagePanel ( Color backColor , ImageIcon i){
		setBackground ( backColor);
		image = i;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x = (getWidth() - image.getIconWidth()) / 2;
		int y = (getHeight() - image.getIconHeight()) / 2;
		image.paintIcon(this, g, x, y);
	}
	
}
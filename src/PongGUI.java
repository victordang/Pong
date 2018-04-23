import java.awt.*;
import javax.swing.JFrame;

/*Victor Dang and Jinni Xia
 * 27 May 2015
 * Opens the actual Pong game in a new window to start game play (though blends into the home screen)
 */

public class PongGUI{
	
	//constructor
	public PongGUI(){
	}
	
	//method
    public void play()  {
        JFrame frame = new JFrame("Pong");
        frame.setLayout(new BorderLayout());

        PongPanel pongPanel = new PongPanel();
        frame.add(pongPanel, BorderLayout.CENTER);

        frame.setUndecorated(true);
		frame.setSize(500,500); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

    }
}
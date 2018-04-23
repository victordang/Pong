import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

/*Victor Dang and Jinni Xia
 * 27 May 2015
 * The tester that plays the game, also sets up the home screen with buttons for more options and music options
 */

public class Pong {
	
	//instance variables
	private static JButton pong = new JButton("PLAY");
	private static JButton creds = new JButton ("CREDITS");
	private static JButton about = new JButton ("ABOUT");
	private static JButton quit = new JButton ("QUIT");
	private static JButton nomusic=new JButton("NO MUSIC");
	private static JButton darude=new JButton("DARUDE");
	private static JButton anaconda=new JButton ("ANACONDA");
	private static JButton gangnam=new JButton("GANGNAM STYLE");
	private static AudioInputStream audioStream;
	private static Clip audioClip;
	
	public static void main(String[] args) {
		//displaying window
		JFrame frame = new JFrame ("PONG by Jinni Xia & Victor Dang ");
		
        frame.setUndecorated(true);
        
        
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
	
		//create image
		ImageIcon image = new ImageIcon ("pong3.gif");
	    ImagePanel panel = new ImagePanel (Color.black , image);
		Container container = frame.getContentPane();
		container.add(panel);
		init();
	
		File audioFile = new File("darude1.wav");
        try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
        
		//creating buttons for interface
				
				panel.add(pong);
				panel.add(creds);
				panel.add(about);
				panel.add(quit);
				panel.add(nomusic);
				panel.add(darude);
				panel.add(anaconda);
				panel.add(gangnam);
				frame.add(panel);
				frame.setVisible(true);
				frame.setResizable(true);

				
	} // end of main
	
	//methods
	//what do the buttons do when they're pressed 
	private static void init (){
		nomusic.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					audioStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				audioClip.close();
			}
		}
		);
		
		gangnam.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == gangnam) {
					try {
						audioStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					audioClip.close();
					File audioFile = new File("gangnam.wav");
			        try {
						audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
			            DataLine.Info info = new DataLine.Info(Clip.class, format);
			            audioClip = (Clip) AudioSystem.getLine(info);
			            audioClip.open(audioStream);
			            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			            gainControl.setValue(-5.0f);
			            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			            audioClip.start();
					} catch (UnsupportedAudioFileException d) {
						d.printStackTrace();
					} catch (IOException d) {
						d.printStackTrace();
					} catch (LineUnavailableException d) {
						d.printStackTrace();
					}
				}
			}
		}
		);
		
		darude.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == darude) {
					try {
						audioStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					audioClip.close();
					File audioFile = new File("darude1.wav");
			        try {
						audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
			            DataLine.Info info = new DataLine.Info(Clip.class, format);
			            audioClip = (Clip) AudioSystem.getLine(info);
			            audioClip.open(audioStream);
			            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			            gainControl.setValue(-13.0f);
			            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			            audioClip.start();
					} catch (UnsupportedAudioFileException d) {
						d.printStackTrace();
					} catch (IOException d) {
						d.printStackTrace();
					} catch (LineUnavailableException d) {
						d.printStackTrace();
					}
				}
			}
		}
		);
		
		anaconda.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == anaconda) {
					try {
						audioStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					audioClip.close();
					File audioFile = new File("anaconda.wav");
			        try {
						audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
			            DataLine.Info info = new DataLine.Info(Clip.class, format);
			            audioClip = (Clip) AudioSystem.getLine(info);
			            audioClip.open(audioStream);
			            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			            gainControl.setValue(-10.0f);
			            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			            audioClip.start();
					} catch (UnsupportedAudioFileException d) {
						d.printStackTrace();
					} catch (IOException d) {
						d.printStackTrace();
					} catch (LineUnavailableException d) {
						d.printStackTrace();
					}
				}
			}
		}
		);
		
		creds.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == creds) {
					JOptionPane.showMessageDialog(null, 
							  "Victor Dang - GUI & main gameplay \n "
							+ "Jinni Xia - Audio & polishing to make your gameplay experience the best & fixing all of Victor's errors");
				}
			}
		}
		);
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == about) {
					JOptionPane.showMessageDialog(null, "This is a remake of the original Atari game PONG. First player to 5 points wins!"
							+ "\n Player 1 uses W to move up, S to move down. \n Player 2 uses UP arrow key to move up and DOWN arrow key to move down."
							+ "\n Tip: Choose no music if your computer is slow.");
				}
			}
		}
		);
		
		quit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == quit) {
				    System.exit(0);
				}
			}
		}
		);
	
	
		
		pong.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == pong)
				{
					PongGUI pong = new PongGUI();
					pong.play();
				}
			}
		}
		);
	} // end of init
	
} // end of class
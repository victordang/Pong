import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

/*Victor Dang and Jinni Xia
 * 27 May 2015
 * The actual movements and actions behind the Pong game, moves the ball and paddles
 */

public class PongPanel extends JPanel implements ActionListener, KeyListener{

    private boolean playing = true;
    private boolean gameOver = false;
    private boolean pressUp = false;
    private boolean pressDown = false;
    private boolean pressW = false;
    private boolean pressS = false;
    
    private int ballX = 250;
    private int ballY = 250;
    private int diameter = 20;
    private int ballChangeX = -1;
    private int ballChangeY = 3;
    
    private int player1X = 25;
    private int player1Y = 250;
    private int p1Width = 10;
    private int p1Height = 50;
    
    private int player2X = 465;
    private int player2Y = 250;
    private int p2Width = 10;
    private int p2Height = 50;
    
    private int paddleSpeed = 8;
    private int p1Score = 0;
    private int p2Score = 0;


    //constructor
    public PongPanel(){
        setBackground(Color.BLACK);

        //listens to key presses
        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(1100/65, this);
        timer.start();
        
    }
    
    //methods
    public void actionPerformed(ActionEvent e){
        step();
    }

    //moves players
    public void step(){

        if(playing){
            //moves player 1
        	if (pressW) {
                if (player1Y-paddleSpeed > 0) {
                    player1Y -= paddleSpeed;
                }
            }
            if (pressS) {
                if (player1Y + paddleSpeed + p1Height < getHeight()) {
                    player1Y += paddleSpeed;
                }
            }
            
            //moves player 2
            if (pressUp) {
                if (player2Y-paddleSpeed > 0) {
                    player2Y -= paddleSpeed;
                }
            }
            if (pressDown) {
                if (player2Y + paddleSpeed + p2Height < getHeight()) {
                    player2Y += paddleSpeed;
                }
            }
            
            
            //ball position after moving
            int nextBallLeft = ballX + ballChangeX;
            int nextBallRight = ballX + diameter + ballChangeX;
            int nextBallTop = ballY + ballChangeY;
            int nextBallBottom = ballY + diameter + ballChangeY;

            int playerOneRight = player1X + p1Width;
            int playerOneTop = player1Y;
            int playerOneBottom = player1Y + p1Height;

            float playerTwoLeft = player2X;
            float playerTwoTop = player2Y;
            float playerTwoBottom = player2Y + p2Height;

            //if the ball bounces off top/bottom of screen
            if (nextBallTop < 0 || nextBallBottom > getHeight()) {
                ballChangeY *= -1;
            }

            //if the ball is about to hit the left side
            if (nextBallLeft < playerOneRight) { 
                //if it misses the paddle
                if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
                	File audioFile = new File("score.wav");
                    try {
						AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
	                    DataLine.Info info = new DataLine.Info(Clip.class, format);
	                    Clip audioClip = (Clip) AudioSystem.getLine(info);
	                    audioClip.open(audioStream);
	                    audioClip.start();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
                	ballChangeX=-1;
                    p2Score ++;

                    if (p2Score == 5) {
                        playing = false;
                        gameOver = true;
                    }

                    ballX = 250;
                    ballY = 250;
                    
                }//end of missing paddle if 
                
                else {
                    ballChangeX *= -1;
                    ballChangeX+=1;
                    File audioFile = new File("pongnoise.wav");
                    try {
						AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
	                    DataLine.Info info = new DataLine.Info(Clip.class, format);
	                    Clip audioClip = (Clip) AudioSystem.getLine(info);
	                    audioClip.open(audioStream);
	                    audioClip.start();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
                    
                }//end of else
            }

            //if the ball is going to the right side
            if (nextBallRight > playerTwoLeft) {
                if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
                	File audioFile = new File("score.wav");
                    try {
						AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
	                    DataLine.Info info = new DataLine.Info(Clip.class, format);
	                    Clip audioClip = (Clip) AudioSystem.getLine(info);
	                    audioClip.open(audioStream);
	                    audioClip.start();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
                	ballChangeX=-1;

                    p1Score ++;

                    if (p1Score == 5) {
                        playing = false;
                        gameOver = true;
                    }

                    ballX = 250;
                    ballY = 250;
                }
                else {
                    ballChangeX *= -1;
                    File audioFile = new File("pongnoise.wav");
                    try {
						AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
						AudioFormat format = audioStream.getFormat();
	                    DataLine.Info info = new DataLine.Info(Clip.class, format);
	                    Clip audioClip = (Clip) AudioSystem.getLine(info);
	                    audioClip.open(audioStream);
	                    audioClip.start();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
                }
            }

            //move the ball
            ballX += ballChangeX;
            ballY += ballChangeY;
        }

        	repaint();
    }//end of ball moves
    
    //painting game screen
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        
         if (playing) {
        	 
            int playerOneRight = player1X + p1Width;
            int playerTwoLeft =  player2X;

            g.drawLine(0, 0, 2000, 0);

            //center line
           for (int lineY = 0; lineY < getHeight(); lineY += 75) {
                g.drawLine(250, lineY, 250, lineY+25);
            }
           
            
            //draw the scores
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
            g.drawString(String.valueOf(p1Score), 100, 100);
            g.drawString(String.valueOf(p2Score), 400, 100);

            //draw the ball
            g.fillOval(ballX, ballY, diameter, diameter);

            //draw the paddles
           
            g.fillRect(player1X, player1Y, p1Width, p1Height);
            g.fillRect(player2X, player2Y, p2Width, p2Height);
            
            g.drawLine(0 , 493 ,2000 , 493);

        }
         
        else if (gameOver) {

            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
            g.drawString(String.valueOf(p1Score), 100, 100);
            g.drawString(String.valueOf(p2Score), 400, 100);

            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
            if (p1Score > p2Score) {
                g.drawString("Player 1 Wins!", 135, 200);
            }
            else {
                g.drawString("Player 2 Wins!", 135, 200);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Space to restart.", 200, 400);
            
            ballChangeX=-1;
        }
    }

//what happens if certain keys are pressed

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {

        if(playing){
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                pressUp = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                pressDown = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                pressW = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                pressS = true;
            }
        }
        else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                playing = true;
                player1Y = 250;
                player2Y = 250;
                ballX = 250;
                ballY = 250;
                p1Score = 0;
                p2Score = 0;
            }
            
        }
    }

    public void keyReleased(KeyEvent e) {
        if (playing) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                pressUp = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                pressDown = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_W) {
                pressW = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_S) {
                pressS = false;
            }
        }
    }

}//end of class

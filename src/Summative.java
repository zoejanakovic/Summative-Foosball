
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author janaz9178
 */
public class Summative extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    //Title of the window
    String title = "My Game";
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // YOUR GAME VARIABLES WOULD GO HERE
    //construct ball
    Rectangle ball = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
    //construct each teams lines of players
    Rectangle player1Goalie1 = new Rectangle(0, HEIGHT / 3 - 20, 40, 40);
    Rectangle player1Goalie2 = new Rectangle(0, 2 * HEIGHT / 3 - 20, 40, 40);
    Rectangle player2Forward1 = new Rectangle(WIDTH / 5 - 20, HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Forward2 = new Rectangle(WIDTH / 5 - 20, 2 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Forward3 = new Rectangle(WIDTH / 5 - 20, 3 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Defence1 = new Rectangle(2 * WIDTH / 5 - 20, HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Defence2 = new Rectangle(2 * WIDTH / 5 - 20, 2 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Defence3 = new Rectangle(2 * WIDTH / 5 - 20, 3 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Defence1 = new Rectangle(3 * WIDTH / 5 - 20, HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Defence2 = new Rectangle(3 * WIDTH / 5 - 20, 2 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Defence3 = new Rectangle(3 * WIDTH / 5 - 20, 3 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Forward1 = new Rectangle(4 * WIDTH / 5 - 20, HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Forward2 = new Rectangle(4 * WIDTH / 5 - 20, 2 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player1Forward3 = new Rectangle(4 * WIDTH / 5 - 20, 3 * HEIGHT / 4 - 20, 40, 40);
    Rectangle player2Goalie1 = new Rectangle(WIDTH - 40, HEIGHT / 3 - 20, 40, 40);
    Rectangle player2Goalie2 = new Rectangle(WIDTH - 40, 2 * HEIGHT / 3 - 20, 40, 40);
    //ball velocity
    int velocityX = 4;
    int velocityY = 4;
    //player 1 goalie movement
    boolean aPressed;
    boolean zPressed;
    //player 1 defence movement
    boolean sPressed;
    boolean xPressed;
    //player 1 forward movement
    boolean dPressed;
    boolean cPressed;
    //player 2 goalie movement
    boolean kPressed;
    boolean mPressed;
    //player 2 defence movement
    boolean jPressed;
    boolean nPressed;
    //player 2 forward
    boolean hPressed;
    boolean bPressed;
    //player's score board
    int player1Score = 0;
    int player2Score = 0;
    //create font
    Font myFont = new Font("Comic Sans", Font.BOLD, 100);
    //grass color
    Color grass = new Color(41, 196, 72);
    //countdown variables
    int countNum = 4;
    int numX = WIDTH / 2 - 20;
    int numY = HEIGHT / 2 + 40;
    int fontSize = 80;
    int r = 0;
    Font countFont = new Font("Comic Sans", Font.BOLD, fontSize);
    long textDelay = 2000;
    long nextSwitch = System.currentTimeMillis() + textDelay;
    //start prompt/screen variables
    boolean spacePressed;
    Font nameFont = new Font("Times New Roman", Font.BOLD, 120);
    Font startFont = new Font("Comic Sans", Font.BOLD, 50);
    BufferedImage startBackground;
    //gameRun variable to tell when the countdown to start once the start screen is gone
    int gameRun = 0;

    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public Summative() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();

        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE

        //background of the game
        //start prompt
        if (spacePressed == false) {
            g.drawImage(startBackground, 0, 0, WIDTH, HEIGHT, null);
            g.setColor(grass);
            g.setFont(nameFont);
            g.drawString("FOOSBALL:", 50, HEIGHT / 3);
            g.drawString("1 VS 1", 250, HEIGHT / 2 + 30);
            if (System.currentTimeMillis() >= nextSwitch) {
                g.setColor(Color.WHITE);
                g.setFont(startFont);
                g.drawString("Press The Space Bar To Begin", 30, 2 * HEIGHT / 3);
            }
        } else {
            g.setColor(grass);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.GRAY);
            g.fillRect(15, 0, 10, HEIGHT);
            g.fillRect(WIDTH / 5 - 5, 0, 10, HEIGHT);
            g.fillRect(2 * WIDTH / 5 - 5, 0, 10, HEIGHT);
            g.fillRect(3 * WIDTH / 5 - 5, 0, 10, HEIGHT);
            g.fillRect(4 * WIDTH / 5 - 5, 0, 10, HEIGHT);
            g.fillRect(5 * WIDTH / 5 - 25, 0, 10, HEIGHT);

            //draw centre line and centre circle
            g.setColor(Color.WHITE);
            g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
            g.drawOval(WIDTH / 2 - 110, HEIGHT / 3 - 10, 220, 220);

            //draw creases for both nets
            g.drawRect(0, HEIGHT / 3, WIDTH / 8, HEIGHT / 3);
            g.drawRect(7 * (WIDTH / 8), HEIGHT / 3, WIDTH / 8, HEIGHT / 3);

            //draw player1 players
            g.setColor(Color.red);
            g.fillRect(player1Goalie1.x, player1Goalie1.y, player1Goalie1.width, player1Goalie1.height);
            g.fillRect(player1Goalie2.x, player1Goalie2.y, player1Goalie2.width, player1Goalie2.height);
            g.fillRect(player1Defence1.x, player1Defence1.y, player1Defence1.width, player1Defence1.height);
            g.fillRect(player1Defence2.x, player1Defence2.y, player1Defence2.width, player1Defence2.height);
            g.fillRect(player1Defence3.x, player1Defence3.y, player1Defence3.width, player1Defence3.height);
            g.fillRect(player1Forward1.x, player1Forward1.y, player1Forward1.width, player1Forward1.height);
            g.fillRect(player1Forward2.x, player1Forward2.y, player1Forward2.width, player1Forward2.height);
            g.fillRect(player1Forward3.x, player1Forward3.y, player1Forward3.width, player1Forward3.height);

            //draw player2 players
            g.setColor(Color.BLUE);
            g.fillRect(player2Goalie1.x, player2Goalie1.y, player2Goalie1.width, player2Goalie1.height);
            g.fillRect(player2Goalie2.x, player2Goalie2.y, player2Goalie2.width, player2Goalie2.height);
            g.fillRect(player2Defence1.x, player2Defence1.y, player2Defence1.width, player2Defence1.height);
            g.fillRect(player2Defence2.x, player2Defence2.y, player2Defence2.width, player2Defence2.height);
            g.fillRect(player2Defence3.x, player2Defence3.y, player2Defence3.width, player2Defence3.height);
            g.fillRect(player2Forward1.x, player2Forward1.y, player2Forward1.width, player2Forward1.height);
            g.fillRect(player2Forward2.x, player2Forward2.y, player2Forward2.width, player2Forward2.height);
            g.fillRect(player2Forward3.x, player2Forward3.y, player2Forward3.width, player2Forward3.height);

            //draw ball
            g.setColor(Color.WHITE);
            g.fillRect(ball.x, ball.y, ball.width, ball.height);

            //put a score board with each player's score on their side
            g.setFont(myFont);
            g.setColor(Color.RED);
            g.drawString("" + player1Score, WIDTH / 2 - 185, 100);
            g.setColor(Color.BLUE);
            g.drawString("" + player2Score, WIDTH / 2 + 135, 100);

            //countdown numbers
            if (countNum > 0 && gameRun == 1) {
                g.setColor(Color.BLACK);
                g.setFont(countFont);
                g.drawString("" + countNum, numX, numY);
            }


        }
        if (player1Score == 10) {
            g.setColor(Color.BLACK);
            g.setFont(myFont);
            g.drawString("Red Team Wins!", 75, HEIGHT / 2);
        }
        if (player2Score == 10) {
            g.drawString("Blue Team Wins!", 75, HEIGHT / 2);
        }
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
        startBackground = loadImage("images/fooseball_01.jpg");
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            if (spacePressed == false) {
                gameRun = 0;
            } else {
                gameRun = 1;
            }
            if (gameRun == 1) { //gameRun = 1, create game Run variable
                if (countNum > 0) {
                    countdown();
                } else {

                    collisions();

                    //player 1 goalie movement
                    if (aPressed) {
                        player1Goalie1.y = player1Goalie1.y - 6;
                        player1Goalie2.y = player1Goalie2.y - 6;
                    }
                    if (zPressed) {
                        player1Goalie1.y = player1Goalie1.y + 6;
                        player1Goalie2.y = player1Goalie2.y + 6;
                    }
                    //player 1 defence movement
                    if (sPressed) {
                        player1Defence1.y = player1Defence1.y - 6;
                        player1Defence2.y = player1Defence2.y - 6;
                        player1Defence3.y = player1Defence3.y - 6;
                    }
                    if (xPressed) {
                        player1Defence1.y = player1Defence1.y + 6;
                        player1Defence2.y = player1Defence2.y + 6;
                        player1Defence3.y = player1Defence3.y + 6;
                    }
                    //player 1 forward movement
                    if (dPressed) {
                        player1Forward1.y = player1Forward1.y - 6;
                        player1Forward2.y = player1Forward2.y - 6;
                        player1Forward3.y = player1Forward3.y - 6;
                    }
                    if (cPressed) {
                        player1Forward1.y = player1Forward1.y + 6;
                        player1Forward2.y = player1Forward2.y + 6;
                        player1Forward3.y = player1Forward3.y + 6;
                    }
                    //player 2 forward movement
                    if (hPressed) {
                        player2Forward1.y = player2Forward1.y - 6;
                        player2Forward2.y = player2Forward2.y - 6;
                        player2Forward3.y = player2Forward3.y - 6;
                    }
                    if (bPressed) {
                        player2Forward1.y = player2Forward1.y + 6;
                        player2Forward2.y = player2Forward2.y + 6;
                        player2Forward3.y = player2Forward3.y + 6;
                    }
                    //player 2 defence movement
                    if (jPressed) {
                        player2Defence1.y = player2Defence1.y - 6;
                        player2Defence2.y = player2Defence2.y - 6;
                        player2Defence3.y = player2Defence3.y - 6;
                    }
                    if (nPressed) {
                        player2Defence1.y = player2Defence1.y + 6;
                        player2Defence2.y = player2Defence2.y + 6;
                        player2Defence3.y = player2Defence3.y + 6;
                    }
                    //player 2 goalie movement
                    if (kPressed) {
                        player2Goalie1.y = player2Goalie1.y - 6;
                        player2Goalie2.y = player2Goalie2.y - 6;
                    }
                    if (mPressed) {
                        player2Goalie1.y = player2Goalie1.y + 6;
                        player2Goalie2.y = player2Goalie2.y + 6;
                    }

                    //updates x coordinate every second
                    ball.x += velocityX;
                    //updates y coordinate every second
                    ball.y += velocityY;

                    //player 2 scores on p1 net
                    if (ball.x + ball.width <= 0 && ball.y >= HEIGHT / 3 && ball.y + ball.height <= 2 * HEIGHT) {
                        player2Score++;
                        //countdown();
                        resetBall();
                    }
                    //player 1 scores on p2 net
                    if (ball.x >= WIDTH && ball.y >= HEIGHT / 3 && ball.y + ball.height <= 2 * HEIGHT / 3) {
                        player1Score++;
                        //countdown();
                        resetBall();
                    }

                    if (player1Score == 10 || player2Score == 10) {
                        done = true;
                    }


                }
            }
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };

        }
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down

        @Override
        public void mousePressed(MouseEvent e) {
        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {
        // if a key has been pressed down

        @Override
        public void keyPressed(KeyEvent e) {
            //moving the players with keys
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                zPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_X) {
                xPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                cPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_H) {
                hPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                bPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                jPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_N) {
                nPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_K) {
                kPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                mPressed = true;
            }

            //space key to begin game
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                spacePressed = true;
                gameRun = 1;
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            //stopping the players movement with keys
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                zPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                sPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_X) {
                xPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                cPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_H) {
                hPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                bPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                jPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_N) {
                nPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_K) {
                kPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                mPressed = false;
            }


        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        Summative game = new Summative();

        // starts the game loop
        game.run();
    }

    //method to keep game pieces on the screen and to ricochet off when they collide with other pieces
    public void collisions() {

        // keep p1 goalies on screen
        if (player1Goalie1.y <= 0) {
            player1Goalie1.y = 0;
            player1Goalie2.y = HEIGHT / 3;
        }
        if (player1Goalie2.y + player1Goalie2.height >= HEIGHT) {
            player1Goalie2.y = player1Goalie2.y - 5;
            player1Goalie1.y = 2 * HEIGHT / 3 - 45;
        }
        // keep p2 goalies on screen
        if (player2Goalie1.y <= 0) {
            player2Goalie1.y = 0;
            player2Goalie2.y = HEIGHT / 3;
        }
        if (player2Goalie2.y + player2Goalie2.height >= HEIGHT) {
            player2Goalie2.y = player2Goalie2.y - 5;
            player2Goalie1.y = 2 * HEIGHT / 3 - 45;
        }
        //keep p1 defence on screen
        if (player1Defence1.y <= 0) {
            player1Defence1.y = 0;
            player1Defence2.y = HEIGHT / 4;
            player1Defence3.y = 2 * HEIGHT / 4;
        }
        if (player1Defence3.y + player1Defence3.height >= HEIGHT) {
            player1Defence3.y = player1Defence3.y - 5;
            player1Defence2.y = 3 * HEIGHT / 4 - 40;
            player1Defence1.y = 2 * HEIGHT / 4 - 40;
        }
        //keep p2 defence on screen
        if (player2Defence1.y <= 0) {
            player2Defence1.y = 0;
            player2Defence2.y = HEIGHT / 4;
            player2Defence3.y = 2 * HEIGHT / 4;
        }
        if (player2Defence3.y + player2Defence3.height >= HEIGHT) {
            player2Defence3.y = player2Defence3.y - 5;
            player2Defence2.y = 3 * HEIGHT / 4 - 40;
            player2Defence1.y = 2 * HEIGHT / 4 - 40;
        }
        //keep p1 forward on screen
        if (player1Forward1.y <= 0) {
            player1Forward1.y = 0;
            player1Forward2.y = HEIGHT / 4;
            player1Forward3.y = 2 * HEIGHT / 4;
        }
        if (player1Forward3.y + player1Forward3.height >= HEIGHT) {
            player1Forward3.y = player1Forward3.y - 5;
            player1Forward2.y = 3 * HEIGHT / 4 - 40;
            player1Forward1.y = 2 * HEIGHT / 4 - 40;
        }
        //keep p2 forward on screen
        if (player2Forward1.y <= 0) {
            player2Forward1.y = 0;
            player2Forward2.y = HEIGHT / 4;
            player2Forward3.y = 2 * HEIGHT / 4;
        }
        if (player2Forward3.y + player2Forward3.height >= HEIGHT) {
            player2Forward3.y = player2Forward3.y - 5;
            player2Forward2.y = 3 * HEIGHT / 4 - 40;
            player2Forward1.y = 2 * HEIGHT / 4 - 40;
        }

        //keep the ball on screen at the top
        if (ball.y <= 0) {
            velocityY = 4;
        }

        //keep the ball on screen at the bottom
        if (ball.y + ball.height >= HEIGHT) {
            velocityY = -velocityY;
        }

        //keep the ball on screen along the left where no net
        if (ball.x <= 0 && ball.y <= HEIGHT / 3) {
            velocityX = 4;
        }
        if (ball.x <= 0 && ball.y >= 2 * HEIGHT / 3) {
            velocityX = 4;
        }

        //keep ball on the screen along the right where no net
        if (ball.x >= WIDTH && ball.y <= HEIGHT / 3) {
            velocityX = -4;
        }
        if (ball.x >= WIDTH && ball.y >= 2 * HEIGHT / 3) {
            velocityX = -4;
        }

        //if ball collides with a player, bounces off
        if (ball.intersects(player1Goalie1)) {
            collisionOverlap(player1Goalie1);
        }
        if (ball.intersects(player1Goalie2)) {
            collisionOverlap(player1Goalie2);
        }
        if (ball.intersects(player1Defence1)) {
            collisionOverlap(player1Defence1);
        }
        if (ball.intersects(player1Defence2)) {
            collisionOverlap(player1Defence2);
        }
        if (ball.intersects(player1Defence3)) {
            collisionOverlap(player1Defence3);
        }
        if (ball.intersects(player1Forward1)) {
            collisionOverlap(player1Forward1);
        }
        if (ball.intersects(player1Forward2)) {
            collisionOverlap(player1Forward2);
        }
        if (ball.intersects(player1Forward3)) {
            collisionOverlap(player1Forward3);
        }
        if (ball.intersects(player2Goalie1)) {
            collisionOverlap(player2Goalie1);
        }
        if (ball.intersects(player2Goalie2)) {
            collisionOverlap(player2Goalie2);
        }
        if (ball.intersects(player2Defence1)) {
            collisionOverlap(player2Defence1);
        }
        if (ball.intersects(player2Defence2)) {
            collisionOverlap(player2Defence2);
        }
        if (ball.intersects(player2Defence3)) {
            collisionOverlap(player2Defence3);
        }
        if (ball.intersects(player2Forward1)) {
            collisionOverlap(player2Forward1);
        }
        if (ball.intersects(player2Forward2)) {
            collisionOverlap(player2Forward2);
        }
        if (ball.intersects(player2Forward3)) {
            collisionOverlap(player2Forward3);
        }
    }

    //sets ball back to center
    public void resetBall() {

        //array with 1 and -1 stored in it
        int[] randomArray = new int[2];
        randomArray[0] = -1;
        randomArray[1] = 1;

        //randomly generate a spot in the array
        int ranNumX = (int) (Math.random() * (2 - 1 + 1)) + 1;
        int ranSpotX = randomArray[ranNumX - 1];

        //use the randomly generated 1 or -1 to multiply by the velocityX so
        //that the ball goes in a randon direction when reset
        velocityX = ranSpotX * velocityX;

        //do the same for the velocityY
        //randomly generate a spot in the array
        int ranNumY = (int) (Math.random() * (2 - 1 + 1)) + 1;
        int ranSpotY = randomArray[ranNumY - 1];

        //use the randomly generated 1 or -1 to multiply by the velocityX so
        //that the ball goes in a randon direction when reset
        velocityY = ranSpotY * velocityY;

        //ball back to the center
        ball.x = WIDTH / 2 - 10;
        ball.y = HEIGHT / 2 - 10;
    }

    //checks which side of the players the ball hits and bounces off in the appropriate direction
    public void collisionOverlap(Rectangle player) {
        //set overlap for x values to -1(meaning not set)
        int overlapX = -1;

        //find x overlap
        //ball collides on the left
        if (ball.x <= player.x) {
            //the value of the overlap in the x axis
            overlapX = ball.x + ball.width - player.x;
        } else {
            //player is on the right
            //the value of the overlap in the x axis
            overlapX = player.x + player.width - ball.x;
        }

        //set overlap for y values to -1(meaning not set)
        int overlapY = -1;

        //find y overlap
        //ball collides at the top
        if (ball.y <= player.y) {
            overlapY = ball.y + ball.height - player.y;
        } else {
            //player is below the block
            overlapY = player.y + player.height - ball.y;
        }

        //check which overlap is smaller and fix it
        if (overlapX < overlapY) {
            //find which side it is colliding on
            if (ball.x <= player.x) {
                ball.x = player.x - ball.width;
            } else {
                ball.x = player.x + player.width;
            }
            velocityX = -velocityX;
        } else {
            //find if top or bottom is colliding
            if (ball.y <= player.y) {
                //fix top
                ball.y = player.y - ball.height;
            } else {
                //fix bottom
                ball.y = player.y + player.height;
            }
            velocityY = -velocityY;
        }
    }

    //method to add a countdown until the round begins
    public void countdown() {
        if (System.currentTimeMillis() >= nextSwitch) {
            countNum = countNum - 1;
            numX = WIDTH / 2 - 20;
            numY = HEIGHT / 2 + 40;
            fontSize = 80;
            r = 0;
            nextSwitch = System.currentTimeMillis() + textDelay;

        } else {
            r++;
            fontSize = fontSize + r / 20;
            numX = numX - r / 45;
            numY = numY + r / 45;

        }

        countFont = new Font("Comic Sans", Font.BOLD, fontSize);
    }
    //fooseball_01.jpg

    public BufferedImage loadImage(String filename) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(filename));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return img;
    }
}
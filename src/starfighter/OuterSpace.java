package starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    private Ammo bullet;

    private AlienHorde horde;
    private Bullets bullets;
    private int score;
    
    private boolean[] keys;
    private BufferedImage back;
    
    private int timeElapsed;
    
    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        //instantiate other instance variables
        //Ship, Alien
        ship = new Ship(380, 480, 50, 50, 5);
        
        score = 0;
        
        bullets = new Bullets();
        
        horde = new AlienHorde();
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 6; x++) {
                horde.add(new Alien(70 + 100 * x, 70 + 100 * y, 60, 50, 3));
            }
        }

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();

        
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("StarFighter ", 25, 50);
        
        graphToBack.setColor(Color.WHITE);
        graphToBack.drawString("Score " + score, 300, 50);
        
        for(Alien alienOne : horde.getAliens()){
            alienOne.move("RIGHT");

            if(!(alienOne.getX() >= 0 && alienOne.getX() <= window.getClipBounds().getWidth())){
                alienOne.move("DOWN");
                alienOne.setSpeed(-alienOne.getSpeed());
            }
            
            if(!(alienOne.getY() + alienOne.getHeight() < window.getClipBounds().getHeight())){
                alienOne.setY(0);
                alienOne.move("DOWN");
            }
        }

        if (keys[0] == true) {
            ship.move("LEFT");
        }
        if (keys[1] == true) {
            ship.move("RIGHT");
        }
        if (keys[2] == true) {
            ship.move("UP");
        }
        if (keys[3] == true) {
            ship.move("DOWN");
        }
        if (keys[4] == true) {
            if(timeElapsed > 50){
                timeElapsed = 0;
                bullets.add(new Ammo(ship.getX(), ship.getY(), 3));
                bullets.add(new Ammo(ship.getX() + ship.getWidth() - 10, ship.getY(), 3));
            }
        }
        
        timeElapsed++;
        
        horde.drawAll(graphToBack);
        
        ship.draw(graphToBack);
        
        
        if(!(ship.getX() >= 0 && ship.getX() <= window.getClipBounds().getWidth())){
            ship.setX((int)window.getClipBounds().getWidth() - ship.getX());
        }
        if(!(ship.getY() >= 0 && ship.getY() <= window.getClipBounds().getHeight())){
            ship.setY((int)window.getClipBounds().getHeight() - ship.getY());
        }
        
        
        if (horde.isEmpty()) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 6; x++) {
                    horde.add(new Alien(70 + 100 * x, 70 + 100 * y, 60, 50, 3));
                }
            }
        }
        
        graphToBack.setColor(Color.WHITE);
        graphToBack.drawString("Score " + score, 300, 50);
        
        graphToBack.setColor(Color.BLUE);
        bullets.moveAll();
        bullets.drawAll(graphToBack);
        graphToBack.setColor(Color.BLACK);
        
        checkAlien();
        checkShip();
        
        
        
        twoDGraph.drawImage(back, null, 0, 0);
    }
    
    
    public void checkAlien() {
        for (int i = 0; i < bullets.getList().size(); i++) {
            for (int j = 0; j < horde.getAliens().size(); j++) {
                Alien tempa = horde.getAliens().get(j);
                Ammo tempb = bullets.getList().get(i);
                if (tempb.getX() > tempa.getX() && tempb.getX() < tempa.getX() + tempa.getWidth()) {
                    if (tempb.getY() + tempb.getHeight() > tempa.getY() && tempb.getY() < tempa.getY() + tempa.getHeight()) {
                        horde.remove(j);
                        bullets.remove(i);
                        score++;
                        return;
                    }
                }
            }
        }
    }
    
    public void checkShip() {
            for (int j = 0; j < horde.getAliens().size(); j++) {
                Alien tempa = horde.getAliens().get(j);
                if (ship.getX() > tempa.getX() && ship.getX() < tempa.getX() + tempa.getWidth()) {
                    if (ship.getY() + ship.getHeight() > tempa.getY() && ship.getY() < tempa.getY() + tempa.getHeight()) {
                        ship.reset();
                        score = 0;
                        return;
                    }
                }
            }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'h') {
            keys[0] = true;
        }
        if (e.getKeyChar() == 'j') {
            keys[1] = true;
        }
        if (e.getKeyChar() == 'u') {
            keys[2] = true;
        }
        if (e.getKeyChar() == 'n') {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'h') {
            keys[0] = false;
        }
        if (e.getKeyChar() == 'j') {
            keys[1] = false;
        }
        if (e.getKeyChar() == 'u') {
            keys[2] = false;
        }
        if (e.getKeyChar() == 'n') {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}

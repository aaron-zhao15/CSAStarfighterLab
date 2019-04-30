package starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {

    private int speed;
    private Image image;

    public Ship() {
        this(100, 100, 100, 100, 5);
    }

    public Ship(int x, int y) {
        this(x, y, 10, 10, 10);
    }

    public Ship(int x, int y, int s) {
        this(x, y, 10, 10, s);
    }

    public Ship(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
        try {
            URL url = getClass().getResource("/images/ship.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //System.out.println(e);
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        String temp = direction.toUpperCase();
        switch(temp){
            case "LEFT":
                setX(getX() - getSpeed());
                break;
            case "RIGHT":
                setX(getX() + getSpeed());
                break;
            case "UP":
                setY(getY() - getSpeed());
                break;
            case "DOWN":
                setY(getY() + getSpeed());
                break;
        }
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void reset(){
        super.setX(380);
        super.setY(480);
    }

    public String toString() {
        return super.toString() + getSpeed();
    }
}

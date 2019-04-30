package starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {

    private int speed;

    public Ammo() {
        this(0, 0, 0);
    }

    public Ammo(int x, int y) {
        this(x, y, 0);
    }

    public Ammo(int x, int y, int s) {
        super(x, y);
        speed = s;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void draw(Graphics window) {
        window.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
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

    public String toString() {
        return super.toString() + getSpeed();
    }
}

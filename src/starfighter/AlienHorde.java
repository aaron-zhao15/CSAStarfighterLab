package starfighter;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private List<Alien> aliens;

    public AlienHorde() {
        aliens = new ArrayList<>();
    }
    
    public AlienHorde(int size) {
        aliens = new ArrayList<>(size);
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawAll(Graphics window) {
        for (Alien i : aliens) {
            i.draw(window);
        }
    }

    public void moveAll() {
        for (Alien i : aliens) {
            if (i.getAlive()) {
                if (i.getActiveDirection() == 0) {
                    i.setActiveDirection((int) Math.round((Math.random() * 2) - 1));
                }
                i.setX(i.getX() + i.getActiveDirection() * i.getY() / 100);
                i.setY(i.getY() + 3);
                if (i.getX() < 0) {
                    i.setX(WIDTH - i.getWidth());
                }
                if (i.getX() + i.getWidth() > WIDTH) {
                    i.setX(0);
                }
                if (i.getY() < 0) {
                    i.setY(HEIGHT);
                }
                if (i.getY() > HEIGHT) {
                    i.setY(0);
                }
            } else {
                i.setX(i.getX() + i.getSpeed());
                i.setY(i.getY() + (Math.abs(i.getSpeed()) / i.getSpeed()) * ((2 * (int) Math.floor(Math.cos(i.getX() * Math.PI / 150))) + 1));
                if (i.getX() < 0) {
                    i.setSpeed(-i.getSpeed());
                }
                if (i.getX() + i.getWidth() > WIDTH) {
                    i.setSpeed(-i.getSpeed());
                }
                if (i.getY() < 0) {
                    i.setY(HEIGHT);
                }
                if (i.getY() > HEIGHT) {
                    i.setY(0);
                }
            }
        }
    }

    public void removeDeadOnes(List<Ammo> shots) {
        shots.clear();
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void remove(int i) {
        aliens.remove(i);
    }

    public void activate(int i) {
        aliens.get(i).setAlive(true);
    }

    public boolean isEmpty() {
        return aliens.size() == 0;
    }

    public void clear() {
        aliens.clear();
    }

    public String toString() {
        return "";
    }
}

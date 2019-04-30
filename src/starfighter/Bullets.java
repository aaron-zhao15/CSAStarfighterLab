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

public class Bullets {

    private List<Ammo> ammo;

    public Bullets() {
        ammo = new ArrayList<>();
    }

    public void add(Ammo al) {
        ammo.add(al);
    }

    public void drawAll(Graphics window) {
        for (Ammo a : ammo) {
            a.draw(window);
        }
    }

    public void moveAll() {
        for (Ammo a : ammo) {
            a.move("UP");
        }
        for (int i = 0; i < ammo.size(); i++) {
            if (ammo.get(i).getY() < 0) {
                ammo.remove(i);
            }
        }
    }
    
    

    public void cleanEmUp() {
    }

    public List<Ammo> getList() {
        return ammo;
    }

    public void remove(int i) {
        ammo.remove(i);
    }

    public String toString() {
        return "";
    }
}

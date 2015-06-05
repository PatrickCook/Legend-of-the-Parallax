
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patrick
 */
public class MapCollision {

    int gridX = 0;
    int gridY = 0;
    int pointX;
    int pointY;
    ArrayList<Rectangle> blocked = new ArrayList<Rectangle>();
    ArrayList<Rectangle> open = new ArrayList<Rectangle>();
    int[][] collisionMap;
    GameStart game = new GameStart();

    public MapCollision() {
        setWorldCollision();
    }
    //returns false if the object intersects
    //returns true if they dont;

    public void setOpenAndBlocked() {
        blocked.clear();
        for (int c = 0; c < collisionMap.length; c++) {
            for (int d = 0; d < collisionMap[0].length; d++) {
                if (collisionMap[c][d] == 1) {
                    blocked.add(new Rectangle(d * 50, (c) * 50, 50, 50));
                }

            }
        }
        open.clear();
        for (int c = 0; c < collisionMap.length; c++) {
            for (int d = 0; d < collisionMap[0].length; d++) {
                if (collisionMap[c][d] == 0) {
                    open.add(new Rectangle((d * 50)+10, (c*50)+10, 10, 10));
                }
            }
        }
    }

    public boolean blockedMove(int x, int y, int width, int height) {
        Rectangle p = new Rectangle(x, y, width, height);
        for (Rectangle r : blocked) {
            if (p.intersects(r)) {
                return true;
            }
        }

        return false;
    }

    public Point pointSpawn() {
        Rectangle temp = open.get((int) (Math.random() * open.size()));
        pointX = (int) temp.getX();
        pointY = (int) temp.getY();
        return new Point(pointX, pointY);
    }

    public void setWorldCollision() {

        if (game.world == 1) {

            collisionMap = new int[][]{
                //0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //0
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //1
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //2
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //3
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, //4
                {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, //5
                {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1}, //6
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //7
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1}, //8
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //9
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}, //10
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}
            };
        } else if (game.world == 2) {
            collisionMap = new int[][]{
                //0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},};//11
        }
        setOpenAndBlocked();
    }
}
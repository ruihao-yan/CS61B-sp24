package core;
import tileengine.Tileset;
import tileengine.TETile;
import java.util.Random;
import java.util.Scanner;
public class World {

    // build your own world!
    public static final int DEFAULT_WIDTH = 60;
    public static final int DEFAULT_HEIGHT = 50;
    private final int width;
    private final int height;
    private Random Seed;
    public TETile[][] tiles;

    public World(long seed){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.Seed = new Random(seed);
        this.tiles = new TETile[width][height];
    }

    public World(int width, int height,long seed){
        this.width = width;
        this.height = height;
        this.Seed = new Random(seed);
        this.tiles = new TETile[width][height];
    }

    public void fillWithRandomSeed(TETile[][] tet) {
        int width = tet.length;
        int height = tet[0].length;
        for(int x = 0; x < width; x++) {
           for(int y = 0; y < height; y++) {
               tet[x][y] = randomTile();
           }
        }
    }
    private TETile randomTile() {
        int tilNum = Seed.nextInt(2);
        return switch(tilNum){
            case 0 -> Tileset.WALL;
            default -> Tileset.FLOOR;
        };
    }




}

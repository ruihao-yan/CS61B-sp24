package core;
import tileengine.Tileset;
import tileengine.TETile;
import java.util.Random;

public class World {

    // build your own world!
    public static final int DEFAULT_WIDTH = 60;
    public static final int DEFAULT_HEIGHT = 60;
    private  int width;
    private  int height;
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

    //
    public void creatWorld() {
        fillWithNothingOrWall(this.tiles, false);
            for(int x = 0; x < width; x++) {
               for(int y = 0; y < height; y++) {
                   if(x == 0 || y == 0){
                       continue;
                   }
                   this.tiles[x][y] = randomTile();
               }
            }
        changeFive();
        modifyWallToNothing();
        //changeWall();
    }

    //除了边界以外每个地方是墙或者地板，
    private TETile randomTile() {
        int tilNum = Seed.nextInt(3);
        return switch(tilNum){
            case 0 -> Tileset.FLOOR;
            default -> Tileset.WALL;
        };
    }

    public TETile[][] nextGeneration(TETile[][] tiles) {
        width = tiles.length;
        height = tiles[0].length;
        TETile[][] nextGen = new TETile[width][height];
        // The board is filled with Tileset.NOTHING
        fillWithNothingOrWall(nextGen, true);
        //change if a dead cell has 5 or more than will turn into live cell
        int liveCell = 0;
        int[] xx = {-1,-1,-1,0,0,1,1,1};
        int[] yy = {1,0,-1,1,-1,1,0,-1};
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                int tempx, tempy;
                boolean wall = x == 0 || y == 0 || x == width - 1 || y == height - 1;
                //遍历九宫格
                for(int i = 0; i < xx.length; i++){
                    tempx = x + xx[i];
                    tempy = y + yy[i];
                    if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height && tiles[tempx][tempy] == Tileset.FLOOR){
                        liveCell++;
                    }
                }
                if(!wall && tiles[x][y] == Tileset.WALL && liveCell >= 5){
                    nextGen[x][y] = Tileset.FLOOR;
                }
                else if(!wall && tiles[x][y] == Tileset.FLOOR && liveCell >= 2){
                    nextGen[x][y] = Tileset.FLOOR;
                }
                liveCell = 0;
            }
        }
        return nextGen;
    }

    private void fillWithNothingOrWall(TETile[][] tet, boolean wall) {
        int Width = tet.length;
        int Height = tet[0].length;
        for(int x = 0; x < Width; x++) {
            for(int y = 0; y < Height; y++) {
                tet[x][y] = Tileset.NOTHING;
                if(wall){
                    tet[x][y] = Tileset.WALL;
                }
            }
        }
    }

    private void modifyWallToNothing() {
        int[] tempx = {-1, 0, 1, 0};
        int[] tempy = {0, -1, 0, 1};
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                int wallNumber = 0;
                for(int i = 0; i < tempx.length; i++){
                   int xx = x + tempx[i];
                   int yy = y + tempy[i];
                   if(xx > 0 && xx < width - 1 && yy > 0 && yy < height - 1  && (tiles[xx][yy] == Tileset.WALL || tiles[xx][yy] == Tileset.NOTHING) ){
                      wallNumber++;
                   }
                }
                if(wallNumber == 4){
                    this.tiles[x][y] = Tileset.NOTHING;
                }
            }
       }

    }


    //进行5次迭代
    private void changeFive() {
        for(int x = 0; x < 5; x++) {
            this.tiles = this.nextGeneration(tiles);
        }
    }

    private void changeWall(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this. height; y++){
                if(this.tiles[x][y] == Tileset.WALL){
                    continue;
                }
                int[] xx = {1, 0 , -1, 1};
                int[] yy = {0, -1, 0, 1};
                for(int i = 0; i < xx.length; i++){
                    int tempx = x + 2 * xx[i];
                    int tempy = y + 2 * yy[i];
                    boolean valid =tempx > 0 && tempy > 0 && tempx < this.width && tempy < this.height;
                    if(valid){
                        if(tiles[tempx][tempy] == Tileset.FLOOR && tiles[tempx - xx[i]][tempy - yy[i]] == Tileset.WALL)
                            tiles[tempx - xx[i]][tempy - yy[i]] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

}

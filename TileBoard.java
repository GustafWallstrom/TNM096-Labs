import java.util.*;

//javac EightPuzzle.java && java EightPuzzle

public class TileBoard {

    private final List<Tile> tiles;
    int right, left, up, down;
    public int h, f, g;
    public List<String> moves;

    public TileBoard(final List<Tile> t){
        tiles = new ArrayList<Tile>();
        for(int i = 0; i < t.size(); i++){
            tiles.add(t.get(i));
        }
        moves = new ArrayList<String>();
    }

    public TileBoard(){
        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(1));
        tiles.add(new Tile(2));
        tiles.add(new Tile(3));
        tiles.add(new Tile(4));
        tiles.add(new Tile(5));
        tiles.add(new Tile(6));
        tiles.add(new Tile(7));
        tiles.add(new Tile(8));
        tiles.add(new Tile(0));
        
        moves = new ArrayList<String>();
        h = 0;
    }

    public List<Tile> getTiles(){
        return tiles;
    }

    public void printTiles(){
        for(int i = 0; i < tiles.size(); i++){
            if((i % 3) == 0 || i == 0)
                System.out.print("|" + tiles.get(i).getValue() + " ");

            else if(i == 1 || i == 4 || i == 7)
                System.out.print(tiles.get(i).getValue() + " ");

            else
                System.out.print(tiles.get(i).getValue() + "|\n");
        }
    }

    static int manhattan(int a, int b){
        return Math.abs(a/3 - b/3) + Math.abs(a % 3 - b % 3);
    }

    public Tile getTileByValue(final int v){
        for(int i = 0; i < tiles.size(); i++){
            if(v == tiles.get(i).getValue()) return tiles.get(i);
        }
        return null;
    }

    public int getTileIndex(final int v){
        for(int i = 0; i < tiles.size(); i++){
            if(v == tiles.get(i).getValue()) return i;
        }
        return -1;
    }

    public int getF(){
		return (g + h);
    }
    
	public int getH(){
		return h;
    }
    
	public int getG(){
		return g;
    }
    
	public void setG(int val){
        g = val;
        f = g + h;
    }
    
	public void setH(int val){
        h = val;
        f = g + h;
	}

    public List<Tile> getMovableTiles(){

        ArrayList<Tile> neighbours = new ArrayList<Tile>();

        int zeroPos = getTileIndex(0);

        switch(zeroPos){
            //Set left, right, up, down to -1 if it´s outside the border
            //else set to index of the neighbour
            case 0:
                left = -1;
                right = zeroPos + 1;
                up = -1;
                down = zeroPos + 3;
                break;
            case 1:
                left = zeroPos - 1;
                right = zeroPos + 1;
                up = -1;
                down = zeroPos + 3;
                break;
            case 2:
                left = zeroPos - 1;
                right = -1;
                down = zeroPos + 3;
                up = -1;
                break;
            case 3:
                left = -1;
                right = zeroPos + 1;
                up = zeroPos - 3;
                down = zeroPos + 3;
                break;
            case 4:
                left = zeroPos - 1;
                right = zeroPos + 1;
                down = zeroPos + 3;
                up = zeroPos - 3;
                break;
            case 5:
                right = -1;
                left = zeroPos -1;
                down = zeroPos + 3;
                up = zeroPos - 3;
                break;
            case 6:
                left = -1;
                right = zeroPos + 1;
                up = zeroPos - 3;
                down = -1;
                break;
            case 7:
                left = zeroPos -1;
                right = zeroPos +1;
                down = -1;
                up = zeroPos - 3;
                break;
            case 8:
                left = zeroPos -1;
                right = -1;
                down = -1;
                up = zeroPos -3;
                break;
        }

        for(int i = 0; i < tiles.size(); i++){
            if(i == right || i == left || i == down || i == up)
                neighbours.add(tiles.get(i));
        }
        
        return neighbours;
    }

    public int tilesOutOfPlace(){
        int h = 0; 

        for(int i = 0; i < tiles.size() - 1; i++)
            if(this.getTiles().get(i).getValue() != (i + 1)) h++;

        return h;
    }

@Override
	  public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    TileBoard board = (TileBoard) o;
	    return Arrays.equals(tiles.toArray(new Tile[tiles.size()]), board.getTiles().toArray(new Tile[board.getTiles().size()]));
	  }
	  
@Override
	  public int hashCode() {
	    return Arrays.hashCode(tiles.toArray(new Tile[tiles.size()]));
	  }
}

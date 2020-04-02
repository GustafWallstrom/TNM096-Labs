import java.util.*;

/* Lab 1 in TNM096
     * This will solve an 8-puzzle
     * Author: Gustaf Wallström
     */

public class EightPuzzle {

    static TileBoard startBoard;
    static TileBoard endBoard;
    static TileBoard currentBoard;

    public static TileBoard newCurrentBoard(Tile temp) {

       List<Tile> tempList = new ArrayList<Tile>(currentBoard.getTiles());
       Collections.swap(tempList, currentBoard.getTileIndex(temp.getValue()), currentBoard.getTileIndex(0));

       return new TileBoard(tempList);
    };

    public static void main(final String[] args) {

       /** INITIALIZE */

       final List<Tile> start = new ArrayList<Tile>();
       PriorityQueue<TileBoard> tileBoardQueue = new PriorityQueue<TileBoard>(){

          private static final long serialVersionUID = 1L;

          @Overload Comparable (TileBoard t1, TileBoard t2){
             return (t)
          }

       };

       start.add(new Tile(1));
       start.add(new Tile(5));
       start.add(new Tile(4));
       start.add(new Tile(2));
       start.add(new Tile(8));
       start.add(new Tile(3));
       start.add(new Tile(4));
       start.add(new Tile(6));
       start.add(new Tile(0));

       startBoard = new TileBoard(start);
       endBoard = new TileBoard();
       currentBoard = startBoard;
       tileBoardQueue.add(currentBoard);

       /** RUN */

       System.out.println("\nGoal: ");
       endBoard.printTiles();

       System.out.println("\nStart Board:");
       startBoard.printTiles();

       System.out.println("\nNumber of tiles out of place: " + startBoard.h);

       System.out.println("\nThese tiles can be moved: ");
       for (int i = 0; i < startBoard.getMovableTiles().size(); i++)
          System.out.print(startBoard.getMovableTiles().get(i).getValue() + " ");
      
       System.out.println(" ");

       List<Tile> neighbours = currentBoard.getMovableTiles();

       for(int i = 0; i < neighbours.size(); i++){
          TileBoard b = newCurrentBoard(neighbours.get(i));
          System.out.println("\nCurrent Board with h: " +  b.h + " is :");
          b.printTiles();
          tileBoardQueue.add(b);
       }

       
    }
 }
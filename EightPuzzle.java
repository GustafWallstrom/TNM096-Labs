import java.util.*;

/* Lab 1 in TNM096
     * This will solve an 8-puzzle
     * Author: Gustaf Wallström
     */

public class EightPuzzle {

    static TileBoard startBoard;
    static TileBoard endBoard;
    TileBoard currentBoard;

    public static void main(final String[] args) {

       /** INITIALIZE */

       final List<Tile> start = new ArrayList<Tile>();

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

      /** RUN */
      System.out.println("\nGoal: ");
      endBoard.printTiles();

      System.out.println("\nStart Board:");
      startBoard.printTiles();

      System.out.println("\nNumber of tiles out of place: " + startBoard.tilesOutOfPlace());
   
    }
 }
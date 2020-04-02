import java.util.*;

/* Lab 1 in TNM096
     * This will solve an 8-puzzle
     * Author: Gustaf Wallström
     */

public class EightPuzzle {

   /**DECLARE VARIABLES */

    static TileBoard startBoard;
    static TileBoard endBoard;
    static TileBoard currentTileBoard;
    private static final int SIZE = 100;

   /** FUCTIONS */
    public static TileBoard newcurrentTileBoard(Tile temp) {

       List<Tile> tempList = new ArrayList<Tile>(currentTileBoard.getTiles());
       Collections.swap(tempList, currentTileBoard.getTileIndex(temp.getValue()), currentTileBoard.getTileIndex(0));

       return new TileBoard(tempList);
    };

    static int manhattan(int a, int b){
       return Math.abs(a/3 - b/3) + Math.abs(a % 3 - b % 3);
    }

      //A queue which sorts the TileBoards after the lowest value of h(s), the estimated cost function.
    static PriorityQueue<TileBoard> openList = new PriorityQueue<TileBoard>(SIZE, new Comparator<TileBoard>() {
       @Override
       public int compare(TileBoard t1, TileBoard t2) {
          return (t1.f - t2.f);
       }              
    });

    static HashSet<TileBoard> closedList = new HashSet<TileBoard>();

    public static void main(final String[] args) {

       /** INITIALIZE */

       final List<Tile> start = new ArrayList<Tile>();
      /*
       start.add(new Tile(7));
       start.add(new Tile(2));
       start.add(new Tile(4));
       start.add(new Tile(5));
       start.add(new Tile(0));
       start.add(new Tile(6));
       start.add(new Tile(8));
       start.add(new Tile(3));
       start.add(new Tile(1));
      */
       
       start.add(new Tile(1));
       start.add(new Tile(2));
       start.add(new Tile(3));
       start.add(new Tile(4));
       start.add(new Tile(5));
       start.add(new Tile(6));
       start.add(new Tile(7));
       start.add(new Tile(0));
       start.add(new Tile(8));

       startBoard = new TileBoard(start);
       endBoard = new TileBoard();

       openList.add(startBoard);
       closedList.add(startBoard);

       /** RUN */

       System.out.println("\nGoal: ");
       endBoard.printTiles();

       System.out.println("\nStart Board with h = " + startBoard.h + " : ");
       startBoard.printTiles();

       while(!openList.isEmpty()){

         currentTileBoard = openList.poll();
         closedList.add(currentTileBoard);

         if(currentTileBoard.h == 0){
            System.out.println("\nGoal reached");
            System.out.println("Number of slides, g(s) = " + currentTileBoard.g);

            return;
         }
         List<Tile> neighbours = currentTileBoard.getMovableTiles();

         for(int i = 0; i < neighbours.size(); i++){
            TileBoard b = newcurrentTileBoard(neighbours.get(i));
            b.g = currentTileBoard.g + 1;
            
            //h1
            //b.f = b.h + b.g;

            //h2
            b.f = 0;
            for(int j = 1; j < b.getTiles().size(); j++){

               b.f += manhattan(b.getTileIndex(0), b.getTileIndex(j));
            }

            if(b != null && !closedList.contains(b)){
               openList.add(b);
            }
         }
         
       }

       
    }
 }
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
    public static TileBoard newcurrentTileBoard(Tile t) {

       int pos = currentTileBoard.getTileIndex(t.getValue()) - currentTileBoard.getTileIndex(0);

       List<Tile> tempList = new ArrayList<Tile>(currentTileBoard.getTiles());
       Collections.swap(tempList, currentTileBoard.getTileIndex(t.getValue()), currentTileBoard.getTileIndex(0));
       TileBoard tempBoard = new TileBoard(tempList);

       if(pos == -1) tempBoard.moves.add("L");
       else if(pos == 1) tempBoard.moves.add("R");
       else if(pos == -3) tempBoard.moves.add("U");
       else if(pos == 3)tempBoard.moves.add("D");

       return tempBoard;
    };

    static int manhattan(int a, int b){
       return Math.abs(a/3 - b/3) + Math.abs(a % 3 - b % 3);
    };

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
      
      // Hard from Elmira
      /*
      start.add(new Tile(8));
      start.add(new Tile(6));
      start.add(new Tile(7));
      start.add(new Tile(2));
      start.add(new Tile(5));
      start.add(new Tile(4));
      start.add(new Tile(3));
      start.add(new Tile(0));
      start.add(new Tile(1));
      */
       //Medium difficulty from Elmira
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
       //Easy
      
      
       start.add(new Tile(4));
       start.add(new Tile(1));
       start.add(new Tile(3));
       start.add(new Tile(7));
       start.add(new Tile(2));
       start.add(new Tile(6));
       start.add(new Tile(0));
       start.add(new Tile(5));
       start.add(new Tile(8));
       

       // another medium with another start order...
      /*
       start.add(new Tile(1));
       start.add(new Tile(2));
       start.add(new Tile(3));
       start.add(new Tile(8));
       start.add(new Tile(0));
       start.add(new Tile(4));
       start.add(new Tile(7));
       start.add(new Tile(6));
       start.add(new Tile(5));
       */

       startBoard = new TileBoard(start);
       endBoard = new TileBoard();


       int startH = 0;
       for(int j = 1; j < startBoard.getTiles().size(); j++){
         startH += manhattan(startBoard.getTileIndex(j), endBoard.getTileIndex(j));
      }

      startBoard.setH(startH);

       openList.add(startBoard);
       closedList.add(startBoard);

       /** RUN */

       System.out.println("\nStart Board with h = " + startBoard.getH() + " : ");
       startBoard.printTiles();

      long startTime = System.currentTimeMillis();

      int counter = 0;
      //Search loop
       while(!openList.isEmpty()){

         counter++;
         currentTileBoard = openList.poll();
         closedList.add(currentTileBoard);

         if(currentTileBoard.h == 0){
            System.out.println("\nGoal reached");
            System.out.println("Number of slides, g(s) = " + currentTileBoard.g);
            System.out.println("Time (s): " + (System.currentTimeMillis() - startTime)/1000.0);
            Collections.reverse(currentTileBoard.moves);
            System.out.println("Moves: " + currentTileBoard.moves);

            return;
         }

         int tempg = currentTileBoard.g;

         //Create all possible TileBoards for the next move
         List<Tile> neighbours = currentTileBoard.getMovableTiles();

         for(int i = 0; i < neighbours.size(); i++){

            TileBoard b = newcurrentTileBoard(neighbours.get(i));
            
            b.setG(currentTileBoard.g + 1);
            b.moves.addAll(currentTileBoard.moves);

            //h1
            //b.f = b.h + b.g;

            //h2
            int temph = 0;
            for(int j = 1; j < b.getTiles().size(); j++){
               temph += manhattan(b.getTileIndex(j), endBoard.getTileIndex(j));
            }

            b.setH(temph);
            

            // Add to open list if it does not appear in closed list.
            if (b != null && !closedList.contains(b)){
			         openList.add(b);
		      }
         }
       }

    }
 }
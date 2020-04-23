/**
 * TNM096 Lab3 - CNF
 * Author Gustaf Wallström
 */


public class lab3{
    public static void main(String[] args){

         String [] formulae = {"-sun V -money V ice", "-money V ice V movie", "-movie V money","-money V -ice", "movie"};

        // String[] formulae = {"a V b V -c", "c V b"};
        // String[] formulae = {"a V b V -c", "d V b V -g"};
        // String[] formulae = {"-b V c V t", "-c V z V b"};
        
        System.out.print("\nStarting formulae: {");

        for(int i = 0; i < formulae.length; i++){
            System.out.print(formulae[i]);
            if(i < formulae.length-1) System.out.print(", ");
        }

        System.out.print("}\n");
        

        CNF cnf = new CNF(formulae);
        // cnf.Resolution(cnf.clauseList.get(0), cnf.clauseList.get(1));
        cnf.Solver();
    }
}

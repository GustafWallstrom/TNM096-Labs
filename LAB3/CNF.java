import java.util.ArrayList;

public class CNF {

    ArrayList<Clause> clauseList = new ArrayList<Clause>();

    CNF(String[] stringArr){

        for(int i = 0; i < stringArr.length; i++) {
            Clause temp = new Clause(stringArr[i]);
            clauseList.add(temp);
            temp.RemoveDuplicates();
        }

    }

    public Clause Resolution(Clause A, Clause B){

        String cNeg = "";
        String cPos = "";

        Clause C = new Clause();

        for(int i = 0; i < A.posList.size(); i++){
            if(B.negList.contains(A.posList.get(i))){
                cNeg = cNeg.concat(A.posList.get(i));
            }
        }

        for(int i = 0; i < A.negList.size(); i++){
            if(B.posList.contains(A.negList.get(i))){
                cPos = cPos.concat(A.negList.get(i));
            }
        }

        if(cPos.equals("") && cNeg.equals("")){
            //System.out.println("Resolution false");
            return null;
        }

        if (!cNeg.equals("") || !cPos.equals("")) {

            for (int i = 0; i < A.posList.size(); i++) {
                if (!A.posList.get(i).equals(cNeg) && !C.posList.contains(A.posList.get(i))) {

                    C.posList.add(A.posList.get(i));
                }
            }

            for (int i = 0; i < A.negList.size(); i++) {
                if (!A.negList.get(i).equals(cPos) && !C.negList.contains(A.negList.get(i))) {

                    C.negList.add(A.negList.get(i));
                }
            }

            for (int i = 0; i < B.posList.size(); i++) {
                if (!B.posList.get(i).equals(cPos) && !C.posList.contains(B.posList.get(i))) {

                    C.posList.add(B.posList.get(i));
                }
            }

            for (int i = 0; i < B.negList.size(); i++) {
                if (!B.negList.get(i).equals(cNeg) && !C.negList.contains(B.negList.get(i))) {

                    C.negList.add(B.negList.get(i));
                }
            }
        }

        if (!cPos.equals("") && !cNeg.equals("")) {
            //System.out.println("Resolution false because of intersect");
            return null;
        }

        C.RemoveDuplicates();
        // System.out.println("\nResult of resolution: ");
        // C.printClause();

        return C;
    }
    
    public void Solver(){

        ArrayList<Clause> S;
        Clause C;
        ArrayList<Clause> KB = clauseList;

        boolean iterationDone;
        int iterationCount = 0;

        do{
            iterationCount++;
            S = new ArrayList<Clause>();
            C = new Clause();
            iterationDone = true;

            for(int i = 0; i < KB.size() - 1; i++){
                for(int j = i + 1; j < KB.size(); j++){
                    C = Resolution(KB.get(i), KB.get(j));
                    if(C != null){
                        S.add(C);
                        iterationDone = false;
                    }
                }
            }

            // System.out.println("\nThis is S in do: ");
            // for (int i = 0; i < S.size(); i++){
            //     S.get(i).printClause();
            // }

            // System.out.println("\nKB before Incorporate: ");
            // for (int i = 0; i < KB.size(); i++){
            //     KB.get(i).printClause();
            // }

            KB = Incorporate(S, KB);
            KB = removeDuplicates(KB);

            // System.out.println("\nThis is KB in do: ");
            // for (int i = 0; i < KB.size(); i++){
            //     KB.get(i).printClause();
            // }

        }while (!iterationDone);
       
        System.out.print("\nKB = ");
        System.out.print("{");
        for (int i = 0; i < KB.size(); i++){
            KB.get(i).printClauseAsPartOfSet();
            if(i < KB.size()-1) System.out.print(", ");
        }
        System.out.println("}");

        System.out.println("Number of iterations in do-while: " + iterationCount + "\n");
    }

    public ArrayList<Clause> Incorporate(ArrayList<Clause> S, ArrayList<Clause> KB){
        for (int i = 0; i < S.size(); i++){
            KB = Incorporate_Clause(S.get(i), KB);
        }

        return KB;
    }

    public ArrayList<Clause> Incorporate_Clause(Clause A, ArrayList<Clause> KB){

        for (int i = 0; i < KB.size(); i++){
            if (KB.get(i).equals(A)){
                return KB;
            }
            else if(A.isSubset(KB.get(i))){

                KB.get(i).negList = A.negList;
                KB.get(i).posList = A.posList;
            }

        }

        return KB;
    }

    public ArrayList<Clause> removeDuplicates(ArrayList<Clause> KB){
        ArrayList<Clause> tempKB = KB;

        for (int i = 0; i < KB.size(); i++){
            for (int j = i + 1; j < tempKB.size(); j++){
                
                if (KB.get(i).equals(tempKB.get(j))){
                    KB.remove(i);
                }
            }
        }
        return KB;
    }

}
import java.util.ArrayList;

public class Clause{

    public ArrayList<String> negList = new ArrayList<String>();
    public ArrayList<String> posList = new ArrayList<String>();

    Clause(){};

    Clause(String inString){

        inString = inString.replaceAll(" ", "");
        inString = inString.replaceAll("V", " ");

        String [] inStringSplit = inString.split(" ");

        for(int i = 0; i < inStringSplit.length; i++){
           
            if(inStringSplit[i].contains("-")){
                inStringSplit[i] = inStringSplit[i].replace("-", "");    
                negList.add(inStringSplit[i]);
            }
            else{
                posList.add(inStringSplit[i]);
            }
        }
    }

    public boolean isEmpty(){
        if(negList.isEmpty() && posList.isEmpty())
            return true;
        else
            return false;
    }

    public void printClause(){
        
        System.out.print("[");

        if(!posList.isEmpty()){
            for(int i = 0; i < posList.size(); i++){
                System.out.print(posList.get(i));
                if(i < posList.size() - 1)
                    System.out.print(" V ");
            }
        }

        if(!posList.isEmpty() && !negList.isEmpty())
            System.out.print(" V ");

        if(!negList.isEmpty()){
            for(int i = 0; i < negList.size(); i++){
                System.out.print("-" + negList.get(i));
                if(i < negList.size() - 1)
                    System.out.print(" V ");
            }
        }

        System.out.println("]");
        
    }

    public void printClauseAsPartOfSet(){
        
        if(!posList.isEmpty()){
            for(int i = 0; i < posList.size(); i++){
                System.out.print(posList.get(i));
                if(i < posList.size() - 1)
                    System.out.print(" V ");
            }
        }

        if(!posList.isEmpty() && !negList.isEmpty())
            System.out.print(" V ");

        if(!negList.isEmpty()){
            for(int i = 0; i < negList.size(); i++){
                System.out.print("-" + negList.get(i));
                if(i < negList.size() - 1)
                    System.out.print(" V ");
            }
        }
    }

    public void RemoveDuplicates(){

        for (int i = 0; i < negList.size(); i++){
            for (int j = 0; j < posList.size(); j++){
                if (negList.get(i).equals(posList.get(j))){
                    negList.remove(i);
                    posList.remove(j);
                }
            }
        }
    }

    public boolean isSubset(Clause temp){

        for (int i = 0; i < this.posList.size(); i++){
            if (!temp.posList.contains(this.posList.get(i))){
                return false;
            }
        }

        for (int i = 0; i < this.negList.size(); i++){
            if (!temp.negList.contains(this.negList.get(i))){
                return false;
            }
        }


        return true;
    }

    @Override
    public boolean equals(Object tempObj){
            //System.out.println("EQUALS");
            if (tempObj == this){
                return true;
            }

            if (tempObj == null) {
                return false;
            }

            if (getClass() != tempObj.getClass()){
                return false;
            }

            Clause tempClause = (Clause) tempObj;

            if (negList == null){
                if (tempClause.negList != null){
                    return false;
                }
            }else if (!negList.equals(tempClause.negList)){
                return false;
            }

        if (posList == null){
            if (tempClause.posList != null){
                return false;
            }
        }else if (!posList.equals(tempClause.posList)){
            return false;
        }

        return true;
    }

}
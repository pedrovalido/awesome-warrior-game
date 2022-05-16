import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    
    private static final String FULL_OF_ENERGY = "Full of energy";
    private static final String PAYS = "Pays";
    private static final String GETS = "Gets";

    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        String[] line = new String[4];
        line = buf.readLine().split(" ");
        int nChallenges = Integer.parseInt(line[0]);
        int nDecisions = Integer.parseInt(line[1]);

        //matrix of energy points in transitions
        int[][] transitions = new int[nChallenges][nChallenges];

        //variables to save memory (instead of initializing vars in every iteration of the for loop)
        int challenge1, challenge2;
        int cost;
        int aux;

        for(int i=0;i<nDecisions;i++){
            line = buf.readLine().split(" ");
            challenge1 = Integer.parseInt(line[0]);
            challenge2 = Integer.parseInt(line[3]);
            aux = Integer.parseInt(line[2]);
            cost = line[1].equals(PAYS) ? aux - (aux*2) : aux;
            transitions[challenge1][challenge2] = cost;
        }



    }

    private static void calc(int[][] transitions, int initialChallenge, int finalChallenge, int initialEnergy){
        
    }
}
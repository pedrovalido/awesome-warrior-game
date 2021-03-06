import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import graph.*;

public class Main {

    private static final String FULL_OF_ENERGY = "Full of energy";
    private static final String PAYS = "Pays";

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        String[] line = new String[4];
        line = buf.readLine().split(" ");
        int nChallenges = Integer.parseInt(line[0]);
        int nDecisions = Integer.parseInt(line[1]);

        // List of edges (to later be iterated)
        Digraph transitions = new Digraph();

        // variables to save memory (instead of initialising vars in every iteration of
        // the for loop)
        int challenge1, challenge2;
        int cost;
        int aux;

        for (int i = 0; i < nDecisions; i++) {
            line = buf.readLine().split(" ");
            challenge1 = Integer.parseInt(line[0]);
            challenge2 = Integer.parseInt(line[3]);
            aux = Integer.parseInt(line[2]);
            cost = line[1].equals(PAYS) ? -aux : aux;

            transitions.addNode(new Node(new Edge(challenge1, challenge2, cost), null, null));
        }

        line = buf.readLine().split(" ");

        int initialChallenge = Integer.parseInt(line[0]);
        int finalChallenge = Integer.parseInt(line[1]);
        int initialEnergy = Integer.parseInt(line[2]);

        Integer[] res = calc(transitions, initialChallenge, finalChallenge, initialEnergy, nChallenges);

        if (res[0] == 0)
            System.out.println(res[1]);
        else
            System.out.println(FULL_OF_ENERGY);
    }

    /**
     * Calculates the amount of points with which the wizzard reaches the final
     * challenge
     * 
     * @param digraph          - list of all the edges
     * @param initialChallenge - integer representing the initial challenge
     * @param finalChallenge   - integer representing the final challenge
     * @param initialEnergy    - integer representing the wizzard's inital energy
     * @param nChallenges      - integer representing the total number of challenges
     * @return (int, int) - the first integer represents wether or not the wizzard
     *         can reach the final challenge with more energy than he started with
     *         the second integer represents the maximum amount of points the
     *         wizzard reaches the final challenge, in case it is less than what he
     *         started with
     */
    private static Integer[] calc(Digraph digraph, int initialChallenge, int finalChallenge, int initialEnergy,
            int nChallenges) {

        // Integer wrapper class where null value simulates not being possible to travel
        // to a certain challenge and in each position of the array indicates the "max"
        // amount of points the warrior has at that stage/challenge
        Integer[] points = new Integer[nChallenges];
        points[initialChallenge] = initialEnergy;

        boolean changes = false;

        for (int i = 0; i <= nChallenges; i++) {
            changes = updatePoints(digraph, points);
            if (!changes)
                break;
        }

        int pointsFinalChallenge = points[finalChallenge];
        updatePoints(digraph, points);
        boolean positiveCycle = points[finalChallenge] > pointsFinalChallenge;

        if (positiveCycle || points[finalChallenge] > initialEnergy)
            return new Integer[] { 1, null };

        return new Integer[] { 0, points[finalChallenge] < 0 ? 0 : points[finalChallenge] };
    }

    /**
     * Iterates all the edges and changes the points array in case the wizzard can
     * get more points
     * 
     * @param digraph - list of all the edges
     * @param points - array with the amount of points the wizzard has at each challenge (index)
     * @return a boolean changes representing wether or not changes were made to the points array
     */
    private static boolean updatePoints(Digraph digraph, Integer[] points) {

        boolean changes = false;

        Node aux = new Node(null, null, digraph.getFirst());

        do {
            aux = aux.getNext();
            Edge e = aux.getEdge();
            int firstNode = e.getChallenge1();
            int secondNode = e.getChallenge2();
            if (points[firstNode] != null) {
                int newLen = points[firstNode] + e.getEnergyPoints();
                if (points[secondNode] == null || newLen > points[secondNode]) {
                    points[secondNode] = newLen;
                    changes = true;
                }
            }
        } while (aux.hasNext());
        return changes;
    }
}
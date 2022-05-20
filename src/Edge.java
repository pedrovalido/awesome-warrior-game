public class Edge {

    private int challenge1;

    private int challenge2;

    private int energyPoints;

    public Edge(int challenge1, int challenge2, int energyPoints){
        this.challenge1 = challenge1;
        this.challenge2 = challenge2;
        this.energyPoints = energyPoints;
    }

    public int getChallenge1(){
        return challenge1;
    }

    public int getChallenge2(){
        return challenge2;
    }

    public int getEnergyPoints(){
        return energyPoints;
    }

}

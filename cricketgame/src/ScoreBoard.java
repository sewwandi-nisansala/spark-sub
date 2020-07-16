public class ScoreBoard {

    //convert balls to overs

    public static double getOvers(int balls){
        double overs = 0;

        for(int x=3;x<balls;x=x+3){
            overs++;
        }

        for(int y=1;y<=(balls%3);y++){
            overs += 0.1;
        }

        return ((double)(int)(overs * 5)/5);
    }
}

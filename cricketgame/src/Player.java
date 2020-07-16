import javax.swing.plaf.basic.BasicListUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player {

    public static void Players(){
        List<String> PlayerList = new ArrayList<>();
        PlayerList.add("Player1");
        PlayerList.add("Player2");
        PlayerList.add("Player3");
        PlayerList.add("Player4");
        PlayerList.add("Player5");
        PlayerList.add("Player6");
        PlayerList.add("Player7");
        PlayerList.add("Player8");
        PlayerList.add("Player9");
        PlayerList.add("Player10");
        PlayerList.add("Player11");
        PlayerList.add("Player12");


    }





    public static int bat(){
        Random rnd=new Random();
        return (rnd.nextInt(6)+1);
    }

}


import java.util.Scanner;

public class GamePlay {


    public static void main(String[] args) {
        Scanner teamName = new Scanner(System.in);
        System.out.println("Enter Team names:");

        String name1 = teamName.nextLine();
        String name2 = teamName.nextLine();

        if (!name1.equals("") && !name2.equals("")) {
            Team team1 = new Team(name1);
            Team team2 = new Team(name2);


            ScoreBoard scoreBoard = new ScoreBoard(team1, team2);

            scoreBoard.getToss();
            scoreBoard.startGame();

            boolean isTeamFinishedPlayed = false;
            int bowlerID = 0;
            int catchGuy = 0;
            TeamPlayStatus teamPlayStatus = TeamPlayStatus.NEW_OVER;
            // Team 1 playing
            while (!isTeamFinishedPlayed) {
                Scanner keyVal = new Scanner(System.in);
                char key = keyVal.next().charAt(0);
                if ('p' == key || 'P' == key) {

                    if (teamPlayStatus == TeamPlayStatus.NEW_OVER) {
                        // Generate random Bowler for new over
                        bowlerID = Randomizer.generate(0, 5);
                    }

                    // Generate random catch guy
                    catchGuy = Randomizer.generate(0, 5);
                    teamPlayStatus = scoreBoard.getTeam1().play(scoreBoard.getTeam2().getBowlerName(bowlerID),
                            scoreBoard.getTeam2().getCatchTakenPlayerName(catchGuy));

                    if (scoreBoard.getTeam1().getScore() < scoreBoard.getTeam2().getScore() ||
                            teamPlayStatus == TeamPlayStatus.ALL_WICKETS_FALL ||
                            teamPlayStatus == TeamPlayStatus.OVERS_FINISHED) {
                        isTeamFinishedPlayed = true;
                    }
                }
            }

            // Now Play Team 2
            isTeamFinishedPlayed = false;
            bowlerID = 0;
            teamPlayStatus = TeamPlayStatus.NEW_OVER;
            // Team 1 playing
            while (!isTeamFinishedPlayed) {
                Scanner keyVal = new Scanner(System.in);
                char key = keyVal.next().charAt(0);
                if ('p' == key || 'P' == key) {
                    if (teamPlayStatus == TeamPlayStatus.NEW_OVER) {
                        // Generate random Bowler for new over
                        bowlerID = Randomizer.generate(0, 5);
                    }

                    // Generate random catch guy
                    catchGuy = Randomizer.generate(0, 5);
                    teamPlayStatus = scoreBoard.getTeam2().play(scoreBoard.getTeam1().getBowlerName(bowlerID),
                            scoreBoard.getTeam1().getCatchTakenPlayerName(catchGuy));

                    if (scoreBoard.getTeam1().getScore() < scoreBoard.getTeam2().getScore() ||
                            teamPlayStatus == TeamPlayStatus.ALL_WICKETS_FALL ||
                            teamPlayStatus == TeamPlayStatus.OVERS_FINISHED) {
                        isTeamFinishedPlayed = true;
                    }
                }
            }

            // Game Is Over
            scoreBoard.generateScoreBoard();
        } else {
            throw new IllegalArgumentException("You should enter a valid name");
        }


    }


}

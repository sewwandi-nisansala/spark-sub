import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Team {

    /**
     * Team Name
     */
    private String name;

    /**
     * Team score
     */
    private int score;

    /**
     * Team played overs
     */
    private int overs;

    /**
     * Team Played Balls
     */
    private int balls;

    /**
     * Team fallen Wickets
     */
    private int wickets;

    /**
     * Players
     */
    private List<Player> players;

    /**
     * Batting side player
     */
    private int battingSidePlayer;

    /**
     * Player in the balling side wicket
     */
    private int bowlingSidePlayer;

    String commentatorMessage = "";

    /**
     * Team constructor
     *
     * @param name team name
     */
    Team(String name) {
        this.name = name;
        this.score = 0;
        this.overs = 0;
        this.balls = 0;
        this.wickets = 0;
        this.players = new ArrayList<Player>();
        for (int i = 0; i < CricketRules.PLAYERS_PER_TEAM; i++) {
            this.players.add(new Player("Player " + String.valueOf(i)));
        }

        /*
         * Set initial batsmen
         */
        this.battingSidePlayer = 0;
        this.players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.BATTING_END);
        this.bowlingSidePlayer = 1;
        this.players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.BALLING_END);
    }

    /**
     * Get bowler name
     *
     * @param playerID player ID
     * @return bowler name
     */
    public String getBowlerName(int playerID) {
        return players.get(playerID).getPlayerName();
    }

    /**
     * Get the guy who trying to get the catch name
     *
     * @param playerID player ID
     * @return player name
     */
    public String getCatchTakenPlayerName(int playerID) {
        return players.get(playerID).getPlayerName();
    }

    /**
     * Get Team Name
     *
     * @return team name
     */
    public String getName() {
        return name;
    }

    /**
     * Get All team score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get All team wickets
     *
     * @return wickets
     */
    public int getWickets() {
        return wickets;
    }

    /**
     * Get All team played Overs
     *
     * @return overs
     */
    public double getOvers() {
        return overs + (double) balls / 10;
    }

    /**
     * Team is batting
     *
     * @param bowlerName         bowler name
     * @param catchCandidateName this guy is going to get a catch
     * @return false - not finished playing/ true - finished playing
     */
    public TeamPlayStatus play(String bowlerName, String catchCandidateName) {
        int playedScore = Randomizer.generate(0, 7);

        boolean isAllWicketsFallen = updateScore(bowlerName, catchCandidateName, playedScore);

        TeamPlayStatus teamState = updateBalls();

        if (isAllWicketsFallen) {
            commentatorMessage += "\n All Wickets Has Fallen";
            commentatorMessage += "| " + name + " : " + score + "/" + wickets + " (Overs " + overs + "." + balls + ")";
            teamState = TeamPlayStatus.ALL_WICKETS_FALL;
        }
        System.out.println(commentatorMessage);
        return teamState;
    }

    /**
     * Update a played ball
     *
     * @return is all overs finished?
     */
    private TeamPlayStatus updateBalls() {
        this.balls++;
        // Update a over if a over is balled
        if (balls == CricketRules.MAX_BALLS_PER_OVER) {
            balls = 0;
            overs += 1;
            commentatorMessage += "| Overs : " + overs + "." + balls;
            if (overs == CricketRules.OVERS_PER_TEAM) {
                commentatorMessage += "\n End Of Overs";
                commentatorMessage += "| " + name + " : " + score + "/" + wickets + " (Overs " + overs + "." + balls + ")";
                return TeamPlayStatus.OVERS_FINISHED;
            }

            changeBattingSidePlayer();
            return TeamPlayStatus.NEW_OVER;
        }
        commentatorMessage += "| Overs : " + overs + "." + balls;
        return TeamPlayStatus.PLAYING_OVER;
    }

    /**
     * Update team score
     *
     * @param bowlerName         bowler name
     * @param catchCandidateName this guy is trying to get the catch
     * @param playedScore        player score
     * @return is all wicket fallen?
     */
    private boolean updateScore(String bowlerName, String catchCandidateName, int playedScore) {
        boolean isAllWicketsFall = false;
        commentatorMessage = name + " : " + players.get(battingSidePlayer).getPlayerName();
        switch (playedScore) {
            case 0:
            case 2:
            case 4:
            case 6:
                // Set Score
                this.score += playedScore;
                players.get(battingSidePlayer).updateBall();
                players.get(battingSidePlayer).updateScore(playedScore);
                commentatorMessage += " | scoured : " + playedScore;
                break;
            case 1:
            case 3:
                // Set Score
                this.score += playedScore;
                players.get(battingSidePlayer).updateBall();
                players.get(battingSidePlayer).updateScore(playedScore);
                commentatorMessage += " | scoured : " + playedScore;
                changeBattingSidePlayer();
                break;
            case 5:
                // This case is consider as a Catch & out
                players.get(battingSidePlayer).updateBall();
                players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.OUT_BY_CATCH);
                players.get(battingSidePlayer).setWicketTakenBy(bowlerName);
                players.get(battingSidePlayer).setCatchTakenBy(catchCandidateName);
                commentatorMessage += " | Out | Catch By : " + catchCandidateName + " | Wicket By : " + bowlerName;
                wickets++;
                // Get the next bat's men if all wickets are not gone
                isAllWicketsFall = getNextBatsMen();
                break;
            case 7:
                // This case is consider as a wicket fall
                players.get(battingSidePlayer).updateBall();
                players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.OUT_BY_WICKET);
                players.get(battingSidePlayer).setWicketTakenBy(bowlerName);
                commentatorMessage += " | Out | Wicket By : " + bowlerName;
                wickets++;
                // Get the next bat's men if all wickets are not gone
                isAllWicketsFall = getNextBatsMen();
                break;
        }
        return isAllWicketsFall;
    }

    /**
     * Get Next batsman
     *
     * @return is all wickets falls?
     */
    private boolean getNextBatsMen() {
        // Get the next bat's men if all wickets are not gone
        if (wickets < CricketRules.PLAYERS_PER_TEAM - 1) {
            battingSidePlayer = wickets + 1;
            players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.BATTING_END);
            return false;
        }
        return true;
    }

    /**
     * Change batting side players
     */
    private void changeBattingSidePlayer() {
        // Change Batsmen's sides since they are running
        int temp = battingSidePlayer;
        battingSidePlayer = bowlingSidePlayer;
        bowlingSidePlayer = temp;
        players.get(battingSidePlayer).setPlayerStatus(PlayerStatus.BATTING_END);
        players.get(bowlingSidePlayer).setPlayerStatus(PlayerStatus.BALLING_END);
    }

    /**
     * Generate Team players details
     *
     * @return team details
     */
    public String getTeamDetails() {
        StringBuilder teamDetails = new StringBuilder();
        for (Player player : players) {
            if (player.getPlayerStatus() == PlayerStatus.NOT_PLAYED) {
                teamDetails.append(player.getPlayerName()).append("         Not Played");
            } else if (player.getPlayerStatus() == PlayerStatus.OUT_BY_WICKET) {
                teamDetails.append(player.getPlayerName()).append(" : ").append(player.getScore()).append("/")
                        .append("(Overs ").append(player.getPlayedOvers()).append(") - Wicket By : ").append(player.getWicketTakenBy());
            } else if (player.getPlayerStatus() == PlayerStatus.OUT_BY_CATCH) {
                teamDetails.append(player.getPlayerName()).append(" : ").append(player.getScore()).append("/")
                        .append("(Overs ").append(player.getPlayedOvers()).append(") - Catch By : ").append(player.getCatchTakenBy())
                        .append(" - Wicket By : ").append(player.getWicketTakenBy());
            } else if (player.getPlayerStatus() == PlayerStatus.BATTING_END) {
                teamDetails.append(player.getPlayerName()).append(" : ").append(player.getScore()).append("/")
                        .append("(Overs ").append(player.getPlayedOvers()).append(") (Not Out *) ");
            } else if (player.getPlayerStatus() == PlayerStatus.BALLING_END) {
                teamDetails.append(player.getPlayerName()).append(" : ").append(player.getScore()).append("/")
                        .append("(Overs ").append(player.getPlayedOvers()).append(") (Not Out) ");
            } else {
                throw new IllegalStateException("Wrong player state");
            }
            teamDetails.append("\n");
        }

        return String.valueOf(teamDetails);
    }


}
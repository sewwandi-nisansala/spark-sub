public class Player {

    /**
     * Name Of the player
     */
    private String playerName;

    /**
     * Player status
     */
    private PlayerStatus playerStatus;

    /**
     * Wicket took player name (if out)
     */
    private String wicketTakenBy = "";

    /**
     * Catch taken player name (if out by catch)
     */
    private String catchTakenBy = "";

    /**
     * Player Score
     */
    private int score;

    /**
     * Player played overs
     */
    private int playedOvers;

    /**
     * Player played balls
     */
    private int playedBalls;

    /**
     * Player class constructor to create a new player
     *
     * @param playerName name of the new player
     */
    Player(String playerName) {
        this.playerName = playerName;
        this.score = 0;
        this.playedOvers = 0;
        this.playedBalls = 0;
        this.playerStatus = PlayerStatus.NOT_PLAYED;
    }

    /**
     * Retrieve player name
     *
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set wicket taken player if this player is out
     *
     * @param wicketTakenBy player name
     */
    public void setWicketTakenBy(String wicketTakenBy) {
        this.wicketTakenBy = wicketTakenBy;
    }

    /**
     * Set catch taken player is this player is out
     *
     * @param catchTakenBy player name
     */
    public void setCatchTakenBy(String catchTakenBy) {
        this.catchTakenBy = catchTakenBy;
    }

    /**
     * Get wicket taken player name
     *
     * @return player name
     */
    public String getWicketTakenBy() {
        return wicketTakenBy;
    }

    /**
     * Get catch taken player name
     *
     * @return player name
     */
    public String getCatchTakenBy() {
        return catchTakenBy;
    }

    /**
     * Change Player States (set batting/ out/ etc...)
     *
     * @param playerStatus state of the player
     */
    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    /**
     * Get current player status
     *
     * @return player status
     */
    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    /**
     * Update player score
     *
     * @param score score
     */
    public void updateScore(int score) {
        this.score += score;
    }

    /**
     * Get player score
     *
     * @return player score
     */
    public int getScore() {
        return score;
    }

    /**
     * update a ball for the player
     */
    public void updateBall() {
        playedBalls++;
        // If player has played a over, update over digit
        if (CricketRules.MAX_BALLS_PER_OVER == playedBalls) {
            playedOvers++;
            playedBalls = 0;
        }
    }

    /**
     * get Player Played overs
     *
     * @return player played overs
     */
    public double getPlayedOvers() {
        return playedOvers + (double) playedBalls / 10;
    }
}

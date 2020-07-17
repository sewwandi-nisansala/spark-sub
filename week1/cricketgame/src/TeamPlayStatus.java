public enum TeamPlayStatus {

    /**
     * New Over
     */
    NEW_OVER(0),

    /**
     * Playing Over
     */
    PLAYING_OVER(1),

    /**
     * Overs Finished
     */
    OVERS_FINISHED(2),

    /**
     * All Wickets fall
     */
    ALL_WICKETS_FALL(3);

    /**
     * Team Status Value
     */
    private int value;

    /**
     * Team Status
     *
     * @param value Team Status Value
     */
    TeamPlayStatus(int value) {
        this.value = value;
    }

    /**
     * Get Team Status
     *
     * @return Team Status Value
     */
    public int getValue() {
        return value;
    }


}

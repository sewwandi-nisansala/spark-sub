public enum PlayerStatus {

    /**
     * Player isn't played yet
     */
    NOT_PLAYED(0),

    /**
     * Player is at batting end & facing to the next ball
     */
    BATTING_END(1),

    /**
     * Player is at balling end & facing to next ball
     */
    BALLING_END(2),

    /**
     * Player is out by wicket fall
     */
    OUT_BY_WICKET(3),

    /**
     * Player is out by catch
     */
    OUT_BY_CATCH(4);

    /**
     * Player Status Value
     */
    private int value;

    /**
     * Player Status
     *
     * @param value Player Status Value
     */
    PlayerStatus(int value) {
        this.value = value;
    }

    /**
     * Get Player Status
     *
     * @return Player Status Value
     */
    public int getValue() {
        return value;
    }


}

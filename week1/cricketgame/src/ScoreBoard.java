public class ScoreBoard {


    private Team team1;
    private Team team2;


    /**
     * This is the score board
     *
     * @param team1 team 1
     * @param team2 team 2
     */
    ScoreBoard(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * Get Team 1
     *
     * @return team 1
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * get team 2
     *
     * @return team 2
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * Display Introduction
     */
    public void startGame() {
        System.out.println();
        System.out.println("Welcome to Cricket Match : " + team1.getName() + " Vs " + team2.getName());
        System.out.println();
        System.out.println(team1.getName() + " won the toss" );
        System.out.println("Press p or P for hit a run");
    }

    /**
     * get toss
     *
     * @return toss value
     */
    public static int toss() {
        return Randomizer.generate(0, 1);
    }

    /**
     *set team1 win the toss
     */
    public void getToss(){
        if (toss() == 1) {
            Team temp = team1;
            team1 = team2;
            team2 = temp;
        }

    }

    /**
     * Generate the final score board
     */
    public void generateScoreBoard() {
        // First Let's set Winning team as Team 1
        if (team1.getScore() < team2.getScore()) {
            Team temp = team1;
            team1 = team2;
            team2 = temp;
        }

        generateBriefScoreBoard();
        generateTeamScoreBoard(team1);
        generateTeamScoreBoard(team2);
    }

    /**
     * Generate Team score board
     *
     * @param team team
     */
    private void generateTeamScoreBoard(Team team) {
        System.out.println();
        System.out.println(team.getName() + " :  " + team.getScore() + "/" + team.getWickets() + " (Overs " + team.getOvers() + ")");
        System.out.println();
        System.out.println(team.getTeamDetails());
        System.out.println();
        System.out.println("***************************************************************");
    }

    /**
     * Generate brief winning team score board
     */
    private void generateBriefScoreBoard() {
        System.out.println("***************************************************************");
        if (team1.getScore() == team2.getScore()) {
            System.out.println("                    Match Draw!");
        } else {
            System.out.println("                   Winning Team : " + team1.getName());
        }
        System.out.println("***************************************************************");
        System.out.println(team1.getName() + " :  " + team1.getScore() + "/" + team1.getWickets() + " (Overs " + team1.getOvers() + ")");
        System.out.println(team2.getName() + " :  " + team2.getScore() + "/" + team2.getWickets() + " (Overs " + team2.getOvers() + ")");
        System.out.println("***************************************************************");
    }
}

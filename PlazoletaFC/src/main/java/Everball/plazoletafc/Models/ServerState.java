package Everball.plazoletafc.Models;

import java.util.ArrayList;

public class ServerState {
    private ArrayList<Cap> team_1;
    private ArrayList<Cap> team_2;
    private Ball ball;
    private String match_event;

    public ServerState(ArrayList<Cap> team_1, ArrayList<Cap> team_2, Ball ball, String match_event) {
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.ball = ball;
        this.match_event = match_event;
    }

    public ArrayList<Cap> getTeam_1() {
        return team_1;
    }

    public ArrayList<Cap> getTeam_2() {
        return team_2;
    }

    public Ball getBall() {
        return ball;
    }

    public String getMatch_event() {
        return match_event;
    }
}

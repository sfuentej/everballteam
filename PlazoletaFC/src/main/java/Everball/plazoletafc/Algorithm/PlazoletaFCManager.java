package Everball.plazoletafc.Algorithm;


import Everball.plazoletafc.Models.*;
import Everball.plazoletafc.Utils.Tuple;
import Everball.plazoletafc.Utils.Utils;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PlazoletaFCManager {
    private FieldInfo mFieldInfo;
    private GoalValidPost mOwnValidPost;
    private GoalValidPost mRivalValidPost;
    private Ball mBall;

    private Tuple<ArrayList<Cap>, ArrayList<Cap>> mTeamPosition;
    private Boolean mWaitKickoff = false;

    public PlazoletaFCManager(FieldInfo fieldInfo) {
        this.mFieldInfo = fieldInfo;
        locateGoalPosts(mFieldInfo.getRole());
    }

    public void newServerState(ServerState serverState){
        ArrayList<Cap> activeTeam = new ArrayList<Cap>();
        ArrayList<Cap> inactiveTeam = new ArrayList<Cap>();
        this.mBall = serverState.getBall();

        if(serverState.getMatch_event().contains("has scored")){
            mWaitKickoff = true;
        } else if(serverState.getMatch_event().contains("Kickoff")) {
            mWaitKickoff = false;
        }

        if(!mWaitKickoff){
            if(mFieldInfo.getRole() == 1){
                for(Cap cap: serverState.getTeam_1()){
                    if(cap.getCooldown() <= 0){
                        activeTeam.add(cap);
                    } else {
                        inactiveTeam.add(cap);
                    }
                }
            } else if (mFieldInfo.getRole() == 2){
                for(Cap cap: serverState.getTeam_2()){
                    if(cap.getCooldown() <= 0){
                        activeTeam.add(cap);
                    } else {
                        inactiveTeam.add(cap);
                    }
                }
            }

            mTeamPosition = getTeamPosition(activeTeam, inactiveTeam);
        }
    }

    public Tuple<Boolean, ArrayList<JSONObject>> doActions(){
        ArrayList<JSONObject> actionList = new ArrayList<JSONObject>();
        Tuple<Boolean, ArrayList<JSONObject>> tuple = new Tuple<Boolean, ArrayList<JSONObject>>(false, null);

        if(mWaitKickoff){
            return tuple;
        }

        //Caps back of the ball
        Tuple<Boolean, Cap> nearCap = getMostNearCap(mTeamPosition.getItem1());

        if(nearCap.getItem1()){
            Point2D ballExternalPoint = Utils.getBallExternalPoint(mRivalValidPost, mBall, mFieldInfo.getPlayground_info().getBall_radius());
            double diffX = ballExternalPoint.getX() - nearCap.getItem2().getX();
            double diffY = ballExternalPoint.getY() - nearCap.getItem2().getY();

            double angle = Math.atan2(diffY, diffX) * 57.2957;
            float force = 1.2f;

            actionList.add(Utils.capActionInfo(angle, force, nearCap.getItem2().getCap_num()));
        }

        //Caps in front of the ball
        for(Cap cap: mTeamPosition.getItem2()){
            double angle = mFieldInfo.getRole() == 1? 0: 180;
            float force = 0.6f;

            actionList.add(Utils.capActionInfo(angle, force, cap.getCap_num()));
        }

        if(actionList.size() != 0){
            tuple = new Tuple<Boolean, ArrayList<JSONObject>>(true, actionList);
        }

        return tuple;
    }

    private void locateGoalPosts(int team){
        PlaygroundInfo playgroundInfo = mFieldInfo.getPlayground_info();

        if(team == 1) {
            mOwnValidPost = getValidPost(playgroundInfo.getRight_goal_posts());
            mRivalValidPost = getValidPost(playgroundInfo.getLeft_goal_posts());
        } else if (team == 2) {
            mOwnValidPost = getValidPost(playgroundInfo.getLeft_goal_posts());
            mRivalValidPost = getValidPost(playgroundInfo.getRight_goal_posts());
        }
    }

    private Tuple<ArrayList<Cap>, ArrayList<Cap>> getTeamPosition(ArrayList<Cap> activeTeam, ArrayList<Cap> inactiveTeam){
        ArrayList<Cap> backPosition = new ArrayList<Cap>();
        ArrayList<Cap> forePosition = new ArrayList<Cap>();
        Boolean isForward = Utils.anyForwardCap(inactiveTeam, mFieldInfo.getRole());

        switch (mFieldInfo.getRole()){
            case 1:
                for (Cap cap: activeTeam){
                    if(cap.getX() > mBall.getX()){
                        if(!isForward){
                            backPosition.add(cap);
                        }
                    } else {
                        forePosition.add(cap);
                    }
                }
                break;
            case 2:
                for (Cap cap: activeTeam){
                    if(cap.getX() < mBall.getX()){
                        if(!isForward){
                            backPosition.add(cap);
                        }
                    } else {
                        forePosition.add(cap);
                    }
                }
                break;
        }

        return new Tuple<ArrayList<Cap>, ArrayList<Cap>>(backPosition, forePosition);
    }

    private Tuple<Boolean, Cap> getMostNearCap(ArrayList<Cap> caps){
        Tuple<Boolean, Cap> result = new Tuple<Boolean, Cap>(false, null);
        Cap nearCap = null;
        double minDistance = 999999999;

        for (Cap cap: caps){
            float diffX = mBall.getX() - cap.getX();
            float diffY = mBall.getY() - cap.getY();
            double capDistance = Math.hypot(diffX, diffY);

            if(capDistance < minDistance){
                nearCap = cap;
                minDistance = capDistance;
            }
        }

        if(nearCap != null){
            result = new Tuple<Boolean, Cap>(true, nearCap);
        }

        return result;
    }

    private GoalValidPost getValidPost(GoalPost goalPost){
        float ballRadios = mFieldInfo.getPlayground_info().getBall_radius();

        Point2D topPoint = new Point2D.Float(goalPost.getTop_x(), goalPost.getTop_y() - (ballRadios * 1.5f));
        Point2D bottonPoint = new Point2D.Float(goalPost.getBottom_x(), goalPost.getBottom_y() + (ballRadios * 1.5f));

        return new GoalValidPost(topPoint, bottonPoint);
    }
}

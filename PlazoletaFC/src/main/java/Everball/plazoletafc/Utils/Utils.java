package Everball.plazoletafc.Utils;

import Everball.plazoletafc.Models.Ball;
import Everball.plazoletafc.Models.Cap;
import Everball.plazoletafc.Models.GoalValidPost;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Utils {

    public static JSONObject capActionInfo(double angle, float force, int cap_num){
        JSONObject result = new JSONObject();

        try {
            result.put("angle", angle);
            result.put("force", force);
            result.put("cap_num", cap_num);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Boolean anyForwardCap(ArrayList<Cap> inactiveCaps, int team){
        Boolean res = false;

        for(Cap cap: inactiveCaps){
            if(cap.getVel_x() < 0 && team == 1){
                res = true;
                break;
            } else if (cap.getVel_x() > 0 && team == 2){
                res = true;
                break;
            }
        }

        return res;
    }

    public static Point2D getBallExternalPoint(GoalValidPost post, Ball ball, float ballRadius){
        double diffX = ball.getX() - post.getTopPoint().getX();
        double diffY = ball.getY() - post.getTopPoint().getY();

        double angle = Math.atan2(diffY, diffX);

        double incrX = Math.cos(angle) * ballRadius;
        double incrY = Math.sin(angle) * ballRadius;

        return new Point2D.Double(ball.getX() + incrX, ball.getY() + incrY);
    }
}

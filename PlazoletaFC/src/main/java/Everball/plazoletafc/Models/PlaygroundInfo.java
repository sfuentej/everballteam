package Everball.plazoletafc.Models;

public class PlaygroundInfo {
    private FieldCorners field_corners;
    private GoalPost right_goal_posts;
    private GoalPost left_goal_posts;
    private float cap_radius;
    private float ball_radius;

    public PlaygroundInfo(FieldCorners field_corners, GoalPost right_goal_posts, GoalPost left_goal_posts,
                          float cap_radius, float ball_radius) {
        this.field_corners = field_corners;
        this.right_goal_posts = right_goal_posts;
        this.left_goal_posts = left_goal_posts;
        this.cap_radius = cap_radius;
        this.ball_radius = ball_radius;
    }

    public GoalPost getRight_goal_posts() {
        return right_goal_posts;
    }

    public GoalPost getLeft_goal_posts() {
        return left_goal_posts;
    }

    public float getCap_radius() {
        return cap_radius;
    }

    public float getBall_radius() {
        return ball_radius;
    }
}

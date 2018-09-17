package Everball.plazoletafc.Models;

public class GoalPost {
    private float bottom_x;
    private float bottom_y;
    private float top_x;
    private float top_y;

    public GoalPost(float bottom_x, float bottom_y, float top_x, float top_y) {
        this.bottom_x = bottom_x;
        this.bottom_y = bottom_y;
        this.top_x = top_x;
        this.top_y = top_y;
    }

    public float getBottom_x() {
        return bottom_x;
    }

    public float getBottom_y() {
        return bottom_y;
    }

    public float getTop_x() {
        return top_x;
    }

    public float getTop_y() {
        return top_y;
    }
}

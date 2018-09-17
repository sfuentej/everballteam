package Everball.plazoletafc.Models;

public class FieldCorners {
    private float bottom_left_x;
    private float bottom_left_y;
    private float top_left_x;
    private float top_left_y;
    private float bottom_right_x;
    private float bottom_right_y;
    private float top_right_x;
    private float top_right_y;

    public FieldCorners(float bottom_left_x, float bottom_left_y, float top_left_x, float top_left_y,
                        float bottom_right_x, float bottom_right_y, float top_right_x, float top_right_y) {
        this.bottom_left_x = bottom_left_x;
        this.bottom_left_y = bottom_left_y;
        this.top_left_x = top_left_x;
        this.top_left_y = top_left_y;
        this.bottom_right_x = bottom_right_x;
        this.bottom_right_y = bottom_right_y;
        this.top_right_x = top_right_x;
        this.top_right_y = top_right_y;
    }
}

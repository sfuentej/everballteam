package Everball.plazoletafc.Models;

import java.awt.geom.Point2D;

public class GoalValidPost {
    private Point2D topPoint;
    private Point2D bottomPoint;

    public GoalValidPost(Point2D topPoint, Point2D bottomPoint) {
        this.topPoint = topPoint;
        this.bottomPoint = bottomPoint;
    }

    public Point2D getTopPoint() {
        return topPoint;
    }

    public Point2D getBottomPoint() {
        return bottomPoint;
    }
}

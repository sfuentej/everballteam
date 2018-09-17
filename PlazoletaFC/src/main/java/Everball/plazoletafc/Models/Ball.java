package Everball.plazoletafc.Models;

public class Ball {
    private float x;
    private float y;
    private float vel_x;
    private float vel_y;
    private float cooldown;
    private int cap_num;
    private int anim_state;

    public Ball(float x, float y, float vel_x, float vel_y, float cooldown, int cap_num, int anim_state) {
        this.x = x;
        this.y = y;
        this.vel_x = vel_x;
        this.vel_y = vel_y;
        this.cooldown = cooldown;
        this.cap_num = cap_num;
        this.anim_state = anim_state;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVel_x() {
        return vel_x;
    }

    public float getVel_y() {
        return vel_y;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getCap_num() {
        return cap_num;
    }

    public int getAnim_state() {
        return anim_state;
    }
}

package Everball.plazoletafc.Models;

public class FieldInfo {
    private PlaygroundInfo playground_info;
    private int role;

    public FieldInfo(PlaygroundInfo playground_info, int role) {
        this.playground_info = playground_info;
        this.role = role;
    }

    public PlaygroundInfo getPlayground_info() {
        return playground_info;
    }

    public int getRole() {
        return role;
    }
}

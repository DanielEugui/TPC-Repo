package utils.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PositionResource {

    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;

    public PositionResource(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

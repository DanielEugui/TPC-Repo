package utils.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SizeResource {

    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

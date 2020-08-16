package utils.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementResource {

    @SerializedName("locator")
    @Expose
    private String locator;

    @SerializedName("position")
    @Expose
    private PositionResource position;

    @SerializedName("size")
    @Expose
    private SizeResource size;

    public String getLocator() {
        return locator;
    }

    public PositionResource getPosition() {
        return position;
    }

    public SizeResource getSize() {
        return size;
    }
}

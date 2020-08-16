package utils.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinderResource {

    @SerializedName("locator")
    @Expose
    private String locator;

    public String buildLocator(String elementName) {
        return locator.replace("_ELEMENT_NAME_", elementName);
    }
}

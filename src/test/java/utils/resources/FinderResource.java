package utils.resources;

public class FinderResource {

    private String locator;

    public String buildLocator(String elementName) {
        return locator.replace("_ELEMENT_NAME_", elementName);
    }
}

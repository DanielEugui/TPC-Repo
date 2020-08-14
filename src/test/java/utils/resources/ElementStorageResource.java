package utils.resources;

import java.util.ArrayList;

public class ElementStorageResource {

    private ArrayList<String> imports;
    private ArrayList<ElementResource> elements;
    private ArrayList<FinderResource> finders;

    /**
     * Let assume we have the following functions:
     *
     * load_json(filename) and get_elements(locator)
     *  I created load_json and get_elements methods here only to have a success compile
     */
    public ElementStorageResource load_json(String fileName) {
        return null;
    }
    public ArrayList<ElementResource> get_elements(String locator) {
        return null;
    }

    public ElementResource find_element(String elementName) {

        ElementResource elementFounded = null;
        int i = 0;
        while (i < this.finders.size() && elementFounded == null) {
            String locator = this.finders.get(i).buildLocator(elementName);
            elementFounded = get_elements(locator).get(0);
        }

        if (elementFounded == null) {
            int j = 0;
            while (j < this.imports.size() && elementFounded == null) {
                elementFounded = load_json(this.imports.get(j)).find_element(elementName);
            }
        }
        return elementFounded;
    }

    public ElementResource find_element_near_to(String elementName) {
        return null;
    }
}

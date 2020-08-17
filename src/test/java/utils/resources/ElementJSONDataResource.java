package utils.resources;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ElementJSONDataResource {

    @SerializedName("import")
    @Expose
    private ArrayList<String> imports;
    private Map<String, ElementResource> elements;
    @SerializedName("__FINDERS__")
    @Expose
    private ArrayList<FinderResource> finders;

    public void setElements(Map<String, ElementResource> elements) {
        this.elements = elements;
    }

    public ArrayList<String> getImports() {
        return imports;
    }

    public void setImports(ArrayList<String> imports) {
        this.imports = imports;
    }

    public Map<String, ElementResource> getElements() {
        return elements;
    }

    public void setFinders(ArrayList<FinderResource> finders) {
        this.finders = finders;
    }

    public ArrayList<FinderResource> getFinders() {
        return finders;
    }

    /**
     * Exercise 1:
     *
     * This method is used to find element objects by elementName
     * @param elementName element name
     * @return Element object
     */
    public ElementResource find_element(String elementName) {

        ElementResource elementFounded = null;
        elementFounded = this.elements.get(elementName);
        int i = 0;

        if (this.finders != null) {
            while (i < this.finders.size() && elementFounded == null) {
                String locator = this.finders.get(i).buildLocator(elementName);
                ArrayList<ElementResource> elements = get_elements(locator);
                if (!elements.isEmpty()) {
                    elementFounded = get_elements(locator).get(0);
                }
                i++;
            }
        }

        if (elementFounded == null) {
            if (this.imports != null) {
                int j = 0;
                while (j < this.imports.size() && elementFounded == null) {
                    ElementJSONDataResource dataLoadedResource = Utils.load_json(this.imports.get(j));

                    if (dataLoadedResource.finders != null) {
                        for (FinderResource finder : dataLoadedResource.getFinders()) {
                            String locator = finder.buildLocator(elementName);
                            if (!get_elements(locator).isEmpty()) {
                                elementFounded = get_elements(locator).get(0);
                            }
                        }
                        if (elementFounded == null ) {
                            elementFounded = dataLoadedResource.find_element(elementName);
                        }
                    }
                    j++;
                }
            }
        }
        return elementFounded;
    }

    /**
     * Exercise 2:
     *
     * This method is used to get (if exists) nearest element with a specific locator to elementA
     *
     * @param elementA element used as reference
     * @return Element that is nearest to elementA with same x position
     */
    public ElementResource find_element_near_to(ElementResource elementA, String locator) {
        ArrayList<ElementResource> elementsWithLocatorX = this.get_elements(locator);
        double shorterDistance = -1;
        ElementResource nearestElement = null;
        for (ElementResource element : elementsWithLocatorX) {
            if (element != elementA) {
                double newDistance = this.getShorterDistanceBetweenTwoElements(elementA, element);
                if (nearestElement == null || shorterDistance > newDistance) {
                    shorterDistance = newDistance;
                    nearestElement = element;
                }
            }
        }
        return nearestElement;
    }

    /**
     * Method used to get all element by locator
     *
     * get_elements(locator): receives a locator and returns a list of all element objects found with this locator.
     *
     * @param locator
     * @return
     */
    private ArrayList<ElementResource> get_elements(String locator) {

        ArrayList<ElementResource> res = new ArrayList<ElementResource>();
        Iterator<Map.Entry<String, ElementResource>> it = this.elements.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ElementResource> obj = it.next();
            if (obj.getValue().getLocator().equals(locator)) {
                res.add(obj.getValue());
            }
        }
        return res;
    }

    /**
     * This method is used to get distance between tow position (distance between two points)
     *
     * @param firstPos first position
     * @param secondPos second position
     * @return distance of tow points
     */
    private double getDistanceBetweenTwoPosition(PositionResource firstPos, PositionResource secondPos) {
        int dx = secondPos.getX() - firstPos.getX();
        int dy = secondPos.getY() - firstPos.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * This method is used to get all corner positions from a element
     *
     * @param element to get all corner positions
     * @return a list with all corner positions
     */
    private ArrayList<PositionResource> getCornerPositions(ElementResource element) {
        PositionResource originalPos = element.getPosition();
        SizeResource originalSize = element.getSize();
        Map<String, PositionResource> cornersMap = new HashMap<String, PositionResource>();

        PositionResource topLeftCorner = originalPos;
        PositionResource topRightCorner = new PositionResource(
                originalPos.getX() + originalSize.getWidth(),
                originalPos.getY()
        );
        PositionResource downLeftCorner = new PositionResource(
                originalPos.getX(),
                originalPos.getY() - originalSize.getHeight()
        );
        PositionResource downRightCorner = new PositionResource(
                originalPos.getX() + originalSize.getWidth(),
                originalPos.getY() - originalSize.getHeight()
        );

        ArrayList<PositionResource> corners = new ArrayList<PositionResource>();
        corners.add(topLeftCorner);
        corners.add(topRightCorner);
        corners.add(downLeftCorner);
        corners.add(downRightCorner);
        return corners;
    }

    /**
     * This methods is used to get the shorter distance between two elements
     * (Shorter distances of corners of elements)
     *
     * @param element1
     * @param element2
     * @return Shorter distance between corners of element1 and element2
     */
    private double getShorterDistanceBetweenTwoElements(ElementResource element1, ElementResource element2) {
        ArrayList<PositionResource> cornersOfElement1 = this.getCornerPositions(element1);
        ArrayList<PositionResource> cornersOfElement2 = this.getCornerPositions(element2);

        double shorterDistance = -1;

        for (PositionResource cornerPosOfElement1: cornersOfElement1) {
            for (PositionResource cornerPosOfElement2: cornersOfElement2) {
                double newDistance = this.getDistanceBetweenTwoPosition(cornerPosOfElement1, cornerPosOfElement2);
                if ((shorterDistance == -1) || (shorterDistance > newDistance)) {
                    shorterDistance = newDistance;
                }
            }
        }
        return shorterDistance;
    }
}

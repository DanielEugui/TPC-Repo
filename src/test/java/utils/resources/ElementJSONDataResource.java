package utils.resources;

import utils.Utils;

import java.util.ArrayList;
import java.util.Map;

public class ElementJSONDataResource {

    private ArrayList<String> imports;
    private Map<String, ElementResource> elements;
    private ArrayList<FinderResource> finders;

    public ElementJSONDataResource(ArrayList<String> imports, Map<String, ElementResource> elements, ArrayList<FinderResource> finders) {
        this.imports = imports;
        this.elements = elements;
        this.finders = finders;
    }

    public ArrayList<ElementResource> get_elements(String locator) {
        return null;
    }

    /**
     * Exercise 1:
     *
     * This method is used to find all element objects by elementName
     * @param elementName element name
     * @return Element object
     */
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
                elementFounded = Utils.load_json(this.imports.get(j)).find_element(elementName);
            }
        }
        return elementFounded;
    }

    /**
     * Exercise 2:
     *
     * find_element_near_to is used to get (if exists) element that contains locator as locator that is nearest to elementA
     * @param elementA element used as reference
     * @return Element that is nearest to elementA with same x position
     */
    public ElementResource find_element_near_to(ElementResource elementA, String locatorX) {
        ArrayList<ElementResource> elementsWithLocatorX = this.get_elements(locatorX);
        double shorterDistance = -1;
        ElementResource nearestElement = null;
        for (ElementResource element : elementsWithLocatorX) {
            double newDistance = this.getShorterDistanceBetweenTwoElements(elementA, element);
            if(nearestElement == null || shorterDistance > newDistance) {
                shorterDistance = newDistance;
                nearestElement = element;
            }
        }

        return nearestElement;
    }

    private double getDistanceBetweenTwoPosition(PositionResource firstPos, PositionResource secondPos) {
        int dx = secondPos.getX() - firstPos.getX();
        int dy = secondPos.getY() - firstPos.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * pensar en una mejor estructura para guardar los bordes (un Map puede ser bueno, como keys: top-left-corner...etc)
     */
    private ArrayList<PositionResource> getCornerPositions(ElementResource element) {
        PositionResource originalPos = element.getPosition();
        SizeResource originalSize = element.getSize();

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
        ArrayList<PositionResource> myList = new ArrayList<PositionResource>();
        myList.add(topLeftCorner);
        myList.add(topRightCorner);
        myList.add(downLeftCorner);
        myList.add(downRightCorner);
        return myList;
    }

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

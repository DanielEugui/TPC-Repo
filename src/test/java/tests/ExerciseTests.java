package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;
import utils.resources.ElementResource;
import utils.resources.ElementJSONDataResource;


public class ExerciseTests extends BaseTest {

    @Test
    void testFindElementMethod() {

        ElementJSONDataResource letsTalkFieldsElements = Utils.load_json("src/letsTalkFormElements.json");


        ElementResource roleField = letsTalkFieldsElements.find_element("role");
        ElementResource roleField_1 = letsTalkFieldsElements.find_element("field_1");
        Assert.assertEquals(
                roleField.getLocator(),
                roleField_1.getLocator(),
                "Different results doing a search using keyName and finding element_name"
        );

        Assert.assertTrue(
                letsTalkFieldsElements.find_element("submit") != null,
                "Send button was not found using imports files"
        );

        String send_button_locator = "//button[@type='submit']";
        ElementResource near_button = letsTalkFieldsElements.find_element_near_to(roleField, send_button_locator);
        Assert.assertEquals(
                near_button.getLocator(),
                send_button_locator,
                "Send button was not found using imports files"
        );

    }

}

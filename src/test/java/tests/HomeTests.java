package tests;

import org.testng.annotations.Test;
import utils.Utils;
import utils.resources.ElementResource;
import utils.resources.ElementJSONDataResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeTests extends BaseTest {

    @Test
    void testToValidateBasicProjectIsWorking() {

        ElementJSONDataResource elementJSONDataResource = Utils.load_json("src/myJson.json");
        ElementResource a = elementJSONDataResource.find_element("form-field-email");
        ElementResource b = elementJSONDataResource.find_element_near_to(a, "//div[@class = 'elementor-widget-container']//input[@id='form-field-email']");
    }
}

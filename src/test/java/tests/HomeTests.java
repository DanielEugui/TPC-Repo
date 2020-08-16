package tests;

import com.google.gson.*;
import org.testng.annotations.Test;
import utils.Utils;
import utils.resources.ElementResource;
import utils.resources.ElementJSONDataResource;
import utils.resources.FinderResource;

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
    }
}

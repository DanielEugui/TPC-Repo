package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import utils.resources.ElementJSONDataResource;
import utils.resources.ElementResource;
import utils.resources.FinderResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {

    private Utils() { }

    /**
     * Method used to load a Element json
     * @param json_path json path to be loaded
     * @return ElementJSONDataResource
     */
    public static ElementJSONDataResource load_json(String json_path) {
        Gson gson = new Gson();
        ElementJSONDataResource elementJSONDataResource = null;
        try {
            BufferedReader br_1 = new BufferedReader(new FileReader(json_path));
            BufferedReader br_2 = new BufferedReader(new FileReader(json_path));
            elementJSONDataResource = gson.fromJson(br_1, ElementJSONDataResource.class);

            JsonObject jsonObject = JsonParser.parseReader(br_2).getAsJsonObject();
            Iterator<Map.Entry<String, JsonElement>> it = jsonObject.entrySet().iterator();

            ArrayList<String> imports = null;
            Map<String, ElementResource> elementMap = new HashMap<String, ElementResource>();
            ArrayList<FinderResource> finders = null;

            while (it.hasNext()) {
                Map.Entry<String, JsonElement> obj = it.next();
                if (obj.getKey().equals("import") == false && obj.getKey().equals("__FINDERS__") == false) {
                    ElementResource element = gson.fromJson(obj.getValue(), ElementResource.class);
                    elementMap.put(obj.getKey(), element);
                }
            }

            elementJSONDataResource.setElements(elementMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementJSONDataResource;
    }
}

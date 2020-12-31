package com.pachetepachete.Controllers;

import com.pachetepachete.Application;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Iterator;

public class ReadController {
    private static ReadController readController;

    private ReadController() {
    }

    public static ReadController getInstance() {
        if (readController == null) {
            synchronized (ReadController.class) {
                if (readController == null) {
                    readController = new ReadController();
                }
            }
        }

        return readController;
    }

    public void readJSON(String path) {
        Application application = Application.getInstance();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employees = (JSONArray) jsonObject.get("employees");

            Iterator<JSONObject> iterator = employees.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

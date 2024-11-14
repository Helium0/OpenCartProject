package com.seleniumproject.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "dp")
    public String[] readJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader file = new FileReader(".\\src\\test\\resources\\login.json");
        Object obj = jsonParser.parse(file);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("userLogins");
        String arr [] = new String[jsonArray.size()];
        for (int i =0; i<jsonArray.size(); i++){
            JSONObject logins = (JSONObject) jsonArray.get(i);
            String email = (String) logins.get("email");
            String psw = (String) logins.get("password");
            arr[i] = email +","+psw;
        }
        return arr;
    }
}

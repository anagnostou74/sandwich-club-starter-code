package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichObjects = new JSONObject(json);

            JSONObject nameObject = sandwichObjects.getJSONObject("name");

            String mainNameObject = nameObject.getString("mainName");
            JSONArray alsoKnownAsArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                String alsoKnownAs = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(alsoKnownAs);
            }
            String placeOfOriginObject = sandwichObjects.getString("placeOfOrigin");
            String descriptionObject = sandwichObjects.getString("description");
            String imageObject = sandwichObjects.getString("image");
            JSONArray ingredientsArray = sandwichObjects.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                String ingredients = ingredientsArray.getString(i);
                ingredientsList.add(ingredients);
            }

            return new Sandwich(mainNameObject, alsoKnownAsList, placeOfOriginObject, descriptionObject, imageObject, ingredientsList);

        } catch (JSONException e) {
            Log.e(TAG, "JSON parsing error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

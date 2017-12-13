package com.wenjiang.wenbiao.fategrandorder.test;

import android.content.Context;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/22.
 */

public final class TestBottomData {
    private static TestBottomData data;
    private JSONObject bottomData = null;

    private TestBottomData(Context context){
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("bottom_data.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder contentBuilder = new StringBuilder();
            String content = null;
            while((content = reader.readLine()) != null){
                contentBuilder.append(content);
            }

            bottomData = new JSONObject(new JSONObject(contentBuilder.toString()).getJSONObject("data").toString());
        } catch (IOException e) {
            Logger.e(e.toString());
        } catch (JSONException e) {
            Logger.e(e.toString());
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Logger.e(e.toString());
                }
            }
        }
    }

    public static TestBottomData getInstance(Context context){
        if(data == null){
            data = new TestBottomData(context);
        }

        return data;
    }

    public String getTextColor(){
        String textColor = "";
        try {
            textColor =  bottomData.getString("textColor");
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return textColor;
    }

    public int getTextSize(){
        int textSize = 0;
        try {
            textSize =  bottomData.getInt("textSize");
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return textSize;
    }

    public String getDivideColor(){
        String divideColor = "";
        try {
            divideColor =  bottomData.getString("divideColor");
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return divideColor;
    }

    public List<String> getText(){
        List<String> textList = new ArrayList<>();
        try {
            String textStr = bottomData.getString("text");
            for(String text : textStr.split(",")){
                textList.add(text);
            }
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return textList;
    }

    public List<String> getType(){
        List<String> typeList = new ArrayList<>();
        try {
            String typeStr = bottomData.getString("jump");
            for(String type : typeStr.split(",")){
                typeList.add(type);
            }
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return typeList;
    }
}

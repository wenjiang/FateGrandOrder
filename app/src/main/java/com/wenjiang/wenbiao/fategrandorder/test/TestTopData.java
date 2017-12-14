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
 * Created by weber_zheng on 2017/12/8.
 */

public class TestTopData {
    private static TestTopData data;
    private JSONObject topData = null;
    private List<String> textList;

    private TestTopData(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("top_data.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder contentBuilder = new StringBuilder();
            String content = null;
            while ((content = reader.readLine()) != null) {
                contentBuilder.append(content);
            }

            topData = new JSONObject(new JSONObject(contentBuilder.toString()).getJSONObject("data").toString());
            textList = new ArrayList<>();
            String textStr = topData.getString("text");
            for (String text : textStr.split(",")) {
                textList.add(text);
            }
        } catch (IOException e) {
            Logger.e(e.toString());
        } catch (JSONException e) {
            Logger.e(e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Logger.e(e.toString());
                }
            }
        }
    }

    public static TestTopData getInstance(Context context) {
        if (data == null) {
            data = new TestTopData(context);
        }

        return data;
    }

    public List<String> getEvent() {
        List<String> eventList = new ArrayList<>();
        try {
            String eventStr = topData.getString("event");
            for (String text : eventStr.split(",")) {
                eventList.add(text);
            }
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return eventList;
    }

    public List<String> getSubText(int i) {
        List<String> subList = new ArrayList<>();
        try {
            String subStr = topData.getString("sub" + i);
            for (String subText : subStr.split(",")) {
                subList.add(subText);
            }
        } catch (JSONException e) {
            Logger.e(e.toString());
        }
        return subList;
    }

    public List<String> getGrandText() {
        List<String> grandList = new ArrayList<>();
        int count = getGrandCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                try {
                    String grandStr = topData.getString("grand" + i);
                    grandList.add(grandStr);
                } catch (JSONException e) {
                    Logger.e(e.toString());
                }
            }
        }

        return grandList;
    }

    public int getGrandCount() {
        int count = 0;
        if (topData.has("grandCount")) {
            try {
                count = topData.getInt("grandCount");
            } catch (JSONException e) {
                Logger.e(e.toString());
            }
        }

        return count;
    }

    public String getTextColor() {
        String textColor = "";
        try {
            textColor = topData.getString("textColor");
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return textColor;
    }

    public int getMainCount() {
        return textList.size();
    }

    public String getDivideColor() {
        String divideColor = "";
        try {
            divideColor = topData.getString("divideColor");
        } catch (JSONException e) {
            Logger.e(e.toString());
        }

        return divideColor;
    }

    public List<String> getText() {
        return textList;
    }
}

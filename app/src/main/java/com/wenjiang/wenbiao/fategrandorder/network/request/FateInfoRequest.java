package com.wenjiang.wenbiao.fategrandorder.network.request;

import com.google.gson.JsonArray;
import com.wenjiang.wenbiao.fategrandorder.network.CustomCallback;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by weber_zheng on 2017/11/21.
 */

public interface FateInfoRequest {
    @GET("/fate/star/:{star}")
    void getFateInfo(@Path("star") int star, CustomCallback<JsonArray> callback);
}

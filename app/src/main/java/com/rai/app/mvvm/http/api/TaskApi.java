package com.rai.app.mvvm.http.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by amitrai on 9/5/17.
 * this class is used for :- calling task api
 */

public interface TaskApi {
    @GET("/php/task_manager/v1/tasks")
    Observable<ResponseBody> getTasks(@Header("Authorization") String api_key);
}

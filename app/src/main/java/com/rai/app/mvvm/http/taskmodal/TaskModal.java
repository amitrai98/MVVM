package com.rai.app.mvvm.http.taskmodal;


import com.rai.app.mvvm.http.api.TaskApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amitrai on 9/5/17.
 * this class is used for :- call api for creating tasks.
 */

public class TaskModal {
    private String ServerUrl = "http://192.168.1.91";
    private TaskApi mService;
    private boolean mIsSearching;


    public TaskModal(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ServerUrl)
                .build();

        mService = retrofit.create(TaskApi.class);
    }

    public boolean isSearching() {

        return mIsSearching;
    }

//    public Observable<ResponseBody> searchBooks(String token) throws Exception{
////        if (mIsSearching) {
////            String string;
////            return Observable.just(string);
////        }
//
//        return mService.getTasks(token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(disposable -> {
//                    mIsSearching = true;
//                })
//                .doOnTerminate(() -> {
//                    mIsSearching = false;
//                });
//    }
}

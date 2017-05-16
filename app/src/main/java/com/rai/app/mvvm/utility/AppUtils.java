package com.rai.app.mvvm.utility;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.rai.app.mvvm.R;

import java.util.Random;

/**
 * Created by amitrai on 12/5/17.
 * this class is used for :- utility for the app
 */

public class AppUtils {

    private int prev = 0;

    public void changeColor(View view, Context context){
        Resources res = context.getResources();

        String[] color_array = res.getStringArray(R.array.values);

        int random = getRandom(0, color_array.length);

        while(random == prev){
            random=getRandom(0, color_array.length);
        }
        prev = random;

        String selected_color = color_array[random];
        int color = Color.parseColor(selected_color);
        view.setBackgroundColor(color);

    }

    private int getRandom(int min , int max){
        Random r = new Random();
        return  r.nextInt(max - 0) + 0;
    }
}

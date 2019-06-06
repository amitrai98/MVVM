package com.rai.app.mvvm.presentation.home;


import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.runtimepermission.PermissionHandler;
import com.app.runtimepermission.PermissionListener;
import com.rai.app.mvvm.MainActivity;
import com.rai.app.mvvm.R;
import com.rai.app.mvvm.presentation.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private final String TAG = getClass().getSimpleName();
    private Button btn_isCritical, btn_getSinglePermission, btn_getAllPermission;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        btn_isCritical = (Button) view.findViewById(R.id.btn_isCritical);
        btn_getSinglePermission = (Button) view.findViewById(R.id.btn_getSinglePermission);
        btn_getAllPermission = (Button) view.findViewById(R.id.btn_getAllPermission);

        btn_isCritical.setOnClickListener(this);
        btn_getSinglePermission.setOnClickListener(this);
        btn_getAllPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_getSinglePermission:
                getSinglePermission();
                break;
            case R.id.btn_isCritical:
                checkCriticalPermission();
                break;
            case R.id.btn_getAllPermission:
                getAllPermissions();
                break;

        }
    }


    /**
     * gets all the critical permission from the manifest.
     */
    private void getAllPermissions(){
        PermissionHandler.getAllPermissions(getActivity(), new PermissionListener() {
            @Override
            public void onGranted(String message) {
                Log.e(TAG, message);
                showSnackbar(message);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, errorMessage);
                showSnackbar(errorMessage);
            }

            @Override
            public void onRejected(String message) {
                Log.e(TAG, message);
                showSnackbar(message);
            }
        });
    }

    /**
     * checks if the permission is critical or not
     */
    private void checkCriticalPermission(){
        boolean value  = PermissionHandler.isCriticalPermission(
                Manifest.permission.INTERNET);

        Log.e(TAG , Manifest.permission.INTERNET+" "+value);
        showSnackbar(Manifest.permission.READ_CALENDAR+" "+value);

        boolean pValue  = PermissionHandler.isCriticalPermission(
                Manifest.permission.READ_CALENDAR);

        Log.e(TAG , Manifest.permission.READ_CALENDAR+" "+pValue);
        showSnackbar(Manifest.permission.READ_CALENDAR+" "+pValue);
    }

    /**
     * gets single permission
     */
    private void getSinglePermission(){

        if(PermissionHandler.isGranted(getActivity(), PermissionHandler.CALL_PHONE))
            showSnackbar("yes permission is granted");
        else{
            showSnackbar("no permission is not granged");
            PermissionHandler.getPermission(getActivity(), PermissionHandler.CALL_PHONE, new PermissionListener() {
                @Override
                public void onGranted(String message) {
                    Log.e(TAG, message);
                    showSnackbar(message);
                }

                @Override
                public void onError(String errorMessage) {
                    Log.e(TAG, errorMessage);
                    showSnackbar(errorMessage);
                }

                @Override
                public void onRejected(String message) {
                    Log.e(TAG, message);
                    showSnackbar(message);
                }
            });
        }



    }


    /**
     * shows message in snackbar
     * @param message to be shown
     */
    private void showSnackbar(String message){
        Snackbar snackbar = Snackbar
                .make(MainActivity.layout_snackbar, ""+message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


}

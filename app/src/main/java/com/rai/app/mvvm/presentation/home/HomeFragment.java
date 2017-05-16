package com.rai.app.mvvm.presentation.home;


import android.Manifest;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.runtimepermission.PermissionHandler;
import com.app.runtimepermission.PermissionListener;
import com.rai.app.mvvm.R;
import com.rai.app.mvvm.databinding.FragmentHomeBinding;
import com.rai.app.mvvm.modals.Task;
import com.rai.app.mvvm.presentation.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final String TAG = getClass().getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        PermissionHandler handler = new PermissionHandler();
        handler.getPermission(getActivity(), Manifest.permission.READ_CONTACTS, new PermissionListener() {
            @Override
            public void onGranted(String message) {
                Log.e(TAG, message);
            }

            @Override
            public void onRejected(String message) {
                Log.e(TAG, message);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        });

        Task task = new Task("have to go the the market", "pending");
        FragmentHomeBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_home);
        binding.setTask(task);
    }

}

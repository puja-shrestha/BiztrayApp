package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.puza.mobileui.R;

public class ProfileFragment extends Fragment {

    TextView txt_order_history;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_order_history = (TextView) view.findViewById(R.id.txt_order_history);
        txt_order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderHistoryFragment fragment = new OrderHistoryFragment();
                FragmentTransaction transaction = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
            }
        });

        return view;
    }
}



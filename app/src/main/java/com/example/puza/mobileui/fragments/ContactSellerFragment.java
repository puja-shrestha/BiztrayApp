package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.puza.mobileui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactSellerFragment extends Fragment {


    public ContactSellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_contact_seller, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.unit));
        myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        return view;
    }

}

package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.adapter.OrderHistoryRecycler;
import com.example.puza.mobileui.models.OrderHistoryItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {

    RecyclerView orderHistoryRecyclerView;
    private RecyclerView.LayoutManager oLayoutManager;
    OrderHistoryRecycler orderHistoryAdapter;
    List<OrderHistoryItems> orderHistoryItems;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        orderHistoryRecyclerView = (RecyclerView) view.findViewById(R.id.ordrHistoryRecyclerView);

        orderHistoryItems = getOrderHistoryItems();

        orderHistoryRecyclerView.setHasFixedSize(true);

        oLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        orderHistoryRecyclerView.setLayoutManager(oLayoutManager);
        orderHistoryAdapter = new OrderHistoryRecycler(getActivity(), orderHistoryItems);
        orderHistoryRecyclerView.setAdapter(orderHistoryAdapter);

        return view;
    }

    private List<OrderHistoryItems> getOrderHistoryItems() {
        orderHistoryItems = new ArrayList<OrderHistoryItems>();


        orderHistoryItems.add(new OrderHistoryItems("1-57-2018-06-17", "Sun 17 Jun 2018", "pending"));
        orderHistoryItems.add(new OrderHistoryItems("1-58-2018-06-09", "Tuesday 22 Jun 2018", "pending"));
        orderHistoryItems.add(new OrderHistoryItems("2-52-2018-06-16", "Wednesday 19 july 2018 ", "pending"));
        orderHistoryItems.add(new OrderHistoryItems("1-57-2018-06-17", "Sun 17 Jun 2018", "pending"));
        orderHistoryItems.add(new OrderHistoryItems("1-58-2018-06-09", "Tuesday 22 Jun 2018", "pending"));
        orderHistoryItems.add(new OrderHistoryItems("2-52-2018-06-16", "Wednesday 19 july 2018 ", "pending"));

        return orderHistoryItems;
    }

}

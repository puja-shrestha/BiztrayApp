package com.example.puza.mobileui.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.adapter.CartRecycler;
import com.example.puza.mobileui.models.CartItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    RecyclerView cartRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    CartRecycler cartAdapter;
    List<CartItems> cartItems;
    Button button;

    Dialog MyDialog;
    Button order_button;
    Button cancel;

    private ProgressBar cartProgressBar;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

//        cartProgressBar.setVisibility(View.VISIBLE);
//
//        cartProgressBar = (ProgressBar)view.findViewById(R.id.cartProgressBar);

        cartRecyclerView = (RecyclerView) view.findViewById(R.id.cartRecyclerView);

        cartItems = getCartItems();

        cartRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartAdapter = new CartRecycler(getActivity(), cartItems);
        cartRecyclerView.setAdapter(cartAdapter);

        button = (Button)view.findViewById(R.id.continuePayment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderFragment fragment = new OrderFragment();
                FragmentTransaction transaction = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
            }
        });



        order_button = (Button) view.findViewById(R.id.order_button);
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertdialogOrder();
            }
        });


        return view;
    }

    public void AlertdialogOrder(){
        MyDialog = new Dialog(getContext());
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.order_dialog);
        MyDialog.setTitle("My Custom Dialog");

        cancel = (Button)MyDialog.findViewById(R.id.cancel);

        cancel.setEnabled(true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });

        MyDialog.show();
    }

    private List<CartItems> getCartItems() {
        cartItems = new ArrayList<CartItems>();


        cartItems.add(new CartItems(R.drawable.one, "Pashmina Arts, made by Nepal Hand", "$1.99", "Small", "1pic"));
        cartItems.add(new CartItems(R.drawable.two, "Pashmina Arts, made by Nepal Hand", "$1.99", "Small", "1pic"));
        cartItems.add(new CartItems(R.drawable.one, "Pashmina Arts, made by Nepal Hand", "$1.99", "Small", "1pic"));

        return cartItems;
    }

}

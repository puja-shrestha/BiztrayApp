package com.example.puza.mobileui.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.fragments.OrderHistoryDetailsFragment;
import com.example.puza.mobileui.fragments.ShopItemsFragment;
import com.example.puza.mobileui.models.OrderHistoryItems;

import java.util.List;

public class OrderHistoryRecycler extends RecyclerView.Adapter<OrderHistoryRecycler.MyViewHolder>  {

    private List<OrderHistoryItems> itemList;
    Activity context;
    Fragment fragment;


    public OrderHistoryRecycler(Activity context, List<OrderHistoryItems> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView orderNo, orderDate, orderStatus;
        private CardView orderHistoryCardView;

        public MyViewHolder(View view) {
            super(view);

            orderNo = (TextView) view.findViewById(R.id.order_no);
            orderDate = (TextView) view.findViewById(R.id.order_date);
            orderStatus = (TextView) view.findViewById(R.id.order_status);

            orderHistoryCardView = (CardView) view.findViewById(R.id.orderHistoryCardView);
        }
    }

    @Override
    public OrderHistoryRecycler.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_order, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderHistoryRecycler.MyViewHolder holder, int position) {

        final OrderHistoryItems items = itemList.get(position);
        holder.orderNo.setText(items.getOrderNo());
        holder.orderDate.setText(items.getOrderDate());
        holder.orderStatus.setText(items.getOrderStatus());

        //itemView
        holder.orderHistoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transport("orderCard");
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private void transport(String fragmentName){
        fragment = null;
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();

        switch (fragmentName) {
            case "orderCard":

                fragment = new OrderHistoryDetailsFragment();
                break;
        }

        if (fragment != null){
            fragmentManager.beginTransaction().replace(R.id.frame_container,fragment).addToBackStack(null).commit();
        }
    }

}

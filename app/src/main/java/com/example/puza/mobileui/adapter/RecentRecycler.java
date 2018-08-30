package com.example.puza.mobileui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.fragments.ShopItemsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecentRecycler extends RecyclerView.Adapter<RecentRecycler.MyViewHolder> {

    private List<com.example.puza.mobileui.models.RecentDao.Message> mData;
    private final Context mContext;

    ProgressDialog  progressDialog;
    Fragment fragment;
    AlertDialog myDialog;

    public RecentRecycler(Activity context, List<com.example.puza.mobileui.models.RecentDao.Message> data) {
        this.mData = data;
        this.mContext = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, discount, description, price;
        private ImageView image1;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            image1 = (ImageView) view.findViewById(R.id.recentImage);
            title = (TextView) view.findViewById(R.id.recentTitle);
            description = (TextView) view.findViewById(R.id.recentDescription);
            discount = (TextView) view.findViewById(R.id.recentDiscount);
            price = (TextView) view.findViewById(R.id.recentPrice);

            cardView = (CardView) view.findViewById(R.id.featureSortCard);
        }
    }

    @Override
    public RecentRecycler.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_recent, parent, false);
        return new RecentRecycler.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentRecycler.MyViewHolder holder, int position) {

        final com.example.puza.mobileui.models.RecentDao.Message items = mData.get(position);

        if(items!= null) {
            holder.title.setText(items.getTitle());
            holder.description.setText(Html.fromHtml(items.getDescription()));
            if (items.getDiscount().equals("0")){
                holder.discount.setVisibility(View.GONE);
                holder.price.setText(items.getPrice());
            }else{
                holder.discount.setText(items.getDiscount());
                holder.price.setText(items.getPrice());
                holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

        }
        else{
            holder.title.setText("N/A");
            holder.description.setText("N/A");
            holder.price.setText("N/A");
            holder.discount.setVisibility(View.GONE);
        }

        if (items!=null){
            Picasso.with(mContext)
                    .load("https://www.biztray.com/products/thumb/"+items.getImage1())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.image1);
        }else{
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.image1);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transport("card");
            }
        });
    }

    @Override
    public int getItemCount() {

        return this.mData.size();
    }

    private void transport(String fragmentName){
        fragment = null;
        FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();

        switch (fragmentName) {
            case "card":

                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setTitle("Please wait"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();

                fragment = new ShopItemsFragment();
                break;
        }

        if (fragment != null){
            fragmentManager.beginTransaction().replace(R.id.frame_container,fragment).addToBackStack(null).commit();
        }
    }
}


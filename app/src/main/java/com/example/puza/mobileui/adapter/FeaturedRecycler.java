package com.example.puza.mobileui.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.fragments.ShopItemsFragment;
import com.example.puza.mobileui.models.FeaturedDao.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeaturedRecycler extends RecyclerView.Adapter<FeaturedRecycler.MyViewHolder> {

    private final Context mContext;
    private List<Message> mData;

//    Activity context;
    ProgressDialog  progressDialog;
    Fragment fragment;

    public FeaturedRecycler(Context context, List<Message> data) {
        mContext = context;
        mData = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView  tvtitle, tvTotal;
        private ImageView imageM;
        private CardView cardView;


        public MyViewHolder(View view) {
            super(view);

            Context context = itemView.getContext();
            tvtitle = (TextView) itemView.findViewById(R.id.fTitle);
            tvTotal = (TextView) itemView.findViewById(R.id.price);
            imageM = (ImageView) itemView.findViewById(R.id.image1);

            cardView = (CardView) itemView.findViewById(R.id.dateRecycler);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_featured, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Message items = mData.get(position);

        if(items.getProduct() != null) {
            holder.tvtitle.setText(items.getProduct().getTitle());
            holder.tvTotal.setText(items.getProduct().getPrice());
        }
        else{
            holder.tvtitle.setText("N/A");
            holder.tvTotal.setText("N/A");
        }

        if (items.getProduct()!=null){
            Picasso.with(mContext)
                    .load("https://www.biztray.com/products/thumb/"+items.getProduct().getImage1())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imageM);
        }else{
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imageM);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent.putExtra("id", mData.get(position).getId());
                transport("card");
            }
        });
    }

    @Override
    public int getItemCount() {
       Log.v("size", mData.size()+"");
        return mData.size();
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

package com.example.puza.mobileui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puza.mobileui.R;

import com.example.puza.mobileui.fragments.WebViewFragment;
import com.example.puza.mobileui.models.HotAtBiztrayDao.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotItemRecycler extends RecyclerView.Adapter<HotItemRecycler.MyViewHolder> {

    private List<Message> mData;
    private final Context mContext;

    public HotItemRecycler(Activity context, List<Message> data) {
        this.mData = data;
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id, link;
        private ImageView image1;

        public MyViewHolder(View view) {
            super(view);

            id = (TextView) view.findViewById(R.id.hotId);
            link = (TextView) view.findViewById(R.id.link);
            image1 = (ImageView) view.findViewById(R.id.imageHot);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_hot, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Message items = mData.get(position);

        if (items!=null){
            Picasso.with(mContext)
                    .load("https://www.biztray.com/products/feature/thumb/"+items.getImage())
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

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewFragment fragment = new WebViewFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                bundle.putString("link", items.getLink());
                fragment.setArguments(bundle);
                transaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {

        return this.mData.size();
    }
}

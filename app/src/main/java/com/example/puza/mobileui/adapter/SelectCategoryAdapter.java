package com.example.puza.mobileui.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.fragments.CartFragment;
import com.example.puza.mobileui.fragments.OrderFragment;
import com.example.puza.mobileui.fragments.PostNewAd.ChoosePicture;
import com.example.puza.mobileui.fragments.PostNewAd.FillAdDetails;
import com.example.puza.mobileui.models.ProductCategoriesDAO.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.MyViewHolder> {


    private List<Message> mData;
    private final Context mContext;

    ProgressDialog  progressDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SelectCategoryAdapter(Activity context, List<Message> data) {
        this.mData = data;
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView category_name;
        private RelativeLayout selectCategoryLayout;


        public MyViewHolder(View view) {
            super(view);

            category_name = (TextView) view.findViewById(R.id.categories);
            selectCategoryLayout = (RelativeLayout)view.findViewById(R.id.selectCategoryLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_category, parent, false);

        sharedPreferences = mContext.getSharedPreferences("pref", 0);
        editor = sharedPreferences.edit();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final Message items = mData.get(position);

        if(items!= null) {
            holder.category_name.setText(Html.fromHtml(items.getCategoryName()));
        }
        else{
            holder.category_name.setText("N/A");
        }

        holder.selectCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChoosePicture fragment = new ChoosePicture();
                FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);

                editor.putString("category_id", items.getId());
                editor.commit();

                transaction.commit();

            }
        });
    }



    @Override
    public int getItemCount() {
        return this.mData.size();
    }


}

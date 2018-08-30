package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;

import com.example.puza.mobileui.models.ProductDetailsDAO.ProductDetails;


import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopItemsFragment extends Fragment{

    RatingBar ratingBar;
    private TextView value;
    Button button;

    com.example.puza.mobileui.models.ProductDetailsDAO.Message productDetails;

    private TextView category_id,product_condition, negotiable, location, usedfor, price, detailurl, home_delivery, warranty;

    public ShopItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shop_item_layout, container, false);

        category_id = (TextView)view.findViewById(R.id.category_id);
        product_condition = (TextView)view.findViewById(R.id.product_condition);
        negotiable = (TextView)view.findViewById(R.id.negotiable);
        location = (TextView)view.findViewById(R.id.location);
        usedfor = (TextView)view.findViewById(R.id.usedfor);
        price = (TextView)view.findViewById(R.id.price);
        detailurl = (TextView)view.findViewById(R.id.detailurl);
        home_delivery = (TextView)view.findViewById(R.id.home_delivery);
        warranty = (TextView)view.findViewById(R.id.warranty);

        /*-----------------------------product details starts here-----------------------------------------*/

        ApiInterface apiProductDetails = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductDetails> callProductDetails = apiProductDetails.getProductDetails(getString(R.string.securitykey));
        Log.d("url", callProductDetails.request().url().toString());
        callProductDetails.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {

                if (response.body() != null && response.body().getStatus()){

                    productDetails = response.body().getMessage();
                    product_condition.setText(productDetails.getProductCondition());
                    negotiable.setText(productDetails.getNegotiable());
                    location.setText(productDetails.getLocation());
                    usedfor.setText(productDetails.getUsedfor());
                    price.setText(productDetails.getPrice());
                    detailurl.setText(productDetails.getDetailurl());
                    home_delivery.setText(productDetails.getHomeDelivery());
                    warranty.setText(productDetails.getWarranty());

                    Toast.makeText(getContext(), productDetails.getProductCondition(), Toast.LENGTH_SHORT).show();

                }

                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        /*-----------------------------product details ends here-----------------------------------------*/

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        //container that hold the values and integrate them with the spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.unit));
        myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        //rating Bar
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        value = (TextView) view.findViewById(R.id.value);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                value.setText("value is"+ rating);
            }
        });



        button = (Button)view.findViewById(R.id.contactButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactSellerFragment fragment = new ContactSellerFragment();
                FragmentTransaction transaction = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
            }
        });


        return view;
    }

}

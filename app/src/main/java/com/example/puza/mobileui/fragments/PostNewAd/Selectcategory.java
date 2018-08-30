package com.example.puza.mobileui.fragments.PostNewAd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;

import com.example.puza.mobileui.adapter.SelectCategoryAdapter;
import com.example.puza.mobileui.models.ProductCategoriesDAO.Message;
import com.example.puza.mobileui.models.ProductCategoriesDAO.ProductCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Selectcategory extends Fragment{

    RecyclerView selectCategoryRecyclerView;
    SelectCategoryAdapter selectCategoryAdapter;
    List<Message> selectCategoryItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.tab_select_category, container, false);

    selectCategory();

    return view;

    }

    public void selectCategory() {

        /*-----------------------------Products categories starts here-----------------------------------------*/

        ApiInterface apiCategories = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductCategories> callCategoriesItem = apiCategories.getProductCategories(getString(R.string.securitykey));
        Log.d("url", callCategoriesItem.request().url().toString());
        callCategoriesItem.enqueue(new Callback<ProductCategories>() {
            @Override
            public void onResponse(Call<ProductCategories> call, Response<ProductCategories> response) {

                ProductCategories categories = response.body();
                String status = categories.getStatus().toString();
                Log.v("status", status);
                if (status.equals("true")){

                    selectCategoryRecyclerView = (RecyclerView) getView().findViewById(R.id.categories_list);

                    selectCategoryRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    selectCategoryRecyclerView.setLayoutManager(mLayoutManager);
                    selectCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    selectCategoryItem = response.body().getMessage();
                    selectCategoryAdapter = new SelectCategoryAdapter(getActivity(), selectCategoryItem);

                    selectCategoryRecyclerView.setAdapter(selectCategoryAdapter);

                }
                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductCategories> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        /*-----------------------------Product Categories ends here-----------------------------------------*/

    }

}

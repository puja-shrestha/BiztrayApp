package com.example.puza.mobileui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;
import com.example.puza.mobileui.adapter.RecentToogleRecycler;
import com.example.puza.mobileui.adapter.RecentRecycler;
import com.example.puza.mobileui.models.RecentDao.RecentProducts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;

    /*-----------------recent item----------------*/
    RecyclerView recentItemRecyclerView;
    RecentRecycler recentItemAdapter;
    List<com.example.puza.mobileui.models.RecentDao.Message> recentsItems;
    /*---------------------------------------------------*/

    /*-----------------recent toggle item----------------*/
    RecyclerView recentToggleRecyclerView;
    RecentToogleRecycler recentToggleAdapter;
    List<com.example.puza.mobileui.models.RecentDao.Message> recentToogleItems;
    /*---------------------------------------------------*/


    ToggleButton toggleButton;

    Context context;
    TextView textView;
    TextView sbutton;
    TextView price;
    AlertDialog myDialog;

    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_feature_item, container, false);

        toggleButton = (ToggleButton)view.findViewById(R.id.toogle_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVibrateState();
            }
        });

        sbutton = (TextView) view.findViewById(R.id.button_sortby);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alertdialog();
            }
        });

        fetchAPI();

        return view;
    }

    public void Alertdialog(){

        final CharSequence[] items = {" Best Rating "," Price up "," Price down "," New "};

        final ArrayList seletedItems=new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Short by");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    // indexSelected contains the index of item (of which checkbox checked)
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
                        if (isChecked) {

                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {

                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        myDialog = builder.create();
        myDialog.show();
    }

    //Toogle
    public void changeVibrateState(){

        boolean checked = toggleButton.isChecked();
        if(checked)
        {
            mLayoutManager = new LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            recentToggleAdapter = new RecentToogleRecycler(getActivity(), recentToogleItems);
            recentToggleRecyclerView.setAdapter(recentToggleAdapter);
            recentToggleRecyclerView.setLayoutManager(mLayoutManager);

        }
        else
        {
            mLayoutManager = new LinearLayoutManager(
                    getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            recentItemRecyclerView.setLayoutManager(mLayoutManager);
            recentItemAdapter = new RecentRecycler(getActivity(), recentsItems);
            recentItemRecyclerView.setAdapter(recentItemAdapter);
        }
    }

    public void fetchAPI(){

        /*-----------------------------recent products starts here-----------------------------------------*/

        ApiInterface apiRecent = ApiClient.getClient().create(ApiInterface.class);
        Call<RecentProducts> callRecentProduct = apiRecent.getRecentProducts(getString(R.string.securitykey));
        Log.d("url", callRecentProduct.request().url().toString());
        callRecentProduct.enqueue(new Callback<RecentProducts>() {
            @Override
            public void onResponse(Call<RecentProducts> call, Response<RecentProducts> response) {

                if (response.body() != null && response.body().getStatus()){

                    recentItemRecyclerView = (RecyclerView) getView().findViewById(R.id.featuredSortItemRecyclerV);

                    recentItemRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    recentItemRecyclerView.setLayoutManager(mLayoutManager);
                    recentItemRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    recentsItems = response.body().getMessage();
                    recentItemAdapter = new RecentRecycler(getActivity(), recentsItems);

                    recentItemRecyclerView.setAdapter(recentItemAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RecentProducts> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        /*-----------------------------Recent products ends here-----------------------------------------*/

        /*-----------------------------recent toogle products starts here-----------------------------------------*/

        ApiInterface apiDecorative = ApiClient.getClient().create(ApiInterface.class);
        Call<RecentProducts> callDecorativeProduct = apiDecorative.getRecentProducts(getString(R.string.securitykey));
        Log.d("url", callDecorativeProduct.request().url().toString());
        callDecorativeProduct.enqueue(new Callback<RecentProducts>() {
            @Override
            public void onResponse(Call<RecentProducts> call, Response<RecentProducts> response) {

                if (response.body() != null && response.body().getStatus()){

                    recentToggleRecyclerView = (RecyclerView) getView().findViewById(R.id.featuredSortItemRecyclerV);

                    recentToggleRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    recentToggleRecyclerView.setLayoutManager(mLayoutManager);
                    recentToggleRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    recentToogleItems = response.body().getMessage();
                    recentToggleAdapter = new RecentToogleRecycler(getActivity(), recentToogleItems);

                    recentToggleRecyclerView.setAdapter(recentToggleAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RecentProducts> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        /*-----------------------------recent toogle products starts here-----------------------------------------*/

    }
}


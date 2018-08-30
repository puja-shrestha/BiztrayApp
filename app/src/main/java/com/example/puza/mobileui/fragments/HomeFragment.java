package com.example.puza.mobileui.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;

import com.example.puza.mobileui.adapter.RecentRecycler;
import com.example.puza.mobileui.adapter.FeaturedRecycler;
import com.example.puza.mobileui.adapter.CategoryAdapter;
import com.example.puza.mobileui.adapter.HotItemRecycler;
import com.example.puza.mobileui.models.FeaturedDao.Example;
import com.example.puza.mobileui.models.FeaturedDao.Message;
import com.example.puza.mobileui.models.HotAtBiztrayDao.HBiztray;
import com.example.puza.mobileui.models.ProductCategoriesDAO.ProductCategories;
import com.example.puza.mobileui.models.RecentDao.RecentProducts;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //slider
    private SliderLayout mDemoSlider;
    private LinearLayout mLinearLayout;

    //button
    Button mbutton;
    ProgressDialog progressDialog;

    RecyclerView cRecyclerView;
    CategoryAdapter categoryAdapter;
    List<com.example.puza.mobileui.models.ProductCategoriesDAO.Message> categoryItem;

    /*---------------Home items----------------------*/

    /*---------------featured items----------------------*/
    RecyclerView featuredRecyclerView;
    FeaturedRecycler featuredAdapter;
    List<Message> featuredItems;
    /*---------------------------------------------------*/

    /*-------------------Hot Items-------------------*/
    RecyclerView hotItemRecyclerView;
    HotItemRecycler hotItemAdapter;
    List<com.example.puza.mobileui.models.HotAtBiztrayDao.Message> hotItems;
    /*---------------------------------------------------*/

    /*-----------------Recent sort item----------------*/
    RecyclerView recentRecyclerView;
    RecentRecycler recentAdapter;
    List<com.example.puza.mobileui.models.RecentDao.Message> recentItems;
    /*---------------------------------------------------*/

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

        mbutton= (Button) view.findViewById(R.id.shop_now_button);

        //slider
        mDemoSlider = (SliderLayout)view.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.pagesContainer);
        setupSlider();

        fetchApi();

        return view;
    }

    private void fetchApi() {

        /*-----------------------------Products categories starts here-----------------------------------------*/

        ApiInterface apiCategories = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductCategories> callCategoriesItem = apiCategories.getProductCategories(getString(R.string.securitykey));
        Log.d("url", callCategoriesItem.request().url().toString());
        callCategoriesItem.enqueue(new Callback<ProductCategories>() {
            @Override
            public void onResponse(Call<ProductCategories> call, Response<ProductCategories> response) {

                if (response.body() != null && response.body().getStatus()){

                    cRecyclerView = (RecyclerView) getView().findViewById(R.id.homeRecycler);

                    cRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false);
                    cRecyclerView.setLayoutManager(mLayoutManager);
                    cRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    categoryItem = response.body().getMessage();
                    categoryAdapter = new CategoryAdapter(getActivity(), categoryItem);

                    cRecyclerView.setAdapter(categoryAdapter);
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


        /*-----------------------------Featured products starts here-----------------------------------------*/
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiService.getFeaturedProducts(getString(R.string.securitykey));
        Log.d("url", call.request().url().toString());
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.body() != null && response.body().getStatus()){

                    featuredRecyclerView = (RecyclerView) getView().findViewById(R.id.featuredRecycler);

                    featuredRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false);
                    featuredRecyclerView.setLayoutManager(mLayoutManager);
                    featuredRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    featuredItems = response.body().getMessage();
                    featuredAdapter = new FeaturedRecycler(getContext(), featuredItems);

                    featuredRecyclerView.setAdapter(featuredAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        /*-----------------------------Featured products ends here-----------------------------------------*/


        /*-----------------------------Hot products starts here-----------------------------------------*/

        ApiInterface apiHot = ApiClient.getClient().create(ApiInterface.class);
        Call<HBiztray> callHotItem = apiHot.getHotItem(getString(R.string.securitykey));
        Log.d("url", callHotItem.request().url().toString());
        callHotItem.enqueue(new Callback<HBiztray>() {
            @Override
            public void onResponse(Call<HBiztray> call, Response<HBiztray> response) {

                if (response.body() != null && response.body().getStatus()){

                    hotItemRecyclerView = (RecyclerView) getView().findViewById(R.id.hotRecycler);

                    hotItemRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false);
                    hotItemRecyclerView.setLayoutManager(mLayoutManager);
                    hotItemRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    hotItems = response.body().getMessage();
                    hotItemAdapter = new HotItemRecycler(getActivity(), hotItems);

                    hotItemRecyclerView.setAdapter(hotItemAdapter);
                }
                else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HBiztray> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        /*-----------------------------Hot products ends here-----------------------------------------*/

        /*-----------------------------Recent products starts here-----------------------------------------*/

        ApiInterface apiRecent = ApiClient.getClient().create(ApiInterface.class);
        Call<RecentProducts> callRecentProduct = apiRecent.getRecentProducts(getString(R.string.securitykey));
        Log.d("url", callRecentProduct.request().url().toString());
        callRecentProduct.enqueue(new Callback<RecentProducts>() {
            @Override
            public void onResponse(Call<RecentProducts> call, Response<RecentProducts> response) {

                if (response.body() != null && response.body().getStatus()){

                    recentRecyclerView = (RecyclerView) getView().findViewById(R.id.recentRecycler);

                    recentRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    recentRecyclerView.setLayoutManager(mLayoutManager);
                    recentRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    recentItems = response.body().getMessage();
                    recentAdapter = new RecentRecycler(getActivity(), recentItems);
                    recentAdapter.setHasStableIds(true);

                    recentRecyclerView.setHasFixedSize(true);
                    recentRecyclerView.setAdapter(recentAdapter);
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

    }

    //slider
    private void setupSlider() {
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Pashmina",R.drawable.banner3);
        file_maps.put("Health Care",R.drawable.banner2);
        file_maps.put("Massage and Spa",R.drawable.banner3);
        file_maps.put("Hair Products", R.drawable.banner2);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(2000);
    }

}

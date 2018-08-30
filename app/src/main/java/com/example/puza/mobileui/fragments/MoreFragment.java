package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.puza.mobileui.R;
import com.example.puza.mobileui.adapter.ViewPagerAdapter;
import com.example.puza.mobileui.fragments.PostNewAd.ChoosePicture;
import com.example.puza.mobileui.fragments.PostNewAd.FillAdDetails;
import com.example.puza.mobileui.fragments.PostNewAd.Selectcategory;

public class MoreFragment extends Fragment {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    //Fragments
    Selectcategory selectcategoryFragment;
    ChoosePicture choosePictureFragment;
    FillAdDetails fillAdDetailsFragment;

    String[] tabTitle={"SELECT CATEGORY","CHOOSE PICTURE","FILL AD DETAILS"};
    int[] unreadCount={0,5,0};

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        selectcategoryFragment =new Selectcategory();
        choosePictureFragment =new ChoosePicture();
        fillAdDetailsFragment =new FillAdDetails();
        adapter.addFragment(selectcategoryFragment,"Select Category");
        adapter.addFragment(choosePictureFragment,"Choose Picture");
        adapter.addFragment(fillAdDetailsFragment,"Fill AD Details");
        viewPager.setAdapter(adapter);
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_title.setText(tabTitle[pos]);
        if(unreadCount[pos]>0)
        {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(""+unreadCount[pos]);
        }
        else
            tv_count.setVisibility(View.GONE);


        return view;
    }

}

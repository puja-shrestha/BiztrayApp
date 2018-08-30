package com.example.puza.mobileui.fragments.PostNewAd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puza.mobileui.Interface.ApiInterface;
import com.example.puza.mobileui.Manager.ApiClient;
import com.example.puza.mobileui.R;
import com.example.puza.mobileui.models.PostAdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by My PC on 11/9/2017.
 */

public class FillAdDetails extends Fragment {

    private String  user_id, category_id, radiobuttonOne, radioButtonTwo, radioButtonThree, radioButtonFour;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;

    private TextView negotiable, product_condition;

    private EditText title, description, price, usedFor, detailurl;

    private Spinner runtime, adtype, location, currency, discount;

    private Button submit;

    private ImageView image1;

    SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fill_ad_details, container, false);

        pref = getContext().getSharedPreferences("pref", 0);

        user_id = pref.getString("user_id", "");
        category_id = pref.getString("category_id", "");

        Log.v("category_id", category_id);

        negotiable = (TextView)view.findViewById(R.id.negotiable);
        product_condition = (TextView)view.findViewById(R.id.product_condition);

        title = (EditText)view.findViewById(R.id.title);
        description = (EditText)view.findViewById(R.id.description);
        price = (EditText)view.findViewById(R.id.price);

        usedFor = (EditText)view.findViewById(R.id.usedFor);
        detailurl = (EditText)view.findViewById(R.id.detailUrl);


        /*-----------radio button--------------------------*/
        radioGroup1= (RadioGroup)view.findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                radiobuttonOne = radioButton.getText().toString();
            }
        });

        radioGroup2= (RadioGroup)view.findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                radioButtonTwo = radioButton.getText().toString();

                Log.v("home", radioButtonTwo);
            }
        });

        radioGroup3 = (RadioGroup)view.findViewById(R.id.radioGroup3);
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                radioButtonThree = radioButton.getText().toString();
            }
        });

        radioGroup4= (RadioGroup)view.findViewById(R.id.radioGroup4);
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                radioButtonFour = radioButton.getText().toString();
            }
        });

        /*----------------------------------------------------------*/


        /*--------------------spinner one----------------------------*/
        runtime = view.findViewById(R.id.runtime);
        String[] items2 = new String[]{"1 month", "2 months", "3 months","5 months"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runtime.setAdapter(adapter2);
        /*--------------------spinner one----------------------------*/

        /*--------------------spinner two----------------------------*/
        adtype = view.findViewById(R.id.adtype);
        String[] items3 = new String[]{"I will negotiate with my buyer directly. (I don't want online payment", "I want to sell my product through biztray.com (online payment)"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items3);
        adtype.setAdapter(adapter3);
        /*--------------------spinner two----------------------------*/

        /*--------------------spinner three----------------------------*/
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        location = view.findViewById(R.id.location);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        location.setAdapter(adapter4);
        /*--------------------spinner three----------------------------*/

        /*--------------------spinner four----------------------------*/
        currency = view.findViewById(R.id.currency);
        String[] items5 = new String[]{"NRS", "USD", "EUR", "INR"};

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items5);
        currency.setAdapter(adapter5);
        /*--------------------spinner four----------------------------*/

        /*--------------------spinner five----------------------------*/
        discount= view.findViewById(R.id.discount);
        String[] items6 = new String[]{"Select Discount", "1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%"};

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, items6);

        discount.setAdapter(adapter6);
        /*--------------------spinner five----------------------------*/


         submit = (Button)view.findViewById(R.id.submit);
         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 postUserData();

             }
         });

        return view;
    }


    private void postUserData(){

        ApiInterface postAddApi = ApiClient.getClient().create(ApiInterface.class);

        Call<PostAdd> call = postAddApi.createPostAdd(getString(R.string.securitykey),
                user_id,
                category_id,
                title.getText().toString(),
                description.getText().toString(),
                runtime.getSelectedItem().toString(),
                adtype.getSelectedItem().toString(),
                location.getSelectedItem().toString(),
                price.getText().toString().toString(),
                currency.getSelectedItem().toString(),
                discount.getSelectedItem().toString(),
                radiobuttonOne,
                radioButtonTwo,
                radioButtonThree,
                radioButtonFour,
                usedFor.getText().toString(),
                detailurl.getText().toString());

        call.enqueue(new Callback<PostAdd>() {
            @Override
            public void onResponse(Call<PostAdd> call, Response<PostAdd> response) {
//                Log.d("url",call.request().url().toString());

                //Toast.makeText(RegisterActivity.this,call.request().url().toString(),Toast.LENGTH_LONG).show();

                PostAdd postAdd = response.body();

                if (response.isSuccessful() && postAdd != null) {
                    //Log.v("address", registeruser.getAddress().toString());
                    Toast.makeText(getContext(),response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(),response.body().getMessage().toString(),Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(),
                            String.format("Response is %s", String.valueOf(response.code()))
                            , Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<PostAdd> call, Throwable t) {

                Toast.makeText(getContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

}

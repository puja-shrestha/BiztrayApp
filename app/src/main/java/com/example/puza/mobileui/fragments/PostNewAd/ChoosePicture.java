package com.example.puza.mobileui.fragments.PostNewAd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puza.mobileui.R;

import static android.app.Activity.RESULT_OK;

public class ChoosePicture extends Fragment {

    private ImageView image1, image2,image3,choose1, choose2, choose3;
    private TextView chooseNewPicture1;

    private Bitmap bitmap;
    private Button button;

    private String  user_id, category_id;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_choose_picture, container, false);

        pref = getContext().getSharedPreferences("pref", 0);
        editor = pref.edit();

        user_id = pref.getString("user_id", "");
        category_id = pref.getString("category_id", "");

        button = (Button)view.findViewById(R.id.img_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FillAdDetails fragment = new FillAdDetails();
                FragmentTransaction transaction = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);

                editor.putString("category_id", String.valueOf(getId()));
                editor.commit();

                transaction.commit();
            }
        });

        image1 = (ImageView)view.findViewById(R.id.image1);
        image2 = (ImageView)view.findViewById(R.id.image2);
        image3 = (ImageView)view.findViewById(R.id.image3);

        chooseNewPicture1 = (TextView)view.findViewById(R.id.chooseNewPicture1);
        choose1 = (ImageView) view.findViewById(R.id.choose1);
        choose2 = (ImageView) view.findViewById(R.id.choose2);
        choose3 = (ImageView) view.findViewById(R.id.choose3);

        chooseNewPicture1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGallery1();
            }
        });

        choose1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGallery1();
            }
        });

        choose2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGallery2();
            }
        });

        choose3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGallery3();
            }
        });

        return view;
    }

    private void openGallery1(){
        Intent pickPhoto1 = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto1 , 0);
    }

    private void openGallery2(){
        Intent pickPhoto2 = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto2 , 1);
    }

    private void openGallery3(){
        Intent pickPhoto3 = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto3 , 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    image1.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage1 = data.getData();
                    image2.setImageURI(selectedImage1);
                }
                break;

            case 2:
                if(resultCode == RESULT_OK){
                    Uri selectedImage2 = data.getData();
                    image3.setImageURI(selectedImage2);
                }
                break;
        }
    }
}
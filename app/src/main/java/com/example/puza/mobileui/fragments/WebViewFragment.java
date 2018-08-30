package com.example.puza.mobileui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.puza.mobileui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    private WebView webview;
    String link;

    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        webview = (WebView)view.findViewById(R.id.webview);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
             link= bundle.getString("link", "");
        }

        webview.loadUrl(link);

        return view;
    }

}

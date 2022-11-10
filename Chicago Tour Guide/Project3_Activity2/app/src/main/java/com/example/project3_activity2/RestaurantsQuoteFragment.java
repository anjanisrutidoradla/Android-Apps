package com.example.project3_activity2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;



public class RestaurantsQuoteFragment extends Fragment {
    private static final String TAG = "QuotesFragment";
    //Creating a webview
    private WebView rQuoteView = null;
    private int rCurrIdx = -1;
    private int rQuoteArrayLen;
    private ListViewModel model;
    int getShownIndex() {
        return rCurrIdx;
    }

    void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= rQuoteArrayLen)
            return;
        rCurrIdx = newIndex;
        //Loading Urls and retrieving data
        rQuoteView.loadUrl(RestaurantsActivity.rQuoteArray[rCurrIdx]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_restaurants_quote_fragment, container, false);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        //Working on WebView.
        rQuoteView = (WebView) getActivity().findViewById(R.id.quoteView);
        WebSettings webSettings = rQuoteView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        rQuoteView.setWebViewClient(new WebViewClient());
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            //if (item == mCurrIdx || item < 0 || item >= mQuoteArrayLen)
            //    return;
            rCurrIdx = item;
            rQuoteView.loadUrl(RestaurantsActivity.rQuoteArray[rCurrIdx]);

        });
        rQuoteArrayLen = RestaurantsActivity.rQuoteArray.length;
    }
//    public void onReceivedError(WebView localWebView, int errorCode, String description, String failingUrl)
//    {
//        if (errorCode == neededErrorCode)
//        {
//            hideErrorPage(localWebView);
//        }


}
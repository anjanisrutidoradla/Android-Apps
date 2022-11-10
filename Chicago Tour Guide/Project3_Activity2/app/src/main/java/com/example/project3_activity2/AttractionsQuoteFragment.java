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



public class AttractionsQuoteFragment extends Fragment {
    private static final String TAG = "QuotesFragment";

    private WebView mQuoteView = null;
    private int mCurrIdx = -1;
    private int mQuoteArrayLen;
    private ListViewModel model;
    int getShownIndex() {
        return mCurrIdx;
    }

    void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mQuoteArrayLen)
            return;
        mCurrIdx = newIndex;
        mQuoteView.loadUrl(AttractionsActivity.mQuoteArray[mCurrIdx]);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_quotes_fragment, container, false);
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mQuoteView = (WebView) getActivity().findViewById(R.id.quoteView);
        WebSettings webSettings = mQuoteView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mQuoteView.setWebViewClient(new WebViewClient());
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {

            //if (item == mCurrIdx || item < 0 || item >= mQuoteArrayLen)
            //    return;
            mCurrIdx = item;
            mQuoteView.loadUrl(AttractionsActivity.mQuoteArray[mCurrIdx]);

        });


        mQuoteArrayLen = AttractionsActivity.mQuoteArray.length;
    }
//    public void onReceivedError(WebView localWebView, int errorCode, String description, String failingUrl)
//    {
//        if (errorCode == neededErrorCode)
//        {
//            hideErrorPage(localWebView);
//        }


}
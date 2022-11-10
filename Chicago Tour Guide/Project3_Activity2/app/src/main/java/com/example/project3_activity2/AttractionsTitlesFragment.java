package com.example.project3_activity2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;


public class AttractionsTitlesFragment extends ListFragment {
    private static final String TAG = "TitlesFragment";
    private ListViewModel model;

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        model.selectItem(pos);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }
    //View on creating Viewmodel. Inshort, accessing it.
    @Override
    public void onViewCreated(View view, Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onViewCreated(view, savedState);
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        setListAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.activity_titles_fragment, AttractionsActivity.mTitleArray));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


}
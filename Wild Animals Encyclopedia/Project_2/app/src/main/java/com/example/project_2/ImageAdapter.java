package com.example.project_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
//Create an ImageAdapter
public class ImageAdapter extends BaseAdapter {
    //IDs of image and context
    private Context mContext;
    private List<Integer> mThumbIds;
    private List<String> mThumbName;

    public ImageAdapter(Context c, List<Integer> ids, List<String> name) {
        mContext = c;
        this.mThumbIds = ids;
        this.mThumbName=name;
    }
    @Override
    public int getCount() {
        return mThumbIds.size();
    }
    @Override
    //Returning the data item at position

    public Object getItem(int position) {
        return mThumbIds.get(position);
    }
    @Override

    public long getItemId(int position) {
        return mThumbIds.get(position);
    }
    @Override
    // Returning an ImageView for every item

    public View getView(int position, View convertView, ViewGroup parent) {

        View grid_item=convertView;
        if(grid_item==null){
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid_item=inflater.inflate(R.layout.item,null);
            //IDs of the ImageView and TextView

            ImageView imageView=(ImageView) grid_item.findViewById(R.id.image);
            TextView textView=(TextView) grid_item.findViewById(R.id.name_animal);
            imageView.setImageResource(mThumbIds.get(position));
            textView.setText(mThumbName.get(position));
        }

        return grid_item;
    }


}

package com.finalproject.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.finalproject.R;
import com.finalproject.helper.NewspaperHelper;
import com.finalproject.model.Newspaper;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class NewspaperAdapter  extends BaseAdapter {
    private List<Newspaper> items;
    private Activity activity;

    public NewspaperAdapter(Activity activity, List<Newspaper> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Newspaper itemCurrent = items.get(position);
        setIsFollow(itemCurrent);
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_newspaper, null);
//        set name of menu
        TextView txtView = (TextView) convertView.findViewById(R.id.txtTitle);
        txtView.setText(itemCurrent.getTitle());

//        set view of image
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imgReview);
        Picasso.with(activity).load(itemCurrent.getThumb_art()).fit().centerCrop().into(imgView);
//        set butotn follow click
        clickFollow(itemCurrent,convertView);
        return convertView;
    }

//    set event click
    public void clickFollow(final Newspaper itemCurrent, View convertView){
        final Button btnFollow = (Button) convertView.findViewById(R.id.btnFollow);
        if(itemCurrent.isFollow()){
            btnFollow.setBackgroundResource(R.drawable.heart);
        }else{
            btnFollow.setBackgroundResource(R.drawable.heart2);
        }
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewspaperHelper newspaperHelper = new NewspaperHelper(activity);
                itemCurrent.setFollow(!itemCurrent.isFollow());
                if(itemCurrent.isFollow()){
                    btnFollow.setBackgroundResource(R.drawable.heart);
                }else{
                    btnFollow.setBackgroundResource(R.drawable.heart2);
                }
                if(itemCurrent.isFollow()){
                    newspaperHelper.insert(itemCurrent);
                    v.setBackgroundResource(R.drawable.heart);
                }else{
                    newspaperHelper.delete(itemCurrent);
                }

            }
        });
    }

    public void setIsFollow(Newspaper newspaper){
        NewspaperHelper newspaperHelper = new NewspaperHelper(activity);
        Newspaper mNewspaper = newspaperHelper.get(newspaper.getUrl());
        if(mNewspaper != null){
            newspaper.setFollow(true);
        }else{
            newspaper.setFollow(false);
        }
    }

}

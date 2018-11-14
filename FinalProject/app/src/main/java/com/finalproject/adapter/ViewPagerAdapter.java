package com.finalproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finalproject.model.Image;
import com.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Image> listImage;
    private LayoutInflater inflater;
    private Context context;

    public ViewPagerAdapter(Context context, List<Image> listImage) {
        this.listImage = listImage;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        chuyen file xml thanh view
        View view = inflater.inflate(R.layout.item_viewpager_newspaper,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgDetail);
        TextView textView = (TextView) view.findViewById(R.id.txtDetail);
//        truyen du lieu
        Image image = listImage.get(position);
        Picasso.with(context).load(image.getUrl_img()).fit().centerCrop().into(imageView);
        textView.setText(image.getContent_img());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}

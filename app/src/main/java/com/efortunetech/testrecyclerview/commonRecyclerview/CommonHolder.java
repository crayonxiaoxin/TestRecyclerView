package com.efortunetech.testrecyclerview.commonRecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * CommonHolder for RecyclerView
 * Created by Lau on 2018/8/13.
 */

public class CommonHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public CommonHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    /**
     * 从稀疏数组中取出对应的view，没有则findViewById添加到稀疏数组中（类似holder复用）
     * @param resId     资源id
     * @param <T>       任意类型view
     * @return
     */
    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public void setBackGroundColor(int color) {
        itemView.setBackgroundColor(color);
    }

    public CommonHolder setText(int resId, String text) {
        TextView textView = getView(resId);
        textView.setText(text);
        return this;
    }

    public CommonHolder setImageResource(int resId, int imageId) {
        ImageView imageView = getView(resId);
        imageView.setImageResource(imageId);
        return this;
    }

    public CommonHolder setImageURL(int resId,String url){
        ImageView imageView = getView(resId);
        Picasso.get().load(url).resize(200,200).into(imageView);
        return this;
    }

    // ... and so on.
}

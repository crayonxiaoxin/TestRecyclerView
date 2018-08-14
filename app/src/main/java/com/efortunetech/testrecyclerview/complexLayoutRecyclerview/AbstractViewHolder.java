package com.efortunetech.testrecyclerview.complexLayoutRecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lau on 2018/8/10.
 */

public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setBindHolder(T model);
}

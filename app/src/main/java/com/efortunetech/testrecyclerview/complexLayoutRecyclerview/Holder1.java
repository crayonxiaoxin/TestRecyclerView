package com.efortunetech.testrecyclerview.complexLayoutRecyclerview;

import android.view.View;
import android.widget.TextView;

import com.efortunetech.testrecyclerview.R;

/**
 * Created by Lau on 2018/8/10.
 */

public class Holder1 extends AbstractViewHolder<TestBean1> {

    private final TextView name;

    public Holder1(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
    }

    @Override
    public void setBindHolder(TestBean1 model) {
        name.setText(model.name);
        itemView.setBackgroundColor(model.color);
    }

}

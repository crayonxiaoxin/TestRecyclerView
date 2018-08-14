package com.efortunetech.testrecyclerview.complexLayoutRecyclerview;

import android.view.View;
import android.widget.TextView;

import com.efortunetech.testrecyclerview.R;

/**
 * Created by Lau on 2018/8/10.
 */

public class Holder2 extends AbstractViewHolder<TestBean2> {

    private final TextView name;
    private final TextView content;

    public Holder2(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        content = itemView.findViewById(R.id.content);
    }

    @Override
    public void setBindHolder(TestBean2 model) {
        name.setText(model.name);
        content.setText(model.content);
        itemView.setBackgroundColor(model.color);
    }
}

package com.efortunetech.testrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.efortunetech.testrecyclerview.commonRecyclerview.CommonAdapter;
import com.efortunetech.testrecyclerview.commonRecyclerview.CommonHolder;
import com.efortunetech.testrecyclerview.commonRecyclerview.TestBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommonAdapter<TestBean> testAdapter;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;

    private int page = 1;
    private int limit = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        /**
         * common recyclerview
         */
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        List<TestBean> beanList = loadData(page, limit);

        testAdapter = new CommonAdapter<TestBean>(this, beanList, R.layout.item2, recyclerView) {
            @Override
            protected void convert(Context context, CommonHolder holder, TestBean testBean) {
                holder.setText(R.id.name, testBean.name).setText(R.id.content, testBean.content)
                        .setImageResource(R.id.image, testBean.imageId).setBackGroundColor(testBean.color);
            }
        };
        recyclerView.setAdapter(testAdapter);
        TextView header = getTextView("here is header", Color.RED, Color.GREEN);
        testAdapter.setHeaderView(header);
        final TextView footer = getTextView("here is footer", Color.BLUE, Color.RED);
        footer.setVisibility(View.GONE);
        testAdapter.setFooterView(footer);

        testAdapter.setOnItemClickListener(new CommonAdapter.setOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity2.this, "position => " + position, Toast.LENGTH_SHORT).show();
            }
        });

        // 直接提供loadmore的数据源，内部已经实现监听
        testAdapter.loadMore(new CommonAdapter.setOnLoadMoreListener() {
            @Override
            public void loadMore() {
                if (page < 5) {
                    page++;
                    List<TestBean> appendList = loadData(page, limit);
                    if (appendList.size() > 0) {
                        testAdapter.addAll(appendList);
                    }
                } else {
                    footer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private List<TestBean> loadData(int page, int limit) {
        List<TestBean> appendList = new ArrayList<>();
        for (int i = (page - 1) * limit; i < page * limit; i++) {
            TestBean bean = new TestBean();
            bean.name = "name " + i;
            bean.content = "content " + i;
            bean.imageId = R.drawable.nha_logo;
            bean.color = Color.BLUE;
            appendList.add(bean);
        }
        return appendList;
    }

    private TextView getTextView(String text, int textColor, int bgColor) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(bgColor);
        textView.setTextColor(textColor);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
}

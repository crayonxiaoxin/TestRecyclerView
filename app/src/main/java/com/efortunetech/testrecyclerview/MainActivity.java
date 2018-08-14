package com.efortunetech.testrecyclerview;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.efortunetech.testrecyclerview.complexLayoutRecyclerview.RecyclerviewAdapter;
import com.efortunetech.testrecyclerview.complexLayoutRecyclerview.TestBean1;
import com.efortunetech.testrecyclerview.complexLayoutRecyclerview.TestBean2;
import com.efortunetech.testrecyclerview.complexLayoutRecyclerview.TestBean3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        /**
         * complex layout recyclerview
         */

        final GridLayoutManager manager = new GridLayoutManager(this, 6);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recyclerView.getAdapter().getItemViewType(position);
                if (type == 3) {
                    return 6;
                } else if (type == 2) {
                    return 3;
                } else if (type==1){
                    return 2;
                }else{
                    return 6;
                }
            }
        });
        recyclerView.setLayoutManager(manager);


        adapter = new RecyclerviewAdapter(this);

        List<TestBean1> list1 = new ArrayList<>();
        List<TestBean2> list2 = new ArrayList<>();
        List<TestBean3> list3 = new ArrayList<>();
        int[] colors = {Color.BLUE, Color.GRAY, Color.GREEN};
        for (int i = 0; i < 30; i++) {
            if (i < 12) {
                TestBean1 bean1 = new TestBean1();
                bean1.name = "name" + i;
                bean1.color = colors[0];
                list1.add(bean1);
            } else if (i >= 12 && i < 20) {
                TestBean2 bean2 = new TestBean2();
                bean2.name = "name" + i;
                bean2.content = "content" + i;
                bean2.color = colors[1];
                list2.add(bean2);
            } else {
                TestBean3 bean3 = new TestBean3();
                bean3.name = "name" + i;
                bean3.content = "content" + i;
                bean3.color = colors[2];
                list3.add(bean3);
            }
        }

        adapter.addData(list1, list2, list3);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 10;
                if (spanSize!=manager.getSpanCount()){
                    if (spanIndex==0){
                        outRect.left=10;
                        outRect.right=10;
                    }else{
                        outRect.right=10;
                    }
                }
            }
        });
        TextView header = new TextView(this);
        header.setText("HeaderView");
        header.setGravity(Gravity.CENTER);
        header.setTextColor(Color.RED);
        header.setPadding(10,10,10,10);
        adapter.setHeader(header);
        TextView footer = new TextView(this);
        footer.setText("FooterView");
        footer.setGravity(Gravity.CENTER);
        footer.setTextColor(Color.BLUE);
        footer.setPadding(10,10,10,10);
        adapter.setFooter(footer);
        adapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(MainActivity.this,"position => "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

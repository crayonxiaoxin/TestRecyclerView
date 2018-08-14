package com.efortunetech.testrecyclerview.complexLayoutRecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efortunetech.testrecyclerview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lau on 2018/8/10.
 * <p>
 * Common RecyclerView adapter
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<TestBean1> list1;
    private List<TestBean2> list2;
    private List<TestBean3> list3;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;

    private static final int TYPE_HEADER = -1;
    private static final int TYPE_FOOTER = -2;

    private View header, footer;
    private OnItemClickListener itemClickListener;

    public RecyclerviewAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<TestBean1> list1, List<TestBean2> list2, List<TestBean3> list3) {
        addListByType(TYPE_ONE, list1);
        addListByType(TYPE_TWO, list2);
        addListByType(TYPE_THREE, list3);
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    private List<Integer> types = new ArrayList<>();
    private HashMap<Integer, Integer> positions = new HashMap<>();

    public void addListByType(int type, List list) {
        positions.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new Holder1(LayoutInflater.from(context).inflate(R.layout.item1, parent, false));
            case TYPE_TWO:
                return new Holder2(LayoutInflater.from(context).inflate(R.layout.item2, parent, false));
            case TYPE_THREE:
                return new Holder3(LayoutInflater.from(context).inflate(R.layout.item3, parent, false));
            case TYPE_HEADER:
                return new ReturnHolder(header);
            case TYPE_FOOTER:
                return new ReturnHolder(footer);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int type = getItemViewType(position);
        int realPosition = 0;
        if (type != TYPE_HEADER && type != TYPE_FOOTER) {
            if (header != null) {
                realPosition = position - positions.get(type) - 1;
            } else {
                realPosition = position - positions.get(type);
            }
        }
        switch (type) {
            case TYPE_ONE:
                Holder1 holder1 = (Holder1) holder;
                holder1.setBindHolder(list1.get(realPosition));
                setOnItemClick(holder1, position);
                break;
            case TYPE_TWO:
                Holder2 holder2 = (Holder2) holder;
                holder2.setBindHolder(list2.get(realPosition));
                setOnItemClick(holder2, position);
                break;
            case TYPE_THREE:
                Holder3 holder3 = (Holder3) holder;
                holder3.setBindHolder(list3.get(realPosition));
                setOnItemClick(holder3, position);
                break;
            case TYPE_HEADER:
                break;
            case TYPE_FOOTER:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (header != null && footer == null) {
            return types.size() + 1;
        } else if (header == null && footer != null) {
            return types.size() + 1;
        } else if (header != null && footer != null) {
            return types.size() + 2;
        } else {
            return types.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && header != null) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && footer != null) {
            return TYPE_FOOTER;
        }
        if (header != null && position != 0) {
            return types.get(position - 1);
        }
        return types.get(position);
    }

    public void setHeader(View header) {
        this.header = header;
        notifyItemInserted(0);
    }

    public void setFooter(View footer) {
        this.footer = footer;
        notifyItemInserted(types.size() - 1);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemClick(AbstractViewHolder holder, final int realPosition) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClick(realPosition);
                }
            });
        }
    }
}

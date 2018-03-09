package com.odbpo.fenggou.categorydemo.ui.category.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.odbpo.fenggou.categorydemo.R;
import com.odbpo.fenggou.categorydemo.bean.CategoryBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: zc
 * @Time: 2018/3/1 10:37
 * @Desc:
 */
public class FAdapter extends RecyclerView.Adapter {
    public ItemClick itemClick;
    private List<CategoryBean.DataBean> mData;
    private Context context;
    private int selectItem = 0;

    public FAdapter(ItemClick itemClick, List<CategoryBean.DataBean> mData) {
        this.itemClick = itemClick;
        this.mData = mData;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_f_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder itemHolder = (ViewHolder) holder;
        itemHolder.fText.setText(mData.get(position).getName());
        if (selectItem == position) {
            itemHolder.fView.setVisibility(View.VISIBLE);
            itemHolder.fText.setTextColor(Color.WHITE);
            itemHolder.fText.setBackgroundResource(R.color.color_text_select_bg);
        } else {
            itemHolder.fView.setVisibility(View.GONE);
            itemHolder.fText.setTextColor(Color.GRAY);
            itemHolder.fText.setBackgroundResource(R.color.color_text_unselect_bg);
        }

        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.initId(mData.get(position).getId());
                setSelectItem(position);
                notifyDataSetChanged();//更新适配器
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.f_view)
        View fView;
        @Bind(R.id.f_text)
        TextView fText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClick {
        void initId(int id);
    }

}

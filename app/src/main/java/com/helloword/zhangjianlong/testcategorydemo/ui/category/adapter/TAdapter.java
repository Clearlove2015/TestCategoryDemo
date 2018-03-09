package com.helloword.zhangjianlong.testcategorydemo.ui.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.helloword.zhangjianlong.testcategorydemo.R;
import com.helloword.zhangjianlong.testcategorydemo.bean.CategoryBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: zc
 * @Time: 2018/3/1 10:38
 * @Desc:
 */
public class TAdapter extends RecyclerView.Adapter {
    private List<CategoryBean.DataBean> mData;
    private Context context;

    public TAdapter(List<CategoryBean.DataBean> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_t_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        itemHolder.tTv.setText(mData.get(position).getName());
        Glide.with(context).load(mData.get(position).getImgSrc()).into(itemHolder.tIv);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.t_iv)
        ImageView tIv;
        @Bind(R.id.t_tv)
        TextView tTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

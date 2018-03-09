package com.helloword.zhangjianlong.testcategorydemo.ui.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helloword.zhangjianlong.testcategorydemo.R;
import com.helloword.zhangjianlong.testcategorydemo.bean.CategoryBean;
import com.helloword.zhangjianlong.testcategorydemo.utils.NoScrollGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: zc
 * @Time: 2018/3/1 10:38
 * @Desc:
 */
public class SAdapter extends RecyclerView.Adapter {
    private List<CategoryBean.DataBean> mData;
    private List<CategoryBean.DataBean> mList;
    private Context context;

    public SAdapter(List<CategoryBean.DataBean> mData, List<CategoryBean.DataBean> mList) {
        this.mData = mData;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_s_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        itemHolder.sText.setText(mData.get(position).getName());

        initRv(itemHolder,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.s_text)
        TextView sText;
        @Bind(R.id.t_rv)
        RecyclerView tRv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void initRv(ViewHolder itemHolder,int position){
        List<CategoryBean.DataBean> t_list = new ArrayList<>();
        ListIterator<CategoryBean.DataBean> li = mList.listIterator();
        while (li.hasNext()){
            CategoryBean.DataBean next = li.next();
            if(next.getGrade() == 3 && mData.get(position).getId() == next.getParentId()){
                t_list.add(next);
            }
        }

        NoScrollGridLayoutManager noScrollGridLayoutManager = new NoScrollGridLayoutManager(context, 3, LinearLayout.VERTICAL, false);
        noScrollGridLayoutManager.setScrollEnabled(false);//禁止滑动
        itemHolder.tRv.setLayoutManager(noScrollGridLayoutManager);
        itemHolder.tRv.setAdapter(new TAdapter(t_list));
    }

}

package com.odbpo.fenggou.categorydemo.ui.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.odbpo.fenggou.categorydemo.R;
import com.odbpo.fenggou.categorydemo.bean.CategoryBean;
import com.odbpo.fenggou.categorydemo.ui.category.adapter.FAdapter;
import com.odbpo.fenggou.categorydemo.ui.category.adapter.SAdapter;
import com.odbpo.fenggou.categorydemo.utils.ReadAssetsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements FAdapter.ItemClick{

    @Bind(R.id.f_rv)
    RecyclerView fRv;
    @Bind(R.id.s_rv)
    RecyclerView sRv;

    private List<CategoryBean.DataBean> mList = new ArrayList<>();
    private List<CategoryBean.DataBean> f_list = new ArrayList<>();
    private List<CategoryBean.DataBean> s_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initData();
        initList();
        fRv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        fRv.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        fRv.setAdapter(new FAdapter(this,f_list));
        sRv.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        initId(f_list.get(0).getId());
    }

    public void initData(){
        String json = ReadAssetsUtil.getJson(this, "category.json");
        Gson gson = new Gson();
        CategoryBean categoryBean = gson.fromJson(json, CategoryBean.class);
        mList.clear();
        mList.addAll(categoryBean.getData());
    }

    public void initList(){
        ListIterator<CategoryBean.DataBean> li = mList.listIterator();
        while (li.hasNext()){
            CategoryBean.DataBean next = li.next();
            if(next.getGrade() == 1){
                f_list.add(next);
            }
        }
    }


    @Override
    public void initId(int id) {
        s_list.clear();
        ListIterator<CategoryBean.DataBean> li = mList.listIterator();
        while (li.hasNext()){
            CategoryBean.DataBean next = li.next();
            if(next.getGrade() == 2 && id == next.getParentId()){
                s_list.add(next);
            }
        }
        sRv.setAdapter(new SAdapter(s_list, mList));
        sRv.getAdapter().notifyDataSetChanged();
    }

}

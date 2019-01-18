package com.bwie.likuo.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.activity.ShangPinActivity;
import com.bwie.likuo.adapter.MyLeftAdapter;
import com.bwie.likuo.adapter.MyRightAdapter;
import com.bwie.likuo.model.bean.CategoryLeftRecyclerBean;
import com.bwie.likuo.model.bean.CategoryRightRecyclerBean;
import com.bwie.likuo.net.Constant;
import com.bwie.likuo.net.OkhtttpUtils;
import com.bwie.likuo.persenter.onClicklistener;
import com.bwie.likuo.persenter.onerclicklistener;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    

    @BindView(R.id.leftrecyclerview)
    RecyclerView leftrecyclerview;
    @BindView(R.id.linner)
    LinearLayout linner;
    Unbinder unbinder;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    MyLeftAdapter myLeftAdapter;
    String ccid= "1";
    MyRightAdapter myRightAdapter;
    String ppscid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        leftrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));

        ininData();
        leftrecyclerview.setAdapter(myLeftAdapter);

        ininVData();
        return view;
    }

    private void ininData() {
        OkhtttpUtils.getInstance().doGet(Constant.YIJIZUO_PATH, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                CategoryLeftRecyclerBean categoryLeftRecyclerBean = new Gson().fromJson(json, CategoryLeftRecyclerBean.class);
                List<CategoryLeftRecyclerBean.DataBean> data = categoryLeftRecyclerBean.getData();
                myLeftAdapter = new MyLeftAdapter(getActivity(),data);
                myLeftAdapter.setOnClicklistener(new onClicklistener() {
                    @Override
                    public void cid(String cid) {
                        ccid = cid;
                        Log.e("lk","cid"+cid);
                        ininVData();
                    }
                });
            }


        });
    }
    private void ininVData() {
        OkhtttpUtils.getInstance().doGet(Constant.ERJIRIGHT_PATH + ccid, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                CategoryRightRecyclerBean categoryRightRecyclerBean = new Gson().fromJson(json, CategoryRightRecyclerBean.class);
                List<CategoryRightRecyclerBean.DataBean> data = categoryRightRecyclerBean.getData();
                linner.removeAllViews();
                for (int i = 0; i < data.size(); i++) {
                    CategoryRightRecyclerBean.DataBean dataBean = data.get(i);
                    List<CategoryRightRecyclerBean.DataBean.ListBean> list = dataBean.getList();
                    myRightAdapter = new MyRightAdapter(getActivity(),list);
                    TextView textView = new TextView(getActivity(),null,0);
                    textView.setTextColor(Color.RED);
                    textView.setTextSize(20f);
                    textView.setText(dataBean.getName());
                    RecyclerView recyclerView = new RecyclerView(getActivity(),null,0);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                    recyclerView.setAdapter(myRightAdapter);
                    myRightAdapter.setOnerclicklistener(new onerclicklistener() {
                        @Override
                        public void pscid(String pscid) {
                            Log.e("lk1","psci"+pscid);
                            ppscid = pscid;
                            Intent intent = new Intent(getActivity(),ShangPinActivity.class);
                            intent.putExtra("ppscid",ppscid);
                            startActivity(intent);
                        }
                    });
                    myRightAdapter.notifyDataSetChanged();
                    linner.addView(textView);
                    linner.addView(recyclerView);

                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

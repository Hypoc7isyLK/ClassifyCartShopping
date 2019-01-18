package com.bwie.likuo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.MyCartAdapter;
import com.bwie.likuo.model.bean.ShopCartBean;
import com.bwie.likuo.net.Constant;
import com.bwie.likuo.net.OkhtttpUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    @BindView(R.id.expandlistview)
    ExpandableListView expandlistview;
    Unbinder unbinder;
    MyCartAdapter mMyCartAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        iniData();



        return view;
    }

    private void iniData() {
        OkhtttpUtils.getInstance().doGet(Constant.SHOPCART_PATH, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                ShopCartBean shopCartBean = new Gson().fromJson(json, ShopCartBean.class);
                List<ShopCartBean.DataBean> data = shopCartBean.getData();
                mMyCartAdapter = new MyCartAdapter(getActivity(),data);
                expandlistview.setAdapter(mMyCartAdapter);
                int count = expandlistview.getCount();
                for (int i=0; i<count; i++)
                {
                    expandlistview.expandGroup(i);
                };
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

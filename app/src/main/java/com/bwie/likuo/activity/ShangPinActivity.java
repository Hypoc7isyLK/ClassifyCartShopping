package com.bwie.likuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.bwie.likuo.R;
import com.bwie.likuo.adapter.MyLieBiaoAdapter;
import com.bwie.likuo.model.bean.ShopLieBiaoBean;
import com.bwie.likuo.net.Constant;
import com.bwie.likuo.net.OkhtttpUtils;
import com.bwie.likuo.persenter.onseccuss;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShangPinActivity extends AppCompatActivity {
    @BindView(R.id.liebiaorecycler)
    RecyclerView liebiaorecycler;
    private String mPpscid;
    MyLieBiaoAdapter myLieBiaoAdapter;
    String ppid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_pin);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPpscid = intent.getStringExtra("ppscid");
        liebiaorecycler.setLayoutManager(new LinearLayoutManager(this,OrientationHelper.VERTICAL,false));

        initData();

    }

    private void initData() {
        OkhtttpUtils.getInstance().doGet(Constant.SPLB_PATH + mPpscid, new OkhtttpUtils.OkCallback() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                ShopLieBiaoBean shopLieBiaoBean = new Gson().fromJson(json, ShopLieBiaoBean.class);
                List<ShopLieBiaoBean.DataBean> data = shopLieBiaoBean.getData();
                myLieBiaoAdapter = new MyLieBiaoAdapter(ShangPinActivity.this,data);
                liebiaorecycler.setAdapter(myLieBiaoAdapter);
                myLieBiaoAdapter.setOnseccuss(new onseccuss() {
                    @Override
                    public void pid(String pid) {
                        ppid = pid;
                        Intent intent = new Intent(ShangPinActivity.this,XiangQingActivity.class);
                        intent.putExtra("ppid",ppid);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}

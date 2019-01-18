package com.bwie.likuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.likuo.R;
import com.bwie.likuo.model.bean.AddBean;
import com.bwie.likuo.net.Constant;
import com.bwie.likuo.net.OkhtttpUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangQingActivity extends AppCompatActivity {

    @BindView(R.id.xingqing)
    TextView xingqing;
    @BindView(R.id.button)
    Button button;
    private Unbinder mBind;
    private String ppid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        mBind = ButterKnife.bind(this);
        Intent intent = getIntent();
        ppid = intent.getStringExtra("ppid");
        xingqing.setText(ppid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XiangQingActivity.this, "加购成功", Toast.LENGTH_SHORT).show();
                OkhtttpUtils.getInstance().doGet(Constant.JIARU_PATH + ppid, new OkhtttpUtils.OkCallback() {
                    @Override
                    public void onFailure(Exception e) {

                    }

                    @Override
                    public void onResponse(String json) {
                        AddBean addBean = new Gson().fromJson(json, AddBean.class);
                        if (addBean.getCode().equals(0)){
                            Toast.makeText(XiangQingActivity.this, addBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(XiangQingActivity.this, addBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}

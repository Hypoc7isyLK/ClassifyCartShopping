package com.bwie.likuo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwie.likuo.fragment.FindFragment;
import com.bwie.likuo.fragment.CategoryFragment;
import com.bwie.likuo.fragment.MineFragment;
import com.bwie.likuo.fragment.CartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new MineFragment();
                    case 1:
                        return new CategoryFragment();
                    case 2:
                        return new FindFragment();
                    case 3:
                        return new CartFragment();
                    default:
                        return new MineFragment();

                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());
        tablayout.addTab(tablayout.newTab());
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).setText("首页");
        tablayout.getTabAt(1).setText("分类");
        tablayout.getTabAt(2).setText("发现");
        tablayout.getTabAt(3).setText("购物车");
        tablayout.getTabAt(4).setText("我的");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}

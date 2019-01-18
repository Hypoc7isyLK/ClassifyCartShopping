package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.model.bean.ShopLieBiaoBean;
import com.bwie.likuo.persenter.onseccuss;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/21
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyLieBiaoAdapter extends RecyclerView.Adapter<MyLieBiaoAdapter.ViewHolder> {
    Context context;
    List<ShopLieBiaoBean.DataBean> mDataBeans;


    public MyLieBiaoAdapter(Context context, List<ShopLieBiaoBean.DataBean> dataBeans) {
        this.context = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.activity_shang_pin_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final String images = mDataBeans.get(i).getImages();
        final String[] image = images.split("!");
        viewHolder.shangpintupian.setImageURI(Uri.parse(image[0]));
        viewHolder.shangpinname.setText(mDataBeans.get(i).getTitle());
        viewHolder.shangpinjiage.setText("￥"+mDataBeans.get(i).getPrice());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnseccuss.pid(mDataBeans.get(i).getPid()+"");


            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView shangpintupian;
        private TextView shangpinname;
        private TextView shangpinjiage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shangpintupian = itemView.findViewById(R.id.shangpintupian);
            shangpinname = itemView.findViewById(R.id.shangpinname);
            shangpinjiage = itemView.findViewById(R.id.shangpinjiage);
        }
    }

    /*public interface onseccuss{
        void pid(String pid);
    }*/
    private onseccuss mOnseccuss;

    public void setOnseccuss(onseccuss onseccuss) {
        mOnseccuss = onseccuss;
    }
}

package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.model.bean.CategoryRightRecyclerBean;
import com.bwie.likuo.persenter.onerclicklistener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/21
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyRightAdapter extends RecyclerView.Adapter<MyRightAdapter.ViewHolder> {

    Context context;
    List<CategoryRightRecyclerBean.DataBean.ListBean> mListBeans;


    public MyRightAdapter(Context context, List<CategoryRightRecyclerBean.DataBean.ListBean> listBeans) {
        this.context = context;
        mListBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_category_right_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.youfresco.setImageURI(Uri.parse(mListBeans.get(i).getIcon()));
        viewHolder.youtextview.setText(mListBeans.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnerclicklistener.pscid(mListBeans.get(i).getPscid()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView youfresco;
        private TextView youtextview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            youfresco = itemView.findViewById(R.id.youfresco);
            youtextview = itemView.findViewById(R.id.youtextview);
        }
    }
    /*public interface onerclicklistener{
        void pscid(String pscid);
    }*/

    private onerclicklistener mOnerclicklistener;

    public void setOnerclicklistener(onerclicklistener onerclicklistener) {
        mOnerclicklistener = onerclicklistener;
    }

}

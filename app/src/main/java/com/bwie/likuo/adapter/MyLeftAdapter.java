package com.bwie.likuo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.model.bean.CategoryLeftRecyclerBean;
import com.bwie.likuo.persenter.onClicklistener;

import java.util.List;

/**
 * date:2018/12/21
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.ViewHolder> {

    Context context;
    List<CategoryLeftRecyclerBean.DataBean> mDataBeans;


    public MyLeftAdapter(Context context, List<CategoryLeftRecyclerBean.DataBean> dataBeans) {
        this.context = context;
        mDataBeans = dataBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_category_left_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.lefttextview.setText(mDataBeans.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClicklistener.cid(mDataBeans.get(i).getCid()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lefttextview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lefttextview = itemView.findViewById(R.id.lefttextview);
        }
    }

    /*public interface onClicklistener{
        void cid(String cid);
    }*/
    private onClicklistener mOnClicklistener;

    public void setOnClicklistener(onClicklistener onClicklistener) {
        mOnClicklistener = onClicklistener;
    }
}

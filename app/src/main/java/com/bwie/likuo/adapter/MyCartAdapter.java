package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bwie.likuo.R;
import com.bwie.likuo.model.bean.ShopCartBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/21
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyCartAdapter extends BaseExpandableListAdapter {
    Context context;
    List<ShopCartBean.DataBean> mListBeans;


    public MyCartAdapter(Context context, List<ShopCartBean.DataBean> listBeans) {
        this.context = context;
        mListBeans = listBeans;
    }

    @Override
    public int getGroupCount() {
        return mListBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListBeans.get(groupPosition).getList().size();
    }


    public static class GroupViewHolder {
        public TextView funame;

        public GroupViewHolder(View rootView) {
            this.funame = rootView.findViewById(R.id.funame);
        }

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ShopCartBean.DataBean dataBean = mListBeans.get(groupPosition);
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fragment_cart_fu_item, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.funame.setText(mListBeans.get(groupPosition).getSellerName());

        return convertView;
    }


    public static class ChildViewHolder {
        public SimpleDraweeView carttupian;
        public TextView cartname;
        public TextView cartjiage;

        public ChildViewHolder(View rootView) {
            this.carttupian = (SimpleDraweeView) rootView.findViewById(R.id.carttupian);
            this.cartname = (TextView) rootView.findViewById(R.id.cartname);
            this.cartjiage = (TextView) rootView.findViewById(R.id.cartjiage);
        }

    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fragment_cart_zi_item, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ShopCartBean.DataBean.ListBean listBean = mListBeans.get(groupPosition).getList().get(childPosition);
        String images = listBean.getImages();
        String[] image = images.split("!");
        childViewHolder.carttupian.setImageURI(Uri.parse(image[0]));
        childViewHolder.cartjiage.setText("￥"+listBean.getPrice());
        childViewHolder.cartname.setText(listBean.getTitle());
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



}

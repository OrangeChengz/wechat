package com.example.myapplication.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.bean.pyqInfo;

import java.util.ArrayList;
import java.util.List;

//朋友圈列表的适配器
public class PostListAdapter extends BaseAdapter {
    private Context mContext;
    private List<pyqInfo> mPyqinfos = new ArrayList<>();

    public PostListAdapter(Context context){
        mContext = context;
    }

    //刷新方法
    public void refresh(List<pyqInfo> pyqinfos){
        if(pyqinfos != null && pyqinfos.size()>=0){
            mPyqinfos.clear();

            mPyqinfos.addAll(pyqinfos);

            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mPyqinfos==null ? 0 : mPyqinfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mPyqinfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建或获取viewholder
        ViewHodler hodler = null;

        if(convertView ==null){
            hodler = new ViewHodler();
            convertView = View.inflate(mContext, R.layout.item_postlist,null);
            hodler.username = convertView.findViewById(R.id.tv_post_user);
            hodler.message = convertView.findViewById(R.id.tv_post_message);

            convertView.setTag(hodler);
        } else{
            hodler = (ViewHodler) convertView.getTag();
        }
        //获取当前item数据
        pyqInfo pyqinfo = mPyqinfos.get(position);
        //展示数据
        hodler.username.setText(pyqinfo.getName());
        hodler.message.setText(pyqinfo.getMessage());

        //返回数据
        return convertView;
    }

    private class ViewHodler{
        TextView username;
        TextView message;
    }
}

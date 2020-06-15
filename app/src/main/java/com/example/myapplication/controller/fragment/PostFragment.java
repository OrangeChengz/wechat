package com.example.myapplication.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.adapter.PostListAdapter;
import com.example.myapplication.model.Model;
import com.example.myapplication.model.bean.pyqInfo;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

//朋友圈
public class PostFragment extends Fragment {
    private Button bt_post_publish;
    private EditText et_post;
    private ListView lv_postlist;
    private TextView tv_name1;
    private TextView tv_message1;

    private List<pyqInfo> pyqInfoList = new ArrayList<>();
    private PostListAdapter postlistadapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_post,null);

        initView(view);

        initData();
        return view;
    }

    private void initData(){
        postlistadapter = new PostListAdapter(getActivity());

        lv_postlist.setAdapter(postlistadapter);

        //获取所有朋友圈的信息
         getPyqsFromServer();
    }

    private void getPyqsFromServer() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //获取数据
                //List<pyqInfo> pyqInfoList = Model.getInstance().getDbManager().getPyqTableDao().getpyqs();
                //更新页面
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postlistadapter.refresh(pyqInfoList);
                    }
                });
            }
        });
    }

    private void initView(View view){
        bt_post_publish = (Button)view.findViewById(R.id.bt_post_publish);
        et_post = (EditText)view.findViewById(R.id.et_post);
        lv_postlist = (ListView)view.findViewById(R.id.lv_postlist);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData1();
    }

    private void initData1(){
        //点击 “发布” 按钮之后
        bt_post_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //获取朋友圈内容
                        String message = et_post.getText().toString();
                        if(message.trim().isEmpty())
                            return;
                        String name = EMClient.getInstance().getCurrentUser();
                        pyqInfo pyq = new pyqInfo(name,message);
                        //Model.getInstance().getDbManager().getPyqTableDao().addpyq(pyq);
                        pyqInfoList.add(pyq);
                        //更新页面
                        //postlistadapter.refresh(Model.getInstance().getDbManager().getPyqTableDao().getpyqs());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                postlistadapter.refresh(pyqInfoList);
                                et_post.setText("");
                                Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
            }
        });
    }
}

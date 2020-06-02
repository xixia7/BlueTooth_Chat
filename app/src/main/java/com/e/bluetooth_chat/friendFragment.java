/*
 * @FileName: 
 * @Description: 
 * @version: 
 * @Date: 2020-06-02 18:13:05
 * @Author: 
 * @Email: 844979252@qq.com
 * @LastEditors: wwj
 * @LastEditTime: 2020-06-02 19:10:55
 */
package com.e.bluetooth_chat;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class friendFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<String> list;
    private friend_adapter friend_adapter;


    public friendFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab02, container, false);
        initData();
        initRecyclerView();
        return view;
    }

    //    initialize the data
    private void initData() {
        Log.d("initdata","list");
    }

    //    initialize the recyclerview
    private void initRecyclerView() {
        Log.d("initrv","s");
//        get RecyclerView
        recyclerView = view.findViewById(R.id.recycleview);
////        when we know that changes in content don't change the layout size of the RecyclerView, use this setting to avoid the same calculation to improve performance
//        recyclerView.setHasFixedSize(true);

//        create friend_adapter for recycleview
        friend_adapter = new friend_adapter(getActivity(),list);

//        set friend_adapter for recycleview
        recyclerView.setAdapter(friend_adapter);

//        set the layoutManager to control the effect of display
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

//        set the cut-off rule of item
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }


}

/*
 * @FileName: 
 * @Description: 
 * @version: 
 * @Date: 2020-06-02 18:13:05
 * @Author: 
 * @Email: 844979252@qq.com
 * @LastEditors: wwj
 * @LastEditTime: 2020-06-02 19:13:23
 */
package com.e.bluetooth_chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Map;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class contact_adapter extends RecyclerView.Adapter<contact_adapter.contactViewHolder> {

    private static final String TAG = contact_adapter.class.getSimpleName();

    private Context mcontext;
    private List<Map<String,Object>> mlistdata;

    public contact_adapter(Context context) {
        mcontext = context;
    }

    public void initDataList(List<Map<String,Object>> listData) {
        Log.d(TAG, "initDataList: " + listData.size());
        mlistdata = listData;
        notifyDataSetChanged();
    }

    @Override
    public contactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.contact_text,parent,false);
        return new contactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {
        Map<String,Object> rec= mlistdata.get(position);  
        holder.Name.setText(rec.get("name").toString());
        holder.Tel.setText(rec.get("tel").toString());
    }

    @Override
    public int getItemCount() {
        return mlistdata == null ? 0 : mlistdata.size();
    }

    public class contactViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Tel;

        public contactViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Tel = itemView.findViewById(R.id.tel);
        }
    }

}

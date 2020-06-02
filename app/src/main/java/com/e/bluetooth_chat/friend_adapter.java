/*
 * @FileName: 
 * @Description: 
 * @version: 
 * @Date: 2020-06-02 18:13:05
 * @Author: 
 * @Email: 844979252@qq.com
 * @LastEditors: wwj
 * @LastEditTime: 2020-06-02 19:11:33
 */
package com.e.bluetooth_chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class friend_adapter extends RecyclerView.Adapter<friend_adapter.MyViewHolder> {

    private List<String>list;
    private Context context;
    private View inflater;

//    provide a suitable constructor
    public friend_adapter(Context context, List<String>list) {
       this.list = list;
       this.context = context;
    }

    @Override
//    create new views (invoked by the layout manager) and it won't change the contents of the view
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        create a new view
//        compared to findViewById,which is used to find the specific controls(such as button,textview,etc.),
//        LayoutInflater will find the xml layout files in res/layout/ and instantiate it.
        inflater = LayoutInflater.from(context).inflate(R.layout.rvlayout_text,parent,false);
        //inflater = View.inflate(context,R.layout.rvlayout_text,null);
        MyViewHolder myviewholder = new MyViewHolder(inflater);
        return myviewholder;
    }

    @Override
//   replace the contents of a view(invoked by the layout manager)
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.rtextView.setText(list.get(position));
    }

    @Override
//    return the size of the dataset(invoked by the layout manager)
    public int getItemCount() {
        return list.size();
    }

//    provide a reference to the views for each data item
//    complex data items need more than one view per item, then provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        static TextView rtextView;
        public MyViewHolder(View rvlayout_textView) {
            super(rvlayout_textView);
            rtextView = rvlayout_textView.findViewById(R.id.rtextView);
        }
    }
}

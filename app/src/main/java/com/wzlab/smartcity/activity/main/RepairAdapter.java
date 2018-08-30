package com.wzlab.smartcity.activity.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzlab.smartcity.R;
import com.wzlab.smartcity.po.RepairLog;


import java.util.ArrayList;

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {
    private Context mContext;
    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    private OnItemClickListener mlistener;
    ArrayList<RepairLog> arraylist;
    public RepairAdapter(Context context, ArrayList<RepairLog> arraylist ,  OnItemClickListener listener){
        this.arraylist=arraylist;
        this.mContext=context;
        this.mlistener=listener;
    }
    @Override
    public RepairAdapter.RepairViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepairViewHolder(LayoutInflater.from(mContext).inflate(R.layout.repair_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(RepairViewHolder holder, final int position) {
        RepairLog s = arraylist.get(position);
        holder.textView_title.setText("报修单");

        holder.mIvF.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_finish));
        holder.mIvLogo.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_biaoqian));
        holder.textView_repairid.setText(arraylist.get(position).getRepair_id());
        holder.textView_id.setText(arraylist.get(position).getDevice_id());
        holder.textView_content.setText(arraylist.get(position).getRepair_content());
        holder.getTextView_time.setText(arraylist.get(position).getRepair_time());
        holder.textView_phone.setText(arraylist.get(position).getUser_phone());
        holder.textView_address.setText(arraylist.get(position).getUser_address());

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mlistener.onItemClick(view,position);
    }
});
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }
    class RepairViewHolder extends RecyclerView.ViewHolder{
private TextView textView_repairid;
        private TextView textView_id,getTextView_time,textView_content,textView_phone,textView_address,textView_title;
        private ImageView mIvLogo,mIvF;
        private View viewDivider;
        public RepairViewHolder(View itemView) {
            super(itemView);
            mIvF=itemView.findViewById(R.id.iv_finish);
            mIvLogo = itemView.findViewById(R.id.iv_biaoqian);
            textView_title = itemView.findViewById(R.id.tv_title);
            textView_repairid = itemView.findViewById(R.id.tv_repair_id);
            textView_id = itemView.findViewById(R.id.tv_device_id);
            getTextView_time = itemView.findViewById(R.id.tv_repair_time);
            textView_content = itemView.findViewById(R.id.tv_repair_content);
            textView_phone = itemView.findViewById(R.id.tv_user_phone);
            textView_address = itemView.findViewById(R.id.tv_user_address);
            viewDivider = itemView.findViewById(R.id.view_divider);

        }
    }
}

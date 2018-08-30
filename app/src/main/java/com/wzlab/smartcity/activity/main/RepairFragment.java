package com.wzlab.smartcity.activity.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wzlab.smartcity.R;
import com.wzlab.smartcity.po.RepairLog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepairFragment extends Fragment {
private RecyclerView mRecyclerView;

    public RepairFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repair, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.rv_repair);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<RepairLog> arrayList = new ArrayList<>();
        RepairLog repairLog = new RepairLog();

        repairLog.setRepair_id("维修号：45");
        repairLog.setDevice_id("设备ID：23");
        repairLog.setUser_phone("用户电话：12345678910");
        repairLog.setUser_address("用户地址：宁波");
        repairLog.setRepair_content("维修内容：摄像头损坏");
        repairLog.setRepair_time("维修时间：12:00");
        arrayList.add(repairLog);
        RepairLog repairLog1 = new RepairLog();
        repairLog1.setRepair_id("维修号：45");
        repairLog1.setDevice_id("设备ID：23");
        repairLog1.setUser_phone("用户电话：12345678910");
        repairLog1.setUser_address("用户地址：宁波");
        repairLog1.setRepair_content("维修内容：摄像头损坏");
        repairLog1.setRepair_time("维修时间：12:00");
        arrayList.add(repairLog1);


        RepairAdapter repairAdapter = new RepairAdapter(getContext(), arrayList, new RepairAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(repairAdapter);
    }


}

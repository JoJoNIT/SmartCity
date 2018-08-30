package com.wzlab.smartcity.activity.main;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzlab.smartcity.R;
import com.wzlab.smartcity.adapter.ViewPagerAdapter;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import javax.xml.transform.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabViewFragment extends Fragment {
    TabLayout mTabLayout;
    ViewPager mViewPager;







    public TabViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabview, container, false);
        return view;
    }
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mTabLayout = view.findViewById(R.id.tl_tab);
        mViewPager = view.findViewById(R.id.vp_pager);
        final ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new DeviceOverviewFragment());
        arrayList.add(new RepairFragment());
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),arrayList);
        mViewPager.setAdapter(pagerAdapter);



      ;

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("报警");
        mTabLayout.getTabAt(1).setText("维修");



    }





}

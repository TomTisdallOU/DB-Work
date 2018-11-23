package com.example.dbproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StandingsFragment extends Fragment {
    TabLayout tabLayout = null;
    ViewPager viewPager = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.standings_fragment,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        ViewPageAdapter adapter = new ViewPageAdapter(getFragmentManager());
        adapter.AddFragment(new WeeklyStandingsFragment(), "Weekly");
        adapter.AddFragment(new OverallStandingsFragment(), "Overall");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }
}

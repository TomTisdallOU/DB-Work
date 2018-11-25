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

public class UserFragment extends Fragment {
    TabLayout tabLayout = null;
    ViewPager viewPager = null;
    int userID;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle arguments = getArguments();
        userID = arguments.getInt("UserID");

        return inflater.inflate(R.layout.user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO - save the state -- I think the new UserPicksFragment messes up the views.
        tabLayout = view.findViewById(R.id.userTabLayout);
        viewPager = view.findViewById(R.id.userViewPager);
        ViewPageAdapter adapter = new ViewPageAdapter(getFragmentManager());

        Bundle arguments = new Bundle();
        arguments.putInt("UserID",  userID);


        Fragment fragmentUserPicks = new UserPicksFragment();
        fragmentUserPicks.setArguments(arguments);

        Fragment fragmentUserSettings = new UserSettingsFragment();
        fragmentUserSettings.setArguments(arguments);

        adapter.AddFragment(fragmentUserPicks, "Picks");
        adapter.AddFragment(fragmentUserSettings, "User");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
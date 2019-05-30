package com.lentimosystems.licio.safariguides.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lentimosystems.licio.safariguides.Fragments.BusesFragment;
import com.lentimosystems.licio.safariguides.Fragments.CruisersFragment;
import com.lentimosystems.licio.safariguides.Fragments.TrucksFragment;
import com.lentimosystems.licio.safariguides.Fragments.VansFragment;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private Context context;

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return VansFragment.getInstance();
        else if (position == 1)
            return CruisersFragment.getInstance();
        else if (position == 2)
            return TrucksFragment.getInstance();
        else if (position == 3)
            return BusesFragment.getInstance();
        else
            return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Vans";

            case 1:
                return "Cruisers";

            case 2:
                return "Trucks";

            case 3:
                return "Buses";

        }
        return "";
    }
}

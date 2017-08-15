package jorgemonzon.pruebatecnica.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jorgemonzon.pruebatecnica.Fragments.BuscarFragment;
import jorgemonzon.pruebatecnica.Fragments.CrearNuevoFragment;
import jorgemonzon.pruebatecnica.Fragments.ListadoFragment;
import jorgemonzon.pruebatecnica.R;

/**
 * Created by jorge on 11/08/17.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;

    private String tabTitles[];

    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.string_array_titulos_tabs);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ListadoFragment.newInstance();
            case 1:
                return BuscarFragment.newInstance();
            case 2:
                return CrearNuevoFragment.newInstance();
            default:
                return ListadoFragment.newInstance();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}

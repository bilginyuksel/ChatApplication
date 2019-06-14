package c.bilgin.chatapplication.NewsHomePage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerNewsAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public ViewPagerNewsAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{HomeFragment.getInstance(),
                AIFragment.getInstance(),
                CSFragment.getInstance(),
        VRFragment.getInstance(),
        SDFragment.getInstance()};
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return 5;
    }
}

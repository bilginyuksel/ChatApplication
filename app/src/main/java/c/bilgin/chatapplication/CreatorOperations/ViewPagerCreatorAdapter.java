package c.bilgin.chatapplication.CreatorOperations;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerCreatorAdapter extends FragmentPagerAdapter {

    private Fragment fragments[];
    public ViewPagerCreatorAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{FragmentAddNews.getInstance(),
        FragmentAddUser.getInstance()};
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}

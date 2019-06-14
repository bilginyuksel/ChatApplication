package c.bilgin.chatapplication.QuestionPage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment child_fragments[];


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        child_fragments = new Fragment[]{
                QuestionChildFragment.getInstance(),
                QuestionChildFragmentAnswers.getInstance(),
                QuestionChildFragmentFollow.getInstance()
        };
    }

    @Override
    public Fragment getItem(int i) {
        return child_fragments[i];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = getItem(position).getClass().getSimpleName();
        if(title.equals("QuestionChildFragment")) title = "Question";
        else if(title.equals("QuestionChildFragmentAnswers")) title="Answer";
        else if(title.equals("QuestionChildFragmentFollow")) title="Follow";

        return title;
    }

    @Override
    public int getCount() {
        return child_fragments.length;
    }
}

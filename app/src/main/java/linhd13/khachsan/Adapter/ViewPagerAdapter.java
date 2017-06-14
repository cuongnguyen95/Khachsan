package linhd13.khachsan.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import linhd13.khachsan.Fragment.PageFragment;

/**
 * Created by cuong pc on 6/11/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Integer> images;

    public ViewPagerAdapter(FragmentManager fm, List<Integer> imagesList) {
        super(fm);
        this.images = imagesList;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.getInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}

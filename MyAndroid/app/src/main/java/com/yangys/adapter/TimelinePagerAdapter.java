package com.yangys.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yangys.fragment.timeline.ArticleFragment;
import com.yangys.fragment.timeline.FavoriteFragment;
import com.yangys.fragment.timeline.ReadLaterFragment;

import example.com.myandroid.R;

/**
 * Created by yangys on 2018/10/12.
 */

public class TimelinePagerAdapter extends FragmentPagerAdapter{

    ArticleFragment articleFragment;
    FavoriteFragment favoriteFragment;
    ReadLaterFragment readLaterFragment;

    String [] titles;

    public TimelinePagerAdapter(FragmentManager fm , Context context ,
                                ArticleFragment articleFragment,
                                FavoriteFragment favoriteFragment,
                                ReadLaterFragment readLaterFragment){
        super(fm);
        this.articleFragment = articleFragment;
        this.favoriteFragment = favoriteFragment;
        this.readLaterFragment = readLaterFragment;

        titles = new String[]{context.getString(R.string.timeline_articles),
                context.getString(R.string.timeline_favorites),
                context.getString(R.string.timeline_read_later)};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return articleFragment;
            case 1:
                return favoriteFragment;
            case 2:
                return readLaterFragment;
        }
        return articleFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

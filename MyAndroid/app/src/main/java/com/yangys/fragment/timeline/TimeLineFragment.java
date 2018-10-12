package com.yangys.fragment.timeline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangys.adapter.TimelinePagerAdapter;

import example.com.myandroid.R;

/**
 * Created by yangys on 2018/10/11.
 */

public class TimeLineFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArticleFragment articleFragment;
    private FavoriteFragment favoriteFragment;
    private ReadLaterFragment readLaterFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleFragment = new ArticleFragment();
        favoriteFragment = new FavoriteFragment();
        readLaterFragment = new ReadLaterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timeline_fragment,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        tabLayout = view.findViewById(R.id.timeline_tab_layout);
        viewPager = view.findViewById(R.id.tview_pager);
        viewPager.setAdapter(new TimelinePagerAdapter(getFragmentManager(),getActivity(),
                articleFragment,favoriteFragment,readLaterFragment));
        tabLayout.setupWithViewPager(viewPager);
    }
}

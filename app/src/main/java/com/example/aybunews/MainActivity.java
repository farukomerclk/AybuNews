package com.example.aybunews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    private fragment_yemek_listesi yemek_listesi = new fragment_yemek_listesi();
    private fragment_duyurular duyurular = new fragment_duyurular();
    private fragment_haberler haberler = new fragment_haberler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);

////////////////////////////////////////////////////////////////////////////////////////////////////

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(yemek_listesi, "Yemek Listesi");
        viewPagerAdapter.addFragment(duyurular, "Duyurular");
        viewPagerAdapter.addFragment(haberler, "Haberler");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.yemek);
        tabLayout.getTabAt(1).setIcon(R.drawable.duyuru);
        tabLayout.getTabAt(2).setIcon(R.drawable.haberler);


    }

    private class ViewPagerAdapter  extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        //position of the fragments
        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = yemek_listesi;
                    break;
                case 1:
                    fragment = duyurular;
                    break;
                case 2:
                    fragment = haberler;
                    break;
            }
            return fragment;
        }

        //number of fragments
        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}

package com.example.admin.menagmenttool;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static FragmentManager fragmentManager;
    public String username;
    private int[] tabIcons = {
            R.drawable.news,
            R.drawable.cal,
            R.drawable.stat
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();//Get Fragment Manager
        Bundle extras = getIntent().getExtras();
        username = extras.getString("Message");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


      //  Bundle bundle=new Bundle();//to fragment
      //  bundle.putString("useremail", username);
      //  ThreeFragment fragobj=new ThreeFragment();
      //  fragobj.setArguments(bundle);

        setupTabIcons();
       // send();

    }
    public void send(){
       // Fragment frag =new ThreeFragment();
        //Bundle bundle=new Bundle();//to fragment
        //bundle.putString("User", "Nazym");
        //frag.setArguments(bundle);
        //fragmentManager.beginTransaction().replace(R.id.viewpager, frag).commit();
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), username,"Overview");

        adapter.addFragment(new ThreeFragment(), username,"Calendar");
        adapter.addFragment(new TwoFragment(), username,"Statistics" );
        viewPager.setAdapter(adapter);
    }
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String username , String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            Bundle bundle=new Bundle();//to fragment
            bundle.putString("User",username);
            fragment.setArguments(bundle);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}

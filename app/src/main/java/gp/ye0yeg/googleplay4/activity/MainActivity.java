package gp.ye0yeg.googleplay4.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.base.BaseApplication;
import gp.ye0yeg.googleplay4.fragmentFactory.FragmentFactory;
import gp.ye0yeg.googleplay4.utils.LogUtils;
import gp.ye0yeg.googleplay4.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerSlidingTabStrip tabs;
    private String[] mainTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initToolBar();
    }

    private void initData() {
//        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        MainFragmentStatePagerAdapter adapter = new MainFragmentStatePagerAdapter(getSupportFragmentManager());
        mainTitle = UIUtils.getStringArray(R.array.main_titles);
//        viewPager.setAdapter(new HomeAdapter());
        viewPager.setAdapter(adapter);
        //viewPager 和 tabs的绑定
        tabs.setViewPager(viewPager);

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.main_tabs);

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("GooglePlay");
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setNavigationIcon(R.mipmap.testback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.getContext(), "Back被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class HomeAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (mainTitle != null) {
                return mainTitle.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(UIUtils.getContext());
            tv.setText(mainTitle[position]);
            container.addView(tv);
            return tv;

        }

        //必须重新写
        @Override
        public CharSequence getPageTitle(int position) {
            return mainTitle[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class MainFragmentPagerAdapter extends FragmentPagerAdapter {
        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.getFragment(position);
            LogUtils.sf("初始化" + mainTitle[position]);
            return fragment;
        }

        //必须重新写
        @Override
        public CharSequence getPageTitle(int position) {
            return mainTitle[position];
        }

        @Override
        public int getCount() {
            if (mainTitle != null) {
                return mainTitle.length;
            }
            return 0;
        }
    }

    ;

    private class MainFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        public MainFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.getFragment(position);
            LogUtils.sf("初始化" + mainTitle[position]);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mainTitle[position];
        }

        @Override
        public int getCount() {
            if (mainTitle != null) {
                return mainTitle.length;
            }
            return 0;
        }
    }

    ;
}

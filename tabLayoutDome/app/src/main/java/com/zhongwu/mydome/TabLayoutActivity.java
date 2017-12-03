package com.zhongwu.mydome;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongwu.mydome.fragment.CustomFragment;
import com.zhongwu.mydome.utils.Constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongwu on 2017/12/3.
 */

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout mTabLayout;

    ViewPager mViewPager;

    Paint mPaint;


    private List<String> mTitleList = new ArrayList<>();

    {
        mTitleList.add("全部");
        mTitleList.add("新闻");
        mTitleList.add("图片");
        mTitleList.add("视频");
        mTitleList.add("体育");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initViews();

        initSettings();

        initGetIndicator();

        initListener();
    }

    private void initGetIndicator() {
        try {
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);

            LinearLayout layout = (LinearLayout) field.get(mTabLayout);

            Class<?> clazz = field.get(mTabLayout).getClass();
            Field paintField = clazz.getDeclaredField("mSelectedIndicatorPaint");
            paintField.setAccessible(true);
            mPaint = (Paint) paintField.get(layout);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == mViewPager.getAdapter().getCount() - 1) return;

                if (null != mPaint) {
                    mPaint.setColor(betWeenColor(position, positionOffset));
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private int betWeenColor(int position, float positionOffset) {

        int startColor = Constant.COLORS[position];
        int endColor = Constant.COLORS[position + 1];

        int redOne = (int) (Color.red(startColor) * (1 - positionOffset) + Color.red(endColor) * positionOffset);
        int greenOne = (int) (Color.green(startColor) * (1 - positionOffset) + Color.green(endColor) * positionOffset);
        int blueOne = (int) (Color.blue(startColor) * (1 - positionOffset) + Color.blue(endColor) * positionOffset);

        return Color.rgb(redOne, greenOne, blueOne);
    }


    private void initSettings() {
        for (int index = 0; index < mTabLayout.getTabCount(); index++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(index);
            if (null != tab) {
                try {
                    Field field = TabLayout.Tab.class.getDeclaredField("mView");
                    field.setAccessible(true);
                    ViewGroup tabView = (ViewGroup) field.get(tab);

                    for (int i = 0; i < tabView.getChildCount(); i++) {
                        View indexView = tabView.getChildAt(i);
                        if (indexView instanceof TextView) {
                            ((TextView) indexView).setTextColor(Constant.COLORS[index]);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.id_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);

        mViewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            CustomFragment f = new CustomFragment();

            Bundle bundle = new Bundle();
            bundle.putString("title", mTitleList.get(position));
            f.setArguments(bundle);

            return f;
        }

        @Override
        public int getCount() {
            return mTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

    }

}

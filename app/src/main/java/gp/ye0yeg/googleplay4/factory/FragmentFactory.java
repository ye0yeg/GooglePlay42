package gp.ye0yeg.googleplay4.factory;

import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

import gp.ye0yeg.googleplay4.fragment.AppFragment;
import gp.ye0yeg.googleplay4.fragment.CategoryFragment;
import gp.ye0yeg.googleplay4.fragment.GameFragment;
import gp.ye0yeg.googleplay4.fragment.HomeFragment;
import gp.ye0yeg.googleplay4.fragment.HotFragment;
import gp.ye0yeg.googleplay4.fragment.RecommendFragment;
import gp.ye0yeg.googleplay4.fragment.SubjectFragment;

/**
 * Created by Administrator on 4/27/2017.
 */

public class FragmentFactory {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_APP = 1;
    public static final int FRAGMENT_GAME = 2;
    public static final int FRAGMENT_SUBJECT = 3;
    public static final int FRAGMENT_RECOMMEND = 4;
    public static final int FRAGMENT_CATEGORY = 5;
    public static final int FRAGMENT_HOT = 6;

    static SparseArrayCompat<Fragment> cachesFragmentMap = new SparseArrayCompat<Fragment>();

    public static Fragment getFragment(int position) {

        Fragment fragment = null;

        Fragment tmpFragment = cachesFragmentMap.get(position);
        if (tmpFragment != null) {
            fragment = tmpFragment;
            return fragment;
        }
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_APP:
                fragment = new AppFragment();
                break;
            case FRAGMENT_GAME:
                fragment = new GameFragment();
                break;
            case FRAGMENT_SUBJECT:
                fragment = new SubjectFragment();
                break;
            case FRAGMENT_RECOMMEND:
                fragment = new RecommendFragment();
                break;
            case FRAGMENT_HOT:
                fragment = new HotFragment();
                break;
            case FRAGMENT_CATEGORY:
                fragment = new CategoryFragment();
                break;
            default:
                break;
        }
        //保存对应的缓存
        cachesFragmentMap.put(position, fragment);
        return fragment;
    }
    //我居然渴望他们爱我，我真可怜。——提利昂·兰尼斯特
}

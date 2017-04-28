package gp.ye0yeg.googleplay4.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gp.ye0yeg.googleplay4.base.BaseFragment;
import gp.ye0yeg.googleplay4.base.LoadingPager;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 */
public class HomeFragment extends BaseFragment {
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public LoadingPager.lodedResult initData() {
        SystemClock.sleep(1000);
        return LoadingPager.lodedResult.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("SUCCESS");
        return tv;
    }
}
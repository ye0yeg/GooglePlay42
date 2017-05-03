package gp.ye0yeg.googleplay4.fragment;

import android.os.Bundle;
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
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        return tv;
    }

    @Override
    public LoadingPager.lodedResult initData() {
        return null;
    }

    @Override
    public View initSuccessView() {

        return null;
    }
}

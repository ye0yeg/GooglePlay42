package gp.ye0yeg.googleplay4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 */
public class CategoryFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        return tv;
    }
}

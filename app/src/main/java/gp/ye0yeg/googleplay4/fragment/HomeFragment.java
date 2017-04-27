package gp.ye0yeg.googleplay4.fragment;

import android.view.View;
import android.widget.TextView;

import gp.ye0yeg.googleplay4.base.BaseFragment;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 */
public class HomeFragment extends BaseFragment {
    private TextView tv;


    @Override
    public View initView() {
        tv = new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        return tv;
    }

    @Override
    protected void initData() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        super.initData();
    }
}

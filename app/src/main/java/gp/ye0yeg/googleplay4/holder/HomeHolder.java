package gp.ye0yeg.googleplay4.holder;

import android.view.View;
import android.widget.TextView;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 5/4/2017.
 */

public class HomeHolder extends BaseHolder<String> {
    public View holderView;
    public TextView tv_tmp1;
    public TextView tv_tmp2;

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_tmp, null);
        tv_tmp1 = (TextView) view.findViewById(R.id.tmp_tv_1);
        tv_tmp2 = (TextView) view.findViewById(R.id.tmp_tv_2);
        return view;
    }

    public void refreshHolderView(String data) {
        tv_tmp1.setText("Head" + data);
        tv_tmp2.setText("Tail" + data);
    }
}

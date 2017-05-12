package gp.ye0yeg.googleplay4.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 5/12/2017.
 */
public class TestHolder extends BaseHolder<String> {

    @ViewInject(R.id.item_appinfo_iv_icon)
    ImageView iv_icon;
    @ViewInject(R.id.item_appinfo_rb_stars)
    RatingBar rb_stars;
    @ViewInject(R.id.item_appinfo_tv_des)
    TextView tv_des;
    @ViewInject(R.id.item_appinfo_tv_size)
    TextView tv_size;
    @ViewInject(R.id.item_appinfo_tv_title)
    TextView tv_title;
    @Override
    public View initHolderView() {
            View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);
            //注入
            x.view().inject(this, view);
            return view;
    }

    @Override
    public void refreshHolderView(String data) {
//        tv_title.setText("data"+data);

    }
}

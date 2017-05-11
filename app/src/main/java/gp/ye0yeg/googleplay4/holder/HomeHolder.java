package gp.ye0yeg.googleplay4.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.bean.AppInfoBean;
import gp.ye0yeg.googleplay4.conf.Constants;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 5/4/2017.
 */
public class HomeHolder extends BaseHolder<AppInfoBean> {

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

    //HolderView
    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_app_info, null);
        //注入

        x.view().inject(this, view);
        return view;
    }

    @Override
    public void refreshHolderView(AppInfoBean data) {
        tv_size.setText(data.size + "");
        tv_des.setText(data.des);
        tv_title.setText(data.name);
        iv_icon.setImageResource(R.drawable.ic_default); //默认图片
        String uri = Constants.URLS.IMAGEBASEURL + data.iconUrl;
        x.image().bind(iv_icon, uri);
        rb_stars.setRating(data.stars);
    }
}

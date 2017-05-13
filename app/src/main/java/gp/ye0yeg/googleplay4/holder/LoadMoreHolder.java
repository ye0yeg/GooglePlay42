package gp.ye0yeg.googleplay4.holder;

import android.view.View;
import android.widget.LinearLayout;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 5/13/2017.
 */
public class LoadMoreHolder extends BaseHolder<Integer> {

    @ViewInject(R.id.item_loadmore_container_loading)
    LinearLayout containerLoading;
    @ViewInject(R.id.item_loadmore_container_retry)
    LinearLayout containerRetry;
//    @ViewInject(R.id.item_loadmore_tv_retry)

    public static final int STATE_LOADING = 0;
    public static final int STATE_RETRY = 1;
    public static final int STATE_NONE = 2;

    @Override
    public View initHolderView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_loadmore, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void refreshHolderView(Integer state) {
        containerLoading.setVisibility(8);
        containerRetry.setVisibility(8);
        switch (state){
            case STATE_LOADING:
                containerLoading.setVisibility(0);
                break;
            case STATE_RETRY:
                containerRetry.setVisibility(0);
                break;
            case STATE_NONE:
                break;
            default:
                break;
        }
    }
}

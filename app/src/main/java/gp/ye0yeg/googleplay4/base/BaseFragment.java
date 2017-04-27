package gp.ye0yeg.googleplay4.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ye0ye on 2017/4/27.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadingPager loadingPager = new LoadingPager(BaseApplication.getContext()) {
            @Override
            protected int initData() {
                return BaseFragment.this.initData();
            }

            @Override
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }
        };
        return loadingPager;
    }



    /*
    * @des 必须实现， 但是不知道具体实现，定义为抽象方法让子类实现，真正加载数据,是loaddingpage的同名方法
    * @call loadData()被调用的时候
    * */
    public abstract int initData();

    /*
    *
    * */
    public abstract View initSuccessView();
}

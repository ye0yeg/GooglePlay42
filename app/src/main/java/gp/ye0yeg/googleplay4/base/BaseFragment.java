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
            protected lodedResult initData() {
                return BaseFragment.this.initData();
            }

            @Override
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }
        };
        loadingPager.loadData();
        return loadingPager;
    }



    /*
    * @des 必须实现， 但是不知道具体实现，定义为抽象方法让子类实现，真正加载数据,是loaddingpage的同名方法
    * @call loadData()被调用的时候
    * */
    public abstract LoadingPager.lodedResult initData();

    /*
    *@des 返回成功的视图
    * @call 正在加载数据完成之后，并数据加载成功，必须告知成功视图
    * */
    public abstract View initSuccessView();
}

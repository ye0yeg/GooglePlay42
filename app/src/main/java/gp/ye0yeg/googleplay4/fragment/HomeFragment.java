package gp.ye0yeg.googleplay4.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import gp.ye0yeg.googleplay4.base.BaseApplication;
import gp.ye0yeg.googleplay4.base.BaseFragment;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.base.LoadingPager;
import gp.ye0yeg.googleplay4.base.LoadingPager.lodedResult;
import gp.ye0yeg.googleplay4.base.SuperAdapter;
import gp.ye0yeg.googleplay4.bean.AppInfoBean;
import gp.ye0yeg.googleplay4.bean.HomeBean;
import gp.ye0yeg.googleplay4.conf.Constants;
import gp.ye0yeg.googleplay4.holder.HomeHolder;
import gp.ye0yeg.googleplay4.utils.LogUtils;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 * 初步断定问题xutils线程不能和该线程同步导致在获取数据的时候主线程还在运行结果获得空数据
 */
public class HomeFragment extends BaseFragment {
    private TextView tv;
    public List<AppInfoBean> datas;  //ListView的数据源
    public List<String> picture; //轮播图
    private int state = 0;
    private HomeAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        xutils2Data();
        SystemClock.sleep(100);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public lodedResult initData() {
        datas = new ArrayList<AppInfoBean>();
        picture = new ArrayList<String>();


        return LoadingPager.lodedResult.SUCCESS;
//        if (state == 0) {
//            return LoadingPager.lodedResult.SUCCESS;
//        } else if (state == 1) {
//            return LoadingPager.lodedResult.EMPTY;
//        } else {
//            return LoadingPager.lodedResult.EMPTY;
//        }
    }

    private void xutils2Data() {
        RequestParams params = new RequestParams(Constants.URLS.BASEURL + "home");
//        params.setSslSocketFactory(...); // 设置ssl
        params.addQueryStringParameter("index", "0");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                datas = homeBean.list;
                picture = homeBean.picture;
                LogUtils.s("数据初始化以后的dataSize：" + datas.size()
                );
                Toast.makeText(x.app(), "成功的提示", Toast.LENGTH_LONG).show();
                LogUtils.s(datas.get(1).name);//可以获得数据
                mAdapter.notifyDataSetChanged();
                state = 0;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage() + "\r\n 错误", Toast.LENGTH_LONG).show();
                LogUtils.s("onError");
                state = 1;

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                LogUtils.s("cancelled");
                state = 2;
            }

            @Override
            public void onFinished() {
//                //暂时在这里获得数据
//                if (state == 0) {
//                    getNewinitView();
//                }
            }
        });
    }

    //这个方法在主线程中运行。
    @Override
    public View initSuccessView() {
        if (picture.size() == 0) {
            TextView tv = new TextView(UIUtils.getContext());
            tv.setText("NO DATA");
            return tv;
        }
        mAdapter = new HomeAdapter(datas);
        ListView listView = new ListView(BaseApplication.getContext());
        listView.setCacheColorHint(Color.YELLOW);
        listView.setFastScrollEnabled(true);
        LogUtils.s("主界面的data：" + datas.size());
        listView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
        return listView;
    }

    class HomeAdapter extends SuperAdapter<AppInfoBean> {

        public HomeAdapter(List<AppInfoBean> dataSource) {
            super(dataSource);
            LogUtils.s("调用了HomeAdapter构造函数");
        }

        @Override
        public BaseHolder<AppInfoBean> getSpecialHolder() {
//            return new TestHolder();
            return new HomeHolder();
        }
    }
}
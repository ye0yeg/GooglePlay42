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

import gp.ye0yeg.googleplay4.base.BaseFragment;
import gp.ye0yeg.googleplay4.base.BaseHolder;
import gp.ye0yeg.googleplay4.base.LoadingPager;
import gp.ye0yeg.googleplay4.base.SuperAdapter;
import gp.ye0yeg.googleplay4.bean.HomeBean;
import gp.ye0yeg.googleplay4.conf.Constants;
import gp.ye0yeg.googleplay4.holder.HomeHolder;
import gp.ye0yeg.googleplay4.utils.LogUtils;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 */
public class HomeFragment extends BaseFragment {
    private TextView tv;
    private List<String> datas;
    private  int state = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public LoadingPager.lodedResult initData() {
//        state =0;
        datas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            datas.add(i + "");
        }
        SystemClock.sleep(100);
        //"http://192.168.1.100:8080/GooglePlayServer/";
        RequestParams params = new RequestParams(Constants.URLS.BASEURL+"home" );
//        params.setSslSocketFactory(...); // 设置ssl
        params.addQueryStringParameter("index", "0");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson= new Gson();
                HomeBean homeBean = gson.fromJson(result,HomeBean.class);

                Toast.makeText(x.app(), "成功的提示" + result, Toast.LENGTH_LONG).show();
                LogUtils.s("SUCCESS");
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

            }
        });

        if(state == 0){
            return LoadingPager.lodedResult.SUCCESS;
        }else if(state ==1){
            return LoadingPager.lodedResult.EMPTY;
        }else {
            return LoadingPager.lodedResult.EMPTY;
        }

    }


    @Override
    public View initSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setCacheColorHint(Color.YELLOW);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(new HomeAdapter(datas));
        return listView;
    }


    class HomeAdapter extends SuperAdapter<String> {

        public HomeAdapter(List<String> dataSource) {
            super(dataSource);
        }

        @Override
        public BaseHolder<String> getSpecialHolder() {
            return new HomeHolder();
        }
    }
}
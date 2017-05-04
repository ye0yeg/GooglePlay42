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

import java.util.ArrayList;
import java.util.List;

import gp.ye0yeg.googleplay4.base.BaseFragment;
import gp.ye0yeg.googleplay4.base.LoadingPager;
import gp.ye0yeg.googleplay4.base.SuperAdapter;
import gp.ye0yeg.googleplay4.holder.HomeHolder;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by Administrator on 4/27/2017.
 */
public class HomeFragment extends BaseFragment {
    private TextView tv;
    private List<String> datas;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public LoadingPager.lodedResult initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            datas.add(i + "");
        }

        SystemClock.sleep(1000);
        return LoadingPager.lodedResult.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setCacheColorHint(Color.YELLOW);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(new HomeAdapter(datas));
        return listView;
    }


    private class HomeAdapter extends SuperAdapter<String> {


        public HomeAdapter(List<String> dataSource) {
            super(dataSource);
            datas = dataSource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HomeHolder holder = null;
            if (convertView == null) {
                holder = new HomeHolder();
            } else {
                holder = (HomeHolder) convertView.getTag();
            }
            holder.setDataAndRefreshHolderView(datas.get(position));
            //刷新数据
            return holder.holderView;
        }
    }
}
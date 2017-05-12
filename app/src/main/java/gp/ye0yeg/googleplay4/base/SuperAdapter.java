package gp.ye0yeg.googleplay4.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gp.ye0yeg.googleplay4.utils.LogUtils;

/**
 * Created by Administrator on 5/4/2017.
 */

public abstract class SuperAdapter<ITEMBEANTYPE> extends BaseAdapter{
    private List<ITEMBEANTYPE> dataSource = new ArrayList<ITEMBEANTYPE>();

    public SuperAdapter(List<ITEMBEANTYPE> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        LogUtils.s("最重要的数据个数:"+this.dataSource.size());
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        if (this.dataSource != null) {
            return this.dataSource.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogUtils.s("触发设置数据");
        BaseHolder<ITEMBEANTYPE> holder = null;
        if (convertView == null) {
            holder = getSpecialHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //设置数据
        holder.setDataAndRefreshHolderView(dataSource.get(position));
        LogUtils.s("设置数据和dataSource的长度"+dataSource.size());
        //刷新数据
        return holder.holderView;
    }
    //Ennity/ UNITY
    /*
    * @des 返回具体Baseholder的子类
    * @call getView方法中没有converView的时候被创建
    * */
    public abstract BaseHolder<ITEMBEANTYPE> getSpecialHolder();

}




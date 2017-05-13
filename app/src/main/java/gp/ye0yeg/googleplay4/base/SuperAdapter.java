package gp.ye0yeg.googleplay4.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import gp.ye0yeg.googleplay4.holder.LoadMoreHolder;
import gp.ye0yeg.googleplay4.utils.LogUtils;

/**
 * Created by Administrator on 5/4/2017.
 */

public abstract class SuperAdapter<ITEMBEANTYPE> extends BaseAdapter {
    private List<ITEMBEANTYPE> dataSource = new ArrayList<ITEMBEANTYPE>();

    public static final int VIEWTYPE_LOADMORE = 0;
    public static final int VIEWTYPE_NORMAL = 1;
    private LoadMoreHolder loadMoreHolder;

    public SuperAdapter(List<ITEMBEANTYPE> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        if (dataSource != null) {

            LogUtils.s("最重要的数据个数:" + this.dataSource.size());
            return dataSource.size() + 1;
        }
        return 0;
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

    //ListView中的多重ViewType
    @Override
    public int getViewTypeCount() {
        return 2; //默认里是1， 现在+1变成2
    }

    /*
    * 通过滑动的时候显示position, 如果滑到底部，对应的VIEWTYPE是加载更多
    * */
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return VIEWTYPE_LOADMORE;
        }
        return VIEWTYPE_NORMAL;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogUtils.s("加载L:"+position);
        BaseHolder<ITEMBEANTYPE> holder = null;
        //缓存区域
        if (convertView == null) {
            if (getItemViewType(position) == VIEWTYPE_LOADMORE) {//如果是加载更多的类型
                holder = (BaseHolder<ITEMBEANTYPE>) getLoadMoreHolder();
            } else {
                holder = getSpecialHolder();
            }
//            holder = getSpecialHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        //“加载更多”设置数据区域
        if (getItemViewType(position) == VIEWTYPE_LOADMORE) {
            //加载更多的时候设置数据
            loadMoreHolder.setDataAndRefreshHolderView(LoadMoreHolder.STATE_LOADING);

        } else {
            //设置数据
            holder.setDataAndRefreshHolderView(dataSource.get(position));
            //刷新数据

        }
        return holder.holderView;
    }


    //Ennity/ UNITY
    /*
    * @des 返回具体Baseholder的子类
    * @call getView方法中没有converView的时候被创建
    * */
    public abstract BaseHolder<ITEMBEANTYPE> getSpecialHolder();

    /*
    * @des 返回一个加载更多的holder
    * */
    public LoadMoreHolder getLoadMoreHolder() {
        if (loadMoreHolder == null) {
            loadMoreHolder = new LoadMoreHolder();
        }
        return loadMoreHolder;
    }
}




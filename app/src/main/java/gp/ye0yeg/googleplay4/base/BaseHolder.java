package gp.ye0yeg.googleplay4.base;

import android.view.View;

/**
 * Created by Administrator on 5/4/2017.
 */

public abstract class BaseHolder<HOLDERBEANTYPE> {

    public View holderView;
    //Holder
    public HOLDERBEANTYPE mdata;

    public BaseHolder() {
        holderView = initHolderView();
        //如果有了根部局，根部局就是可以持有子对象,作为一个根部局的holder，holder中hold住子对象
        holderView.setTag(this);
    }

    /**
     * @des 设置数据，刷新视图
     * @call 需要设置数据和刷新数据的时候调用
     */
    public void setDataAndRefreshHolderView(HOLDERBEANTYPE data) {
        //设置数据
        mdata = data; //null

        //刷新界面
        refreshHolderView(data); //null
    }

    //不知道根部局是什么样的，必须实现但是不知道具体如何实现，定义成抽象方法

    /**
     * @des 初始化HolderView 根视图
     */
    public abstract View initHolderView();


    /**
     * @des 设置数据刷新的视图
     * @call 需要设置和刷新数据的时候
     */
    public abstract void refreshHolderView(HOLDERBEANTYPE data);
}

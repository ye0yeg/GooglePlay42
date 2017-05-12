package gp.ye0yeg.googleplay4.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import gp.ye0yeg.googleplay4.R;
import gp.ye0yeg.googleplay4.factory.ThreadPoolFactory;
import gp.ye0yeg.googleplay4.utils.UIUtils;

/**
 * Created by ye0ye on 2017/4/27.
 */

public abstract class LoadingPager extends FrameLayout {
    /*任何数据展示都需要四个页面
    1. 加载页面
    2. 错误页面
    3. 空页面
    4. 成功页面
*/
    public static final int STATE_LOADING = 0; //加载状态
    public static final int STATE_EMPTY = 1; //空状态
    public static final int STATE_ERROR = 2; //错误状态
    public static final int STATE_SUCCESS = 3; //成功状态

    public int curState = STATE_LOADING;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    public LoadingPager(Context context) {
        super(context);
        initCommonView();
    }

    /*
    * @des 初始化常规视图
    * */
    private void initCommonView() {
        //1 加载
        loadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        this.addView(loadingView);
        //2.错误视图
        errorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        this.addView(errorView);
        //3. 空白
        emptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        this.addView(emptyView);
        refreshUI();

    }

    /*
    * @des 根据当前状态显示不同的VIEW
    * @call 1. LoadingPager初始化的时候
    * @call 2. 真正加载完成 数据以后会调用
    * */
    private void refreshUI() {
/*        if(curState == STATE_LOADING){
            loadingView.setVisibility(View.VISIBLE);
        }else{
            loadingView.setVisibility(View.GONE);
        }
        */
        //该句同等于上面代码块，为三元运算符, 仕途显示隐藏
        loadingView.setVisibility((curState == STATE_LOADING) ? 0 : 8);
        emptyView.setVisibility((curState == STATE_EMPTY) ? 0 : 8);
        errorView.setVisibility((curState == STATE_ERROR) ? 0 : 8);
        if (successView == null && curState == STATE_SUCCESS) {
            successView = initSuccessView();
            this.addView(successView);
        }
        if (successView != null) {
//            successView.setVisibility((curState == STATE_LOADING) ? 0 : 8);
        }
    }

    /*
    * des 触发加载数据
    * @call 暴露给外界调用, 外界触发加载数据
    *
    * */
    public void loadData() {
        ThreadPoolFactory.getNormalPool().execute(new LoadDataTask());
      }


    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            //真正加载数据
            lodedResult tempState = initData();
                    //加载完成以后获得结果
                    curState = tempState.getState();
                    //刷新UI,因为刷新UI没有在主线程中，所以放到安全线程访问方法中，到主线程运行
                    UIUtils.postTaskSafely(new Runnable() {
                        @Override
                        public void run() {
                            refreshUI();
                    //异步加载数据
                }
            });
        }
    }

    /*
    * @des 必须实现， 但是不知道具体实现，定义为抽象方法让子类实现，真正加载数据
    * @call loadData()被调用的时候
    * */
    protected abstract lodedResult initData();



    /*
    * @des 返回成功视图
    * @call 真正加载数据完成之后并且数据加载成功我们必须告知成功视图
    *
    * */
    public abstract View initSuccessView();

    public enum lodedResult {
        SUCCESS(STATE_SUCCESS), ERROR(STATE_ERROR), EMPTY(STATE_EMPTY);
        int state;

        public int getState() {
            return state;
        }

        private lodedResult(int state) {
            this.state = state;
        }
    }
}

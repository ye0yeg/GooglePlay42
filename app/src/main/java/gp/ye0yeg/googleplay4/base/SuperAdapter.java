package gp.ye0yeg.googleplay4.base;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/4/2017.
 */

public abstract class SuperAdapter<ITEMBEANTYPE> extends BaseAdapter{
    public List<ITEMBEANTYPE> dataSource = new ArrayList<ITEMBEANTYPE>();

    public SuperAdapter(List<ITEMBEANTYPE> dataSource) {
        this.dataSource = dataSource;
    }

    @Override

    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        if (dataSource != null) {
            return dataSource.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

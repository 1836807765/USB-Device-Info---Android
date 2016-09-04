package aws.apps.usbDeviceEnumerator.ui.main.tabs;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import aws.apps.usbDeviceEnumerator.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TabViewHolder {

    private final View rootView;
    @BindView(android.R.id.list)
    protected ListView list;
    @BindView(android.R.id.empty)
    protected View empty;
    @BindView(R.id.count)
    protected TextView count;

    public TabViewHolder(final View rootView) {
        ButterKnife.bind(this, rootView);

        this.rootView = rootView;
        this.list.setEmptyView(empty);
        this.list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public ListView getList() {
        return list;
    }

    public TextView getCount() {
        return count;
    }
}

package aws.apps.usbDeviceEnumerator.ui.debug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import aws.apps.usbDeviceEnumerator.R;
import aws.apps.usbDeviceEnumerator.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDumpFragment extends Fragment implements Reloadable {
    private static final int LAYOUT_ID = R.layout.fragment_monospace_textview;

    @BindView(android.R.id.content)
    protected TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved) {
        return inflater.inflate(LAYOUT_ID, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.bind(this, view);
        textView.setMaxLines(Integer.MAX_VALUE);
    }

    @Override
    public void reload() {
        if (isAdded() && getActivity() != null && getView() != null) {
            final String directory = Constants.PATH_SYS_BUS_USB;
            textView.setText(DeviceDumper.getDump(getContext(), directory));
        }
    }


}

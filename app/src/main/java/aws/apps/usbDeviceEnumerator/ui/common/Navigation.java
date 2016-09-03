package aws.apps.usbDeviceEnumerator.ui.common;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import aws.apps.usbDeviceEnumerator.R;
import aws.apps.usbDeviceEnumerator.ui.usbinfo.BaseInfoFragment;
import aws.apps.usbDeviceEnumerator.ui.usbinfo.InfoFragmentFactory;
import aws.apps.usbDeviceEnumerator.ui.usbinfo.UsbInfoActivity;
import aws.apps.usbDeviceEnumerator.usb.sysbususb.SysBusUsbDevice;

public class Navigation {
    private static final String TAG = Navigation.class.getSimpleName();
    private static final int FRAGMENT_CONTAINER = R.id.fragment_container;
    private static final int DEFAULT_FRAGMENT_TRANSACTION = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;

    private final AppCompatActivity activity;

    public Navigation(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void showAndroidUsbDeviceInfo(String device) {
        if (isSmallScreen()) {
            Intent i = new Intent(activity.getApplicationContext(), UsbInfoActivity.class);
            i.putExtra(UsbInfoActivity.EXTRA_TYPE, BaseInfoFragment.TYPE_ANDROID_INFO);
            i.putExtra(UsbInfoActivity.EXTRA_DATA_ANDROID, device);
            startActivity(i);
        } else {
            final Fragment fragment = InfoFragmentFactory.getFragment(device);
            stackFragment(fragment);
        }
    }

    public void showLinuxUsbDeviceInfo(SysBusUsbDevice device) {
        if (isSmallScreen()) {
            Intent i = new Intent(activity.getApplicationContext(), UsbInfoActivity.class);
            i.putExtra(UsbInfoActivity.EXTRA_TYPE, BaseInfoFragment.TYPE_LINUX_INFO);
            i.putExtra(UsbInfoActivity.EXTRA_DATA_LINUX, device);
            startActivity(i);
        } else {
            final Fragment fragment = InfoFragmentFactory.getFragment(device);
            stackFragment(fragment);
        }
    }

    public boolean isSmallScreen() {
        final boolean res = activity.findViewById(FRAGMENT_CONTAINER) == null;
        Log.d(TAG, "^ Is " + activity.getClass().getName() + " running in a small screen? " + res);
        return res;
    }

    public void stackFragment(Fragment fragment) {
        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(FRAGMENT_CONTAINER, fragment);
        ft.setTransition(DEFAULT_FRAGMENT_TRANSACTION);

        ft.commit();
    }

    private void startActivity(final Intent intent) {
        ActivityCompat.startActivity(activity, intent, null);
    }
}

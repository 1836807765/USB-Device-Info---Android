/*******************************************************************************
 * Copyright 2011 Alexandros Schillings
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package aws.apps.usbDeviceEnumerator.ui.usbinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import aws.apps.usbDeviceEnumerator.R;
import aws.apps.usbDeviceEnumerator.usb.sysbususb.SysBusUsbDevice;

public class UsbInfoActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_DATA_ANDROID = "data_android";
    public static final String EXTRA_DATA_LINUX = "data_linux";

    /**
     * Called when the activity is first created.
     */

    private int mType;
    private String mAndroidKey;
    private SysBusUsbDevice mLinuxDevice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_usb_info);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            mType = b.getInt(EXTRA_TYPE);
            mAndroidKey = b.getString(EXTRA_DATA_ANDROID);
            mLinuxDevice = b.getParcelable(EXTRA_DATA_LINUX);

            if (mType == BaseInfoFragment.TYPE_ANDROID_INFO) {
                Fragment f = new AndroidUsbInfoFragment(mAndroidKey);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, f);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                ft.commit();
            } else if (mType == BaseInfoFragment.TYPE_LINUX_INFO) {
                Fragment f = new LinuxUsbInfoFragment(mLinuxDevice);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, f);
                ft.setTransition(FragmentTransaction.TRANSIT_NONE);

                ft.commit();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }
}

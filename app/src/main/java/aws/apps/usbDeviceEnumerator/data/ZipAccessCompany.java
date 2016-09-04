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
package aws.apps.usbDeviceEnumerator.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import aws.apps.usbDeviceEnumerator.R;

public class ZipAccessCompany {
    private final String TAG = this.getClass().getName();
    private Context context;

    private String localZipLocation = "";
    private String localZipFullPath = "";

    public ZipAccessCompany(Context context) {
        this.context = context;
        doPathStuff();
    }

    private void doPathStuff() {
        localZipLocation = Environment.getExternalStorageDirectory() + context.getString(R.string.sd_zip_location_company);
        localZipFullPath = localZipLocation + context.getString(R.string.sd_zip_name_company);
    }

    public String getLocalZipFullPath() {
        return localZipFullPath;
    }

    public String getLocalZipLocation() {
        return localZipLocation;
    }

    public Bitmap getLogo(final String logo) {
        Log.d(TAG, "^ Getting logo '" + logo + "' from '" + localZipFullPath + "'");

        Bitmap result = null;
        if (TextUtils.isEmpty(logo)) {
            result = null;
        } else {
            try {
                FileInputStream fis = new FileInputStream(localZipFullPath);
                ZipInputStream zis = new ZipInputStream(fis);
                ZipEntry ze = null;

                // Until we find their map, or we run out of options
                if (zis.getNextEntry() != null) ze = zis.getNextEntry();

                while ((ze = zis.getNextEntry()) != null) {
                    if (ze.getName().equals(logo)) {
                        Log.d(TAG, "^ Found it!");
                        result = BitmapFactory.decodeStream(zis);
                        break;
                    } else {

                    }
                }

                zis.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "^ Error opening zip file: ", e);
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "^ Error opening zip file: ", e);
                e.printStackTrace();
            }
        }
        return result;
    }
}

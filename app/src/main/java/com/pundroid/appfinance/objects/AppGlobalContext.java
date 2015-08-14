package com.pundroid.appfinance.objects;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.Log;

import com.pundroid.appfinance.databases.DbAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by pumba30 on 12.08.2015.
 */
public class AppGlobalContext extends Application {
    private static final String ICONS_FOLDER = "icons";
    private static final String TAG = AppGlobalContext.class.getSimpleName();
    private File iconFolder;

    public static final int IMAGE_WIDTH = 64;
    public static final int IMAGE_HEIGHT = 64;
    private static DbAdapter dbAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        dbAdapter = new DbAdapter(this);
    }

    public static DbAdapter getInstanceDbAdapter() {
        Log.d(TAG, "getInstanceSimpleAdapter");

        return dbAdapter;
    }


    public String getIconsFolder() throws IOException {

        if (iconFolder == null) {
            iconFolder = new File(getApplicationInfo().dataDir + "/" + ICONS_FOLDER);
            Log.d(TAG, "new File");
        }

        if (!iconFolder.exists()) {
            try {
                if (iconFolder.mkdir()) {
                    copyIconFolder(ICONS_FOLDER);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return iconFolder.getAbsolutePath();
    }

    // copying the files from folder assets in the application folder "icons"
    private void copyIconFolder(String nameFolder) throws IOException {

        String destinationFolder = getApplicationInfo().dataDir + "/" + ICONS_FOLDER;
        Log.d(TAG, destinationFolder);

        AssetManager assetManager = getAssets();
        String[] files = null;

        // get a list of files
        try {
            files = assetManager.list(nameFolder);
        } catch (IOException ex) {
            Log.e(TAG, "Failed to get asset file list.\n" + ex);
        }

        //reading the files and copying  to the appropriate folder
        for (String filename : files) {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = assetManager.open(ICONS_FOLDER + "/" + filename);
                outputStream = new FileOutputStream(iconFolder + "/" + filename);
                copyFile(inputStream, outputStream);
            } catch (IOException ex) {
                Log.e(TAG, "Failed to copy asset file list.\n" + ex);
            } finally {
                assert inputStream != null;
                inputStream.close();
                assert outputStream != null;
                outputStream.close();
            }
        }
    }

    private void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
    }
}























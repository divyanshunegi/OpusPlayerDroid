package com.divyanshu.opusplayer;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.divyanshu.opusplayer.utils.Utils;

/**
 * Created by divyanshunegi on 4/9/17.
 * Project : OpusPlayerDroid
 */
public class PermissionPage extends RuntimePermissionsActivity {

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_activity);
        super.requestAppPermissions(new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        R.string.runtime_permissions_txt,
                        REQUEST_PERMISSIONS);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if(!Utils.opusfileExists("working_opus_file.opus")){
            Utils.copyAssets(this);
        }
        else{
            Log.i(PermissionPage.class.getSimpleName(),"Files Exists!");
        }
        startActivity(new Intent(this,MainActivity.class));
    }
}

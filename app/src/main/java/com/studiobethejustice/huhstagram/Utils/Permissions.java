package com.studiobethejustice.huhstagram.Utils;


import android.Manifest;

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_EXTERNAL_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_EXTERNA_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
}

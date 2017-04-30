package com.longisland_219408hotmail.mathematicswolf.permission;

/**
 * Created by Chatikyan on 24.11.2016.
 */

public interface SinglePermissionCallback {

    void onPermissionResult(boolean permissionGranted, boolean isPermissionDeniedForever);
}

package com.longisland_219408hotmail.mathematicswolf.permission;

import java.util.List;

/**
 * Created by Chatikyan on 24.11.2016.
 */

public interface MultiplePermissionCallback {

    void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions);

    void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions);
}

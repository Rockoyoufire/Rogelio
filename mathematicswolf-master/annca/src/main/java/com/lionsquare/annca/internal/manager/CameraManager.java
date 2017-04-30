package com.lionsquare.annca.internal.manager;

import android.content.Context;


import com.lionsquare.annca.internal.configuration.AnncaConfiguration;
import com.lionsquare.annca.internal.configuration.ConfigurationProvider;
import com.lionsquare.annca.internal.manager.impl.CameraHandler;
import com.lionsquare.annca.internal.manager.impl.ParametersHandler;
import com.lionsquare.annca.internal.manager.listener.CameraCloseListener;
import com.lionsquare.annca.internal.manager.listener.CameraOpenListener;
import com.lionsquare.annca.internal.manager.listener.CameraPhotoListener;
import com.lionsquare.annca.internal.manager.listener.CameraVideoListener;
import com.lionsquare.annca.internal.utils.Size;

import java.io.File;


/**
 * Created by memfis on 8/14/16.
 */
public interface CameraManager<CameraId, SurfaceListener, CameraParameters, Camera> {

    void initializeCameraManager(ConfigurationProvider configurationProvider, Context context);

    void openCamera(CameraId cameraId, CameraOpenListener<CameraId, SurfaceListener> cameraOpenListener);

    void closeCamera(CameraCloseListener<CameraId> cameraCloseListener);

    void setFlashMode(@AnncaConfiguration.FlashMode int flashMode);

    void takePhoto(File photoFile, CameraPhotoListener cameraPhotoListener);

    void startVideoRecord(File videoFile, CameraVideoListener cameraVideoListener);

    Size getPhotoSizeForQuality(@AnncaConfiguration.MediaQuality int mediaQuality);

    void stopVideoRecord();

    void releaseCameraManager();

    CameraId getCurrentCameraId();

    CameraId getFaceFrontCameraId();

    CameraId getFaceBackCameraId();

    int getNumberOfCameras();

    int getFaceFrontCameraOrientation();

    int getFaceBackCameraOrientation();

    boolean isVideoRecording();

    boolean handleParameters(ParametersHandler<CameraParameters> parameters);

    void handleCamera(CameraHandler<Camera> cameraHandler);
}

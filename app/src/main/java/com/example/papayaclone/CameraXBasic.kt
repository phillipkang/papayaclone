package com.example.papayaclone


import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig

/** It implements CameraXConfig.Provider */
class CameraXBasic: Application(), CameraXConfig.Provider {

    /** @returns Camera2 default configuration */
    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}
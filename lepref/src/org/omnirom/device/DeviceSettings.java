/*
 *  LeEco Extras Settings Module for Resurrection Remix ROMs
 *  Made by @andr68rus 2017
 */

package org.omnirom.device;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

import android.util.Log;
import android.os.SystemProperties;
import java.io.*;
import android.widget.Toast;  
import android.preference.ListPreference;

import org.omnirom.device.R;

public class DeviceSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	private static final boolean DEBUG = false;
	private static final String TAG = "LePref";

	private static final String QC_SYSTEM_PROPERTY = "persist.sys.le_fast_chrg_enable";
	private static final String HAL3_SYSTEM_PROPERTY = "persist.camera.HAL3.enabled";
	private static final String GPS_THROTTLE_SYSTEM_PROPERTY = "persist.ps.gpsthrottle";

	private static final String GPS_THROTTLE_SYS_SYSTEM_PROPERTY = "persist.ps.gpsthrottle.sys";

    private static final String SYSTEM_PROPERTY_PM_PROXIMITY_OFF = "persist.pm.proximity_off";

    private static final String SYSTEM_PROPERTY_PM_PROXIMITY_ON = "persist.pm.proximity_on";

    private static final String SYSTEM_PROPERTY_PM_PROXIMITY_BON = "persist.pm.proximity_bon";

    private static final String SYSTEM_PROPERTY_PM_PROXIMITY_BOFF = "persist.pm.proximity_boff";

    private static final String SYSTEM_PROPERTY_PM_ON_AFTER_CALL = "persist.pm.on_after_call";

    private static final String SYSTEM_PROPERTY_PM_LOCK_INCALL = "persist.pm.lock_incall";

    private static final String SYSTEM_PROPERTY_PM_DEEP_IDLE = "persist.pm.deep_idle";

    private static final String SYSTEM_PROPERTY_PM_CPUIDLE_DS = "persist.pm.cpuidle_ds";

    private static final String SYSTEM_PROPERTY_PM_BG_DISABLE = "persist.pm.bg_disable";
    private static final String SYSTEM_PROPERTY_PM_BG_SO_DISABLE = "persist.pm.bg_so_disable";

    private static final String SYSTEM_PROPERTY_SYS_HALL_SENSOR = "persist.sys.hall_sensor";

    private static final String SYSTEM_PROPERTY_PM_READER_MODE = "persist.pm.reader_mode";

    private static final String SYSTEM_PROPERTY_PM_HIDE_GMS = "persist.pm.hide_gms";
    private static final String SYSTEM_PROPERTY_PM_FORCE_GMS = "persist.pm.force_gms";

    private static final String SYSTEM_PROPERTY_IMS_ENABLED = "persist.ims.enabled";

    private static final String SYSTEM_PROPERTY_CAMERA_FOCUS_FIX = "persist.camera.focus_fix";

    private static final String SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK = "persist.pm.krnl_wl_block";
    private static final String SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX = "persist.pm.krnl_wl_qcom_rx";

    private static final String SYSTEM_PROPERTY_PM_STOP_SVC = "persist.pm.stop_svc";

    private static final String SYSTEM_PROPERTY_PM_KTHREADS = "persist.pm.kthreads";

    private static final String SYSTEM_PROPERTY_FORCE_EFFECTS = "persist.audio.force_effects";
    private static final String SYSTEM_PROPERTY_EFFECTS_FOR_ALL_STREAMS = "persist.audio.effects_all";
    private static final String SYSTEM_PROPERTY_EFFECTS_GLOBAL = "persist.audio.effects_global";

    private static final String SYSTEM_PROPERTY_DOZE_REDUCE_BRIGHTNESS = "persist.doze.reduce_brightness";
    private static final String SYSTEM_PROPERTY_DOZE_USE_AUTOBRIGHTNESS = "persist.doze.use_autobrightness";
    private static final String SYSTEM_PROPERTY_DOZE_KEEP_NOTIFICATION = "persist.doze.keep_notif";
    private static final String SYSTEM_PROPERTY_DOZE_PULSE_ON_CHARGER = "persist.doze.pulse_on_charger";
    private static final String SYSTEM_PROPERTY_DOZE_PULSING_KEEP_AWAKE = "persist.doze.pulsing_keep_awake";

    private static final String SYSTEM_PROPERTY_DOZE_OVR_DOZE = "persist.doze.ovr_sup_doze";
    private static final String SYSTEM_PROPERTY_DOZE_OVR_SUSPEND = "persist.doze.ovr_sup_suspend";

    private static final String SYSTEM_PROPERTY_PM_IDLE_DISABLE = "persist.pm.idle_disable";
    private static final String SYSTEM_PROPERTY_PM_IDLE_DISABLE_SO = "persist.pm.idle_disable_so";

    private static final String SYSTEM_PROPERTY_PM_FRMWK_WL_BLOCK = "persist.pm.frmwk_wl_block";

    private static final String SYSTEM_PROPERTY_PM_GMS_ENABLE_SO = "persist.pm.gms_enable_so";
    private static final String SYSTEM_PROPERTY_PM_GPS_UNRESTRICTED = "persist.ps.gps_unrestricted";

    private static final String SYSTEM_PROPERTY_PM_HIGH_PERF = "persist.pm.high_perf";

    private static final String SYSTEM_PROPERTY_FX_HP_DETECT = "persist.fx.hp_detect";

    private static final String SYSTEM_PROPERTY_QFP = "persist.qfp";

    private static final String SYSTEM_PROPERTY_PM_BLOCK_GMS_WL = "persist.pm.block_gms_wl";


    private static final String SYSTEM_PROPERTY_PM_EXTREME = "persist.pm.extreme";
    private static final String SYSTEM_PROPERTY_PM_EXTREME_SO = "persist.pm.extreme_so";

    private static final String SYSTEM_PROPERTY_PM_FSYNC = "persist.pm.fsync";

	private SwitchPreference mEnableQC;

	private SwitchPreference mEnableHAL3;

	private SwitchPreference mEnableGPSThrottle;
	private SwitchPreference mEnableGPSThrottleSys;

	private ListPreference mAKT;

	private SwitchPreference mProximityOff;
	private SwitchPreference mProximityOn;
	private SwitchPreference mProximityBOn;
	private SwitchPreference mProximityBOff;
	private SwitchPreference mOnAfterCall;
	private SwitchPreference mLockIncallCall;

	private SwitchPreference mHallSensor;
	private SwitchPreference mQfp;
	private SwitchPreference mDeepIdle;

	private SwitchPreference mCpuidleDeepestState;

	private SwitchPreference mReaderMode;

	private SwitchPreference mHideGMS;
	private SwitchPreference mForceGMS;

	private SwitchPreference mImsEnabled;

	private SwitchPreference mCameraFocusFix;

	private SwitchPreference mKrnlWlBlock;
	private SwitchPreference mKrnlWlQcomRX;
	private SwitchPreference mStopSvc;
	private SwitchPreference mKThreads;

	private SwitchPreference mForceEffects;
	private SwitchPreference mForceEffectsForAllStreams;
	private SwitchPreference mForceEffectsGlobal;

	private SwitchPreference mBgDisable;
	private SwitchPreference mBgSoDisable;

	private SwitchPreference mDozeReduceBrightness;

	private SwitchPreference mDozeEnabled;
	private SwitchPreference mDozeKeepNotification;
	private SwitchPreference mDozeAlwaysOn;
	private SwitchPreference mDozeAlwaysOnCharger;
	private SwitchPreference mDozeUseAutobrightness;
	private SwitchPreference mDozeMediaInfo;

	private SwitchPreference mDozePulseOnCharger;
	private SwitchPreference mDozePulsingKeepAwake;

	private SwitchPreference mDozeOvrDoze;
	private SwitchPreference mDozeOvrSuspend;

	private SwitchPreference mPmIdleDisableSo;
	private SwitchPreference mPmIdleDisable;

	private SwitchPreference mPmGmsEnableSo;

	private SwitchPreference mPmGpsUnrestricted;

	private SwitchPreference mFrmwkWlBlock;

	private SwitchPreference mHighPerf;

	private SwitchPreference mHPDetect;
	private SwitchPreference mBlockGmsWl;

	private SwitchPreference mPmExtreme;
	private SwitchPreference mPmExtremeSo;

	private SwitchPreference mPmFsync;

    private Preference mSaveLog;
	
    private Context mContext;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.le_settings);
        mContext = getApplicationContext();
        
        mEnableQC = (SwitchPreference) findPreference(QC_SYSTEM_PROPERTY);
        if( mEnableQC != null ) {
            mEnableQC.setChecked(SystemProperties.getBoolean(QC_SYSTEM_PROPERTY, false));
            mEnableQC.setOnPreferenceChangeListener(this);
        }
                
        mEnableHAL3 = (SwitchPreference) findPreference(HAL3_SYSTEM_PROPERTY);
        if( mEnableHAL3 != null ) {
            mEnableHAL3.setChecked(SystemProperties.getBoolean(HAL3_SYSTEM_PROPERTY, false));
            mEnableHAL3.setOnPreferenceChangeListener(this);
        }

        mEnableGPSThrottle = (SwitchPreference) findPreference(GPS_THROTTLE_SYSTEM_PROPERTY);
        if( mEnableGPSThrottle != null ) {
            mEnableGPSThrottle.setChecked(SystemProperties.getBoolean(GPS_THROTTLE_SYSTEM_PROPERTY, false));
            mEnableGPSThrottle.setOnPreferenceChangeListener(this);
        }

        mEnableGPSThrottleSys = (SwitchPreference) findPreference(GPS_THROTTLE_SYS_SYSTEM_PROPERTY);
        if( mEnableGPSThrottleSys != null ) {
            mEnableGPSThrottleSys.setChecked(SystemProperties.getBoolean(GPS_THROTTLE_SYS_SYSTEM_PROPERTY, false));
            mEnableGPSThrottleSys.setOnPreferenceChangeListener(this);
        }

        mProximityOff = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_OFF);
        if( mProximityOff != null ) {
            mProximityOff.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_OFF, false));
            mProximityOff.setOnPreferenceChangeListener(this);
        }

        mProximityOn = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_ON);
        if( mProximityOn != null ) {
            mProximityOn.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_ON, false));
            mProximityOn.setOnPreferenceChangeListener(this);
        }

        mProximityBOn = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_BON);
        if( mProximityBOn != null ) {
            mProximityBOn.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_BON, false));
            mProximityBOn.setOnPreferenceChangeListener(this);
        }


        mProximityBOff = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_PROXIMITY_BOFF);
        if( mProximityBOff != null ) {
            mProximityBOff.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_PROXIMITY_BOFF, false));
            mProximityBOff.setOnPreferenceChangeListener(this);
        }

        mOnAfterCall = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_ON_AFTER_CALL);
        if( mOnAfterCall != null ) {
            mOnAfterCall.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_ON_AFTER_CALL, false));
            mOnAfterCall.setOnPreferenceChangeListener(this);
        }


        mLockIncallCall = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_LOCK_INCALL);
        if( mLockIncallCall != null ) {
            mLockIncallCall.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_LOCK_INCALL, false));
            mLockIncallCall.setOnPreferenceChangeListener(this);
        }

        mHallSensor = (SwitchPreference) findPreference(SYSTEM_PROPERTY_SYS_HALL_SENSOR);
        if( mHallSensor != null ) {
            mHallSensor.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_SYS_HALL_SENSOR, false));
            mHallSensor.setOnPreferenceChangeListener(this);
        }

        mDeepIdle = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_DEEP_IDLE);
        if( mDeepIdle != null ) {
            mDeepIdle.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_DEEP_IDLE, false));
            mDeepIdle.setOnPreferenceChangeListener(this);
        }
        
        mCpuidleDeepestState = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_CPUIDLE_DS);
        if( mCpuidleDeepestState != null ) {
            mCpuidleDeepestState.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_CPUIDLE_DS, false));
            mCpuidleDeepestState.setOnPreferenceChangeListener(this);
        }

        mReaderMode = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_READER_MODE);
        if( mReaderMode != null ) {
            mReaderMode.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_READER_MODE, false));
            mReaderMode.setOnPreferenceChangeListener(this);
        }


        mHideGMS = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_HIDE_GMS);
        if( mHideGMS != null ) {
            mHideGMS.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_HIDE_GMS, false));
            mHideGMS.setOnPreferenceChangeListener(this);
        }

        mForceGMS = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_FORCE_GMS);
        if( mForceGMS != null ) {
            mForceGMS.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_FORCE_GMS, false));
            mForceGMS.setOnPreferenceChangeListener(this);
        }


        mImsEnabled = (SwitchPreference) findPreference(SYSTEM_PROPERTY_IMS_ENABLED);
        if( mImsEnabled != null ) {
            mImsEnabled.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_IMS_ENABLED, false));
            mImsEnabled.setOnPreferenceChangeListener(this);
        }

        mCameraFocusFix = (SwitchPreference) findPreference(SYSTEM_PROPERTY_CAMERA_FOCUS_FIX);
        if( mCameraFocusFix != null ) {
            mCameraFocusFix.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_CAMERA_FOCUS_FIX, false));
            mCameraFocusFix.setOnPreferenceChangeListener(this);
        }



        mKrnlWlBlock = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK);
        if( mKrnlWlBlock != null ) {
            mKrnlWlBlock.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_KRNL_WL_BLOCK, false));
            mKrnlWlBlock.setOnPreferenceChangeListener(this);
        }

        mKrnlWlQcomRX = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX);
        if( mKrnlWlQcomRX != null ) {
            mKrnlWlQcomRX.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_KRNL_WL_QCOM_RX, false));
            mKrnlWlQcomRX.setOnPreferenceChangeListener(this);
        }

        mStopSvc = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_STOP_SVC);
        if( mStopSvc != null ) {
            mStopSvc.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_STOP_SVC, false));
            mStopSvc.setOnPreferenceChangeListener(this);
        }

        mKThreads = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_KTHREADS);
        if( mKThreads != null ) {
            mKThreads.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_KTHREADS, false));
            mKThreads.setOnPreferenceChangeListener(this);
        }

        mForceEffects = (SwitchPreference) findPreference(SYSTEM_PROPERTY_FORCE_EFFECTS);
        if( mForceEffects != null ) {
            mForceEffects.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_FORCE_EFFECTS, false));
            mForceEffects.setOnPreferenceChangeListener(this);
        }

        mForceEffectsForAllStreams = (SwitchPreference) findPreference(SYSTEM_PROPERTY_EFFECTS_FOR_ALL_STREAMS);
        if( mForceEffectsForAllStreams != null ) {
            mForceEffectsForAllStreams.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_EFFECTS_FOR_ALL_STREAMS, false));
            mForceEffectsForAllStreams.setOnPreferenceChangeListener(this);
        }

        mForceEffectsGlobal = (SwitchPreference) findPreference(SYSTEM_PROPERTY_EFFECTS_GLOBAL);
        if( mForceEffectsGlobal != null ) {
            mForceEffectsGlobal.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_EFFECTS_GLOBAL, false));
            mForceEffectsGlobal.setOnPreferenceChangeListener(this);
        }

        mBgDisable = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_BG_DISABLE);
        if( mBgDisable != null ) {
            mBgDisable.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_BG_DISABLE, false));
            mBgDisable.setOnPreferenceChangeListener(this);
        }

        mBgSoDisable = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_BG_SO_DISABLE);
        if( mBgSoDisable != null ) {
            mBgSoDisable.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_BG_SO_DISABLE, false));
            mBgSoDisable.setOnPreferenceChangeListener(this);
        }

        mDozeReduceBrightness = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_REDUCE_BRIGHTNESS);
        if( mDozeReduceBrightness != null ) {
            mDozeReduceBrightness.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_REDUCE_BRIGHTNESS, false));
            mDozeReduceBrightness.setOnPreferenceChangeListener(this);
        }


        mDozeEnabled = (SwitchPreference) findPreference("ss.sec.doze_enabled");
        if( mDozeEnabled != null ) {
            mDozeEnabled.setChecked(getSystemSetting("sec.doze_enabled", false));
            mDozeEnabled.setOnPreferenceChangeListener(this);
        }

        mDozeAlwaysOn = (SwitchPreference) findPreference("ss.sec.doze_always_on");
        if( mDozeAlwaysOn != null ) {
            mDozeAlwaysOn.setChecked(getSystemSetting("sec.doze_always_on", false));
            mDozeAlwaysOn.setOnPreferenceChangeListener(this);
        }

        mDozeAlwaysOnCharger = (SwitchPreference) findPreference("ss.sec.doze_always_on_charger");
        if( mDozeAlwaysOnCharger != null ) {
            mDozeAlwaysOnCharger.setChecked(getSystemSetting("sec.doze_always_on_charger", false));
            mDozeAlwaysOnCharger.setOnPreferenceChangeListener(this);
        }

        mDozeUseAutobrightness = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_USE_AUTOBRIGHTNESS);
        if( mDozeUseAutobrightness != null ) {
            mDozeUseAutobrightness.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_USE_AUTOBRIGHTNESS, false));
            mDozeUseAutobrightness.setOnPreferenceChangeListener(this);
        }

        mDozeMediaInfo = (SwitchPreference) findPreference("ss.sys.force_ambient_for_media");
        if( mDozeMediaInfo != null ) {
            mDozeMediaInfo.setChecked(getSystemSetting("sys.force_ambient_for_media", false));
            mDozeMediaInfo.setOnPreferenceChangeListener(this);
        }

        mDozeKeepNotification = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_KEEP_NOTIFICATION);
        if( mDozeKeepNotification != null ) {
            mDozeKeepNotification.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_KEEP_NOTIFICATION, false));
            mDozeKeepNotification.setOnPreferenceChangeListener(this);
        }

        mDozePulseOnCharger = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_PULSE_ON_CHARGER);
        if( mDozePulseOnCharger != null ) {
            mDozePulseOnCharger.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_PULSE_ON_CHARGER, false));
            mDozePulseOnCharger.setOnPreferenceChangeListener(this);
        }

        mDozePulsingKeepAwake = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_PULSING_KEEP_AWAKE);
        if( mDozePulsingKeepAwake != null ) {
            mDozePulsingKeepAwake.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_PULSING_KEEP_AWAKE, false));
            mDozePulsingKeepAwake.setOnPreferenceChangeListener(this);
        }

        mDozeOvrDoze = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_OVR_DOZE);
        if( mDozeOvrDoze != null ) {
            mDozeOvrDoze.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_OVR_DOZE, false));
            mDozeOvrDoze.setOnPreferenceChangeListener(this);
        }

        mDozeOvrSuspend = (SwitchPreference) findPreference(SYSTEM_PROPERTY_DOZE_OVR_SUSPEND);
        if( mDozeOvrSuspend != null ) {
            mDozeOvrSuspend.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_DOZE_OVR_SUSPEND, false));
            mDozeOvrSuspend.setOnPreferenceChangeListener(this);
        }

        mPmIdleDisable = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_IDLE_DISABLE);
        if( mPmIdleDisable != null ) {
            mPmIdleDisable.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_IDLE_DISABLE, false));
            mPmIdleDisable.setOnPreferenceChangeListener(this);
        }

        mPmIdleDisableSo = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_IDLE_DISABLE_SO);
        if( mPmIdleDisableSo != null ) {
            mPmIdleDisableSo.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_IDLE_DISABLE_SO, false));
            mPmIdleDisableSo.setOnPreferenceChangeListener(this);
        }

        mPmGmsEnableSo = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_GMS_ENABLE_SO);
        if( mPmGmsEnableSo != null ) {
            mPmGmsEnableSo.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_GMS_ENABLE_SO, false));
            mPmGmsEnableSo.setOnPreferenceChangeListener(this);
        }

        mPmGpsUnrestricted = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_GPS_UNRESTRICTED);
        if( mPmGpsUnrestricted != null ) {
            mPmGpsUnrestricted.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_GPS_UNRESTRICTED, false));
            mPmGpsUnrestricted.setOnPreferenceChangeListener(this);
        }
     

        mFrmwkWlBlock = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_FRMWK_WL_BLOCK);
        if( mFrmwkWlBlock != null ) {
            mFrmwkWlBlock.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_FRMWK_WL_BLOCK, false));
            mFrmwkWlBlock.setOnPreferenceChangeListener(this);
        }

        mHighPerf = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_HIGH_PERF);
        if( mHighPerf != null ) {
            mHighPerf.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_HIGH_PERF, false));
            mHighPerf.setOnPreferenceChangeListener(this);
        }

        mQfp = (SwitchPreference) findPreference(SYSTEM_PROPERTY_QFP);
        if( mQfp != null ) {
            mQfp.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_QFP, false));
            mQfp.setOnPreferenceChangeListener(this);
        }

        mHPDetect = (SwitchPreference) findPreference(SYSTEM_PROPERTY_FX_HP_DETECT);
        if( mHPDetect != null ) {
            mHPDetect.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_FX_HP_DETECT, false));
            mHPDetect.setOnPreferenceChangeListener(this);
        }


        mBlockGmsWl = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_BLOCK_GMS_WL);
        if( mBlockGmsWl != null ) {
            mBlockGmsWl.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_BLOCK_GMS_WL, false));
            mBlockGmsWl.setOnPreferenceChangeListener(this);
        }

        mPmExtreme = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_EXTREME);
        if( mPmExtreme != null ) {
            mPmExtreme.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_EXTREME, false));
            mPmExtreme.setOnPreferenceChangeListener(this);
        }

        mPmExtremeSo = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_EXTREME_SO);
        if( mPmExtremeSo != null ) {
            mPmExtremeSo.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_EXTREME_SO, false));
            mPmExtremeSo.setOnPreferenceChangeListener(this);
        }

        mPmFsync = (SwitchPreference) findPreference(SYSTEM_PROPERTY_PM_FSYNC);
        if( mPmFsync != null ) {
            mPmFsync.setChecked(SystemProperties.getBoolean(SYSTEM_PROPERTY_PM_FSYNC, false));
            mPmFsync.setOnPreferenceChangeListener(this);
        }


        mSaveLog = findPreference("save_log_key");
        if( mSaveLog != null ) {
            mSaveLog.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(final Preference preference) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
                    Date now = new Date();
                    String suffix = formatter.format(now);
                	SystemProperties.set("vendor.lepref.log_suffix", suffix);
                	SystemProperties.set("vendor.leprefs.savelog", "1");
                    return false;
                }
            });
        }
        if (DEBUG) Log.d(TAG, "Initializating done");
        
    }

    private void setSystemSetting(String key, boolean value) {
        int valueInt = value ? 1 : 0;
        String section = key.substring(0,3);
        String keyname = key.substring(4);
        if( section.equals("sys") ) {
                Settings.System.putInt(mContext.getContentResolver(), keyname, valueInt);
        } else if ( section.equals("sec") ) {
                Settings.Secure.putInt(mContext.getContentResolver(), keyname, valueInt);
        } else if ( section.equals("glb") ) {
                Settings.Global.putInt(mContext.getContentResolver(), keyname, valueInt);
        } else {
            Log.d(TAG, "put: invalid key " + key + ", sec=" + section + ", keyname=" + keyname);
        }
    }

    private boolean getSystemSetting(String key, boolean def) {
        int defInt = def ? 1 : 0;
        String section = key.substring(0,3);
        String keyname = key.substring(4);
        switch(section) {
            case "sys":
                return Settings.System.getInt(mContext.getContentResolver(), keyname, defInt) == 1 ? true : false;
            case "sec":
                return Settings.Secure.getInt(mContext.getContentResolver(), keyname, defInt) == 1 ? true : false;
            case "glb":
                return Settings.Global.getInt(mContext.getContentResolver(), keyname, defInt) == 1 ? true : false;
        }
        Log.d(TAG, "get: invalid key " + key + ", sec=" + section + ", keyname=" + keyname);
        return false;
    }
  
    private void setEnable(String key, boolean value) {
        if( key.startsWith("ss.") ) {
            setSystemSetting(key.substring(3),value);
        } else {
            String value_str = value ? "1":"0";
            if( key.equals("persist.qfp") ) {
                value_str = value ? "true":"false";
            }
    	    SystemProperties.set(key, value_str);
        }
    	Log.d(TAG, key + " setting changed");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
        String strvalue;
        if (DEBUG) Log.d(TAG, "Preference changed.");

    	value = (Boolean) newValue;
    	((SwitchPreference)preference).setChecked(value);
    	setEnable(key,value);
    	return true;
    }
}



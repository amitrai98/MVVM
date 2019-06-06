package com.app.runtimepermission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static android.R.attr.permission;

public class PermissionHandler {

    public static PermissionListener listener = null;
    private final String TAG = getClass().getSimpleName();

    private static String NOT_DEFINED_IN_MANIFEST = "requested runtime permission is not defined in your manifest";
    private static String NOT_REQUIRED = "requested permission do not required runtime permission";
    private static String[] foo_array = {
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.GET_ACCOUNTS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.RECORD_AUDIO",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CALL_PHONE",
            "android.permission.READ_CALL_LOG",
            "android.permission.WRITE_CALL_LOG",
            "android.permission.ADD_VOICEMAIL",
            "android.permission.USE_SIP",
            "android.permission.PROCESS_OUTGOING_CALLS",
            "android.permission.BODY_SENSORS",
            "android.permission.SEND_SMS",
            "android.permission.RECEIVE_SMS",
            "android.permission.READ_SMS",
            "android.permission.RECEIVE_WAP_PUSH",
            "android.permission.RECEIVE_MMS",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    public static String READ_CALENDAR = "android.permission.READ_CALENDAR";
    public static String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    public static String CAMERA = "android.permission.CAMERA";
    public static String READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    public static String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    public static String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    public static String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static String CALL_PHONE = "android.permission.CALL_PHONE";
    public static String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    public static String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    public static String ADD_VOICEMAIL = "android.permission.ADD_VOICEMAIL";
    public static String USE_SIP = "android.permission.USE_SIP";
    public static String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    public static String BODY_SENSORS = "android.permission.BODY_SENSORS";
    public static String SEND_SMS = "android.permission.SEND_SMS";
    public static String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    public static String READ_SMS = "android.permission.READ_SMS";
    public static String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    public static String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
    public static String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";


    public static void getPermission(Activity context, String permission, PermissionListener listener) {
        PermissionHandler.listener = listener;
        if (getListOfPermissions(context, permission)) {
            if (isCriticalPermission( permission)) {
                Intent i = new Intent(context, PermissionDetailActivity.class);
                PermissionDetailActivity.listener = listener;
                i.putExtra(PermissionDetailActivity.PARM_PERMISSION_REQUESTED, permission);
                context.startActivity(i);
            } else
                listener.onGranted(NOT_REQUIRED);
        } else
            listener.onError(NOT_DEFINED_IN_MANIFEST);


    }

    public static void getAllPermissions(Activity activity, PermissionListener listener) {
        getListOfPermissions(activity, listener);
    }


    /**
     * checks is permission is declared in manifest file
     *
     * @param context        of the application
     * @param permissionName name of the permission requested
     * @return boolean value
     */
    public static boolean getListOfPermissions(final Context context, String permissionName) {
        boolean hasPermission = false;

        try {
            final AssetManager _am = context.createPackageContext(context.getPackageName(), 0).getAssets();
            final XmlResourceParser _xmlParser = _am.openXmlResourceParser(0, "AndroidManifest.xml");
            int _eventType = _xmlParser.getEventType();
            while (_eventType != XmlPullParser.END_DOCUMENT) {
                if ((_eventType == XmlPullParser.START_TAG) && "uses-permission".equals(_xmlParser.getName())) {
                    for (byte i = 0; i < _xmlParser.getAttributeCount(); i++) {
//                        if (_xmlParser.getAttributeName(i).equals("name"))
//                        {

                        if (isCriticalPermission( _xmlParser.getAttributeName(i))) {
                            Intent intent = new Intent(context, PermissionDetailActivity.class);
                            intent.putExtra(PermissionDetailActivity.PARM_PERMISSION_REQUESTED, permission);
                            context.startActivity(intent);
                        } else
                            listener.onGranted(NOT_REQUIRED);
//                            _permissions += _xmlParser.getAttributeValue(i) + "\n";


                        if (_xmlParser.getAttributeValue(i).equalsIgnoreCase(permissionName)) {

                            return true;
                        }
                        Log.e("permissions", "" + _xmlParser.getAttributeValue(i));
//                        }
                    }
                }
                _eventType = _xmlParser.nextToken();
            }
            _xmlParser.close(); // Pervents memory leak.
        } catch (final XmlPullParserException exception) {
            exception.printStackTrace();
        } catch (final PackageManager.NameNotFoundException exception) {
            exception.printStackTrace();
        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        return hasPermission;
    }


    /**
     * checks is permission is declared in manifest file
     *
     * @param context of the application
     * @return boolean value
     */
    public static boolean getListOfPermissions(final Context context, PermissionListener listener) {
        boolean hasPermission = false;

        try {
            final AssetManager _am = context.createPackageContext(context.getPackageName(), 0).getAssets();
            final XmlResourceParser _xmlParser = _am.openXmlResourceParser(0, "AndroidManifest.xml");
            int _eventType = _xmlParser.getEventType();
            while (_eventType != XmlPullParser.END_DOCUMENT) {
                if ((_eventType == XmlPullParser.START_TAG) && "uses-permission".equals(_xmlParser.getName())) {
                    for (byte i = 0; i < _xmlParser.getAttributeCount(); i++) {

                        if (isCriticalPermission( _xmlParser.getAttributeValue(i))) {
                            PermissionDetailActivity.listener = listener;
                            Intent intent = new Intent(context, PermissionDetailActivity.class);
                            intent.putExtra(PermissionDetailActivity.PARM_PERMISSION_REQUESTED,
                                    _xmlParser.getAttributeValue(i));
                            context.startActivity(intent);
                        } else
                            listener.onGranted(NOT_REQUIRED);
                    }
                }
                _eventType = _xmlParser.nextToken();
            }
            _xmlParser.close(); // Pervents memory leak.
        } catch (final XmlPullParserException exception) {
            exception.printStackTrace();
        } catch (final PackageManager.NameNotFoundException exception) {
            exception.printStackTrace();
        } catch (final IOException exception) {
            exception.printStackTrace();
        }

        return hasPermission;
    }


    /**
     * checks if permission is critical or not
     *
     * @param permission to be checked
     * @return boolean
     */
    public static boolean isCriticalPermission(String permission) {
        for (String perm : foo_array) {
            if (perm.equalsIgnoreCase(permission)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGranted(Activity activity, String permission) {
        PackageManager pm = activity.getPackageManager();
        int hasPerm = pm.checkPermission(
                permission,
                activity.getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED)
            return false;
        else
            return true;


    }


}

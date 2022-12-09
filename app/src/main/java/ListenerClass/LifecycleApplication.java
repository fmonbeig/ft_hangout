package ListenerClass;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;

import HelperClass.PreferenceHelper;

public class LifecycleApplication extends Application
        implements DefaultLifecycleObserver {
    PreferenceHelper preferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        preferenceHelper = new PreferenceHelper();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_CREATE");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_START");
        preferenceHelper.dateDisplay(this);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_RESUME");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_PAUSE");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_STOP");
        preferenceHelper.dateSave(this);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(getClass().getSimpleName(), "ON_DESTROY");
    }
}
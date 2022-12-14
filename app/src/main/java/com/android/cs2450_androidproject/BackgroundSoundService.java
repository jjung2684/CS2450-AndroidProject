package com.android.cs2450_androidproject;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class BackgroundSoundService extends Service implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Helper class for binding service to components
     */
    public class LocalBinder extends Binder {
        BackgroundSoundService getService() {
            return BackgroundSoundService.this;
        }
    }

    /**
     * @param intent  - intent passed from GameActivity (music service)
     * @param flags   - (Unused)
     * @param startId - (Unused)
     * @return START_STICKY - tells the OS to recreate the service after it has enough memory
     */
    public int onStartCommand(Intent intent, int flags, int startId) {

        /**
         * Initialize a MediaPlayer object and pass the Raw (Jazz_Music) as resource
         */
        mediaPlayer = new MediaPlayer();
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.jazz_music);
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        Toast.makeText(getApplicationContext(), "Playing Background Music", Toast.LENGTH_SHORT).show();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();

        // When MediaPlayer is "finished" release object
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopSelf();     // Stop service when destroyed
        mediaPlayer.stop();
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

}
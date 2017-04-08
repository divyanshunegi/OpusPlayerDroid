package com.divyanshu.opusplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.divyanshu.opusplayer.opus.OpusLocalService;

import top.oply.opuslib.OpusEvent;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getName();
    private OpusReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OpusService.toggle(this, Environment.getExternalStorageDirectory().getPath()+"/Opus/working_opus_file.opus");
        OpusLocalService.decode(this,Environment.getExternalStorageDirectory().getPath()+"/Opus/sample_opus_file.opus",Environment.getExternalStorageDirectory().getPath()+"/Opus/wav_file.wav",null);
//
//        OpusTool oTool = new OpusTool();
//        oTool.decode("working_opus_file.opus.opus","my_wav",null);


    }


    @Override
    protected void onResume() {
        //register a broadcast receiver
        mReceiver = new OpusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(OpusEvent.ACTION_OPUS_UI_RECEIVER);
        registerReceiver(mReceiver, filter);
//        OpusService.toggle(this, Environment.getExternalStorageDirectory().getPath()+"/Opus/working_opus_file.opus");
        super.onResume();
    }

    @Override
    protected void onPause() {
//        OpusService.toggle(this, Environment.getExternalStorageDirectory().getPath()+"/Opus/working_opus_file.opus");
        unregisterReceiver(mReceiver);
        super.onPause();
    }

    //define a broadcast receiver
    class OpusReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int type = bundle.getInt(OpusEvent.EVENT_TYPE, 0);
            switch (type) {
                case OpusEvent.CONVERT_FINISHED:
                    Log.e(TAG,"CONVERT FINISHED");
                    break;
                case OpusEvent.CONVERT_FAILED:
                    Log.e(TAG,"CONVERT FAILED");
                    break;
                case OpusEvent.CONVERT_STARTED:
                    Log.e(TAG,"CONVERT STARTED");
                    break;
                case OpusEvent.RECORD_FAILED:
                    Log.e(TAG,"RECORD FAILED");
                    break;
                case OpusEvent.RECORD_FINISHED:
                    Log.e(TAG,"RECORD FINISHED");
                    break;
                case OpusEvent.RECORD_STARTED:
                    Log.e(TAG,"RECORD STARTED");
                    break;
                case OpusEvent.RECORD_PROGRESS_UPDATE:
                    Log.e(TAG,"RECORD UPDATED");
                    break;
                case OpusEvent.PLAY_PROGRESS_UPDATE:
                    Log.e(TAG,"PLAY PROGRESS UPDATE");
                    break;
                case OpusEvent.PLAY_GET_AUDIO_TRACK_INFO:
                    Log.e(TAG,"PLAY GET AUDIO TRACK INFO");
                    break;
                case OpusEvent.PLAYING_FAILED:
                    Log.e(TAG,"PLAYING FAILED");
                    break;
                case OpusEvent.PLAYING_FINISHED:
                    Log.e(TAG,"PLAYING FINISHED");
                    break;
                case OpusEvent.PLAYING_PAUSED:
                    Log.e(TAG,"PLAYING PAUSED");
                    break;
                case OpusEvent.PLAYING_STARTED:
                    Log.e(TAG,"PLAYING STARTED");
                    break;
                default:
                    Log.d(TAG, intent.toString() + "Invalid request,discarded");
                    break;
            }
        }
    }
}

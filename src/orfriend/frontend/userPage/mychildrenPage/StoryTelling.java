package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;

public class StoryTelling extends AppCompatActivity {

    /**
     * represents the home button
     */
    public ImageButton but1;

    /**
     * represents the back button
     */
    public ImageButton but2;

    public Button btn;
    /**  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_story_telling);
    }**/
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording ;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_telling);
        session = new SessionManager(StoryTelling.this);
        session.checkLogin();
        buttonStart = (Button) findViewById(R.id.buttonx);
        buttonStop = (Button) findViewById(R.id.button2);
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);
        buttonStopPlayingRecording = (Button)findViewById(R.id.button4);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);

        random = new Random();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()) {

                    AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {

                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);

                    Toast.makeText(StoryTelling.this, "Recording started", Toast.LENGTH_LONG).show();
                }
                else {

                    requestPermission();

                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaRecorder.stop();

                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);

                Toast.makeText(StoryTelling.this, "Recording Completed", Toast.LENGTH_LONG).show();

            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException, SecurityException, IllegalStateException {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();

                Toast.makeText(StoryTelling.this, "Recording Playing", Toast.LENGTH_LONG).show();

            }
        });

        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);

                if(mediaPlayer != null){

                    mediaPlayer.stop();
                    mediaPlayer.release();

                    MediaRecorderReady();

                }

            }
        });
        btn=(Button) findViewById(R.id.send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StoryTelling.this, "Story sent", Toast.LENGTH_LONG).show();
                Intent x = new Intent(StoryTelling.this,KidMenu.class);
                startActivity(x);
            }
        });
        home();
        goBack();

    }

    public void MediaRecorderReady(){

        mediaRecorder=new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(AudioSavePathInDevice);

    }

    public String CreateRandomAudioFileName(int string){

        StringBuilder stringBuilder = new StringBuilder( string );

        int i = 0 ;
        while(i < string ) {

            stringBuilder.append(RandomAudioFileName.charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(StoryTelling.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {

                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {

                        Toast.makeText(StoryTelling.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(StoryTelling.this,"Permission Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    public void home(){
        but1 = (ImageButton) findViewById(R.id.home4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(StoryTelling.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }

    public void goBack(){
        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x = new Intent(StoryTelling.this,KidMenu.class);
                x.putExtra("pic", session.getChildDetails().get(KEY_CHILD_PIC));
                x.putExtra("name",  session.getChildDetails().get(KEY_CHILD_NAME));
                startActivity(x);
            }
        });
    }
}

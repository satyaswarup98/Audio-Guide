package com.example.satya.audioguide;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class PlayActivity extends AppCompatActivity {


    public final static int QRcodeWidth = 500;
    private static final String IMAGE_DIRECTORY = "/Audio Guide";
    private static final String LOG_TAG = "Record_String";
    Bitmap bitmap;
    StorageReference riversRef;
    int a = 0;
    String downloadUUID;
    String QRImgName="";
    String contents;

    private ImageView iv;
    private StorageReference mStorageRef;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private ProgressDialog mProgress;

    public static final int REQUEST_CODE=100;
    public static final int PERMISSION_REQUEST=200;

    //    TextView result;
    View mainView;

    private String m_Text = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

//
//            result = (TextView) mainView.findViewById(R.id.result);
            mProgress = new ProgressDialog(this);
            mStorageRef = FirebaseStorage.getInstance().getReference();

//            MainActivity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    if(tab.getPosition()==0){
//                        Intent intent = new Intent(this,ScanActivity.class);
//                        startActivityForResult(intent,REQUEST_CODE);}
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
////                if(tab.getPosition()==0){
////                    Intent intent = new Intent(this,ScanActivity.class);
////                    startActivityForResult(intent,REQUEST_CODE);}
//                }
//            });

                Intent intent = getIntent();
                final Barcode barcode = intent.getParcelableExtra("barcode");
                contents = barcode.displayValue.toString();
                AlertBoxInput();
    }


    public void AlertBoxInput()
        {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save file name as ");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT );
            input.setFilters(new InputFilter[] { filter });
            builder.setView(input);

            // Set up the buttons

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    downloadAudio();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

        }
        private String blockCharacterSet = "~#^|$%&*!.";

        private InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                if (source != null && blockCharacterSet.contains(("" + source))) {
                    return "";
                }
                return null;
            }
        };

        public void downloadAudio() {

            mProgress.setMessage("Downloading Audio...");
            mProgress.show();

            File rootPath = new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY+"/Audio Downloads");
            if (!rootPath.exists()) {
                rootPath.mkdirs();
            }
            if(m_Text=="")
            {
                m_Text=contents;
            }
            final File localFile = new File(rootPath, m_Text+".3gp");
            riversRef = mStorageRef.child("Audio").child(contents);
            riversRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            mProgress.dismiss();
                            // Successfully downloaded data to local file
                            // ...
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    mProgress.dismiss();
                    mProgress.setMessage("Download Failed...");
                    mProgress.show();
                    // Handle failed download
                    // ...
                }
            });
        }
}

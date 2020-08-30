package com.example.satya.audioguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DownloadFragment extends Fragment {

//
//    public final static int QRcodeWidth = 500;
//    private static final String IMAGE_DIRECTORY = "/Audio Guide";
//    private static final String LOG_TAG = "Record_String";
//    Bitmap bitmap;
//    StorageReference riversRef;
//    int a = 0;
//    String downloadUUID;
//    String QRImgName="";
//    String contents;
//
//    private ImageView iv;
//    private StorageReference mStorageRef;
//    private MediaRecorder mRecorder;
//    private String mFileName = null;
//    private ProgressDialog mProgress;
//
//    public static final int REQUEST_CODE=100;
//    public static final int PERMISSION_REQUEST=200;
//
//    //    TextView result;
//    View mainView;
//
//    private String m_Text = "";

    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            View mainView = inflater.inflate(R.layout.activity_play_audio, container, false);


////        result = (TextView) mainView.findViewById(R.id.result);
//            mProgress = new ProgressDialog(getActivity());
//            mStorageRef = FirebaseStorage.getInstance().getReference();
//
//            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},PERMISSION_REQUEST);
//            }
//
//            MainActivity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    if(tab.getPosition()==0){
//                        Intent intent = new Intent(getActivity(),ScanActivity.class);
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
////                    Intent intent = new Intent(getActivity(),ScanActivity.class);
////                    startActivityForResult(intent,REQUEST_CODE);}
//                }
//            });

            return mainView;
        }
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//            if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK ){
//                if(data != null){
//                    final Barcode barcode = data.getParcelableExtra("barcode");
////                result.post(new Runnable() {
////                    @Override
////                    public void run() {
//                    contents = barcode.displayValue.toString();
////                        result.setText(contents);
//                    AlertBoxInput();
//
////                    }
////                });
//
//                }
//            }
//        }
//
//        public void AlertBoxInput()
//        {
//
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle("Save file name as ");
//
//// Set up the input
//            final EditText input = new EditText(getActivity());
//// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//            input.setInputType(InputType.TYPE_CLASS_TEXT );
//            input.setFilters(new InputFilter[] { filter });
//            builder.setView(input);
//
//// Set up the buttons
//
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    m_Text = input.getText().toString();
//                    downloadAudio();
//                }
//            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//
//            builder.show();
//
//        }
//        private String blockCharacterSet = "~#^|$%&*!.";
//
//        private InputFilter filter = new InputFilter() {
//
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
//                if (source != null && blockCharacterSet.contains(("" + source))) {
//                    return "";
//                }
//                return null;
//            }
//        };
//
//        public void downloadAudio() {
//
//            mProgress.setMessage("Downloading Audio...");
//            mProgress.show();
//
//            File rootPath = new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY+"/Audio Downloads");
//            if (!rootPath.exists()) {
//                rootPath.mkdirs();
//            }
//            if(m_Text=="")
//            {
//                m_Text=contents;
//            }
//            final File localFile = new File(rootPath, m_Text+".3gp");
//            riversRef = mStorageRef.child("Audio").child(contents);
//            riversRef.getFile(localFile)
//                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            mProgress.dismiss();
//                            // Successfully downloaded data to local file
//                            // ...
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    mProgress.dismiss();
//                    mProgress.setMessage("Download Failed...");
//                    mProgress.show();
//                    // Handle failed download
//                    // ...
//                }
//            });
//        }
    }

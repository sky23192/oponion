package com.app.oponion;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import extra.CircleTransform;
import firebasemodels.Feed;
import gun0912.tedbottompicker.TedBottomPicker;
import imgur.DocumentHelper;
import imgur.ImageResponse;
import imgur.Upload;
import imgur.UploadService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityNewShout extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = Oponion.APP_TAG + ActivityNewShout.class.getSimpleName();
    private static final int REQUEST_CAMERA = 1212;
    private static final int SELECT_FILE = 1234;

    private String userChosenTask;

    private ImageView ivSelectedImage, ivLocation;

    private static final int REQUEST_LOCATION = 6;

    private AddressResultReceiver mResultReceiver;

    TextView tvLocation;

    EditText etBody;

    TedBottomPicker tedBottomPicker;

    LinearLayout llCoverPhoto;

    ImageView ivCoverPhoto;

    boolean isAddingCoverPhoto = false;

    LocationManager mLocationManager;

    private static long DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

    private String linkUrl = "";

    private Location positionLocation;

    private EditText etTitle;

    private ProgressBar pbProcessing;

    boolean gps_enabled = false;
    boolean network_enabled = false;

    private File chosenFile;

    private Upload upload;

    private ImageResponse imageResponse;

    private ImageView ivUserProfilePic;

    private final android.location.LocationListener mLocationListener = new android.location.LocationListener()
    {
        @Override
        public void onLocationChanged(Location location)
        {

            positionLocation = location;

            Intent intent = new Intent(ActivityNewShout.this, GeocodeAddressIntentService.class);
            intent.putExtra(Constants.RECEIVER, mResultReceiver);
            intent.putExtra(Constants.FETCH_TYPE_EXTRA, Constants.USE_ADDRESS_LOCATION);

            intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA,
                    location.getLatitude());
            intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA,
                    location.getLongitude());

            startService(intent);

            if (ActivityCompat.checkSelfPermission(ActivityNewShout.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityNewShout.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLocationManager.removeUpdates(mLocationListener);

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle)
        {

        }

        @Override
        public void onProviderEnabled(String s)
        {

        }

        @Override
        public void onProviderDisabled(String s)
        {

        }
    };

    final PermissionListener permissionListener = new PermissionListener()
    {
        @Override
        public void onPermissionGranted()
        {

            tedBottomPicker = new TedBottomPicker.Builder(ActivityNewShout.this)
                    .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener()
                    {
                        @Override
                        public void onImageSelected(Uri uri)
                        {
                            if (isAddingCoverPhoto)
                            {
                                String filePath = DocumentHelper.getPath(ActivityNewShout.this, uri);
                                chosenFile = new File(filePath);
                                Picasso.with(ActivityNewShout.this).load(uri).into(ivCoverPhoto);
                                llCoverPhoto.setVisibility(View.GONE);
                                isAddingCoverPhoto = false;
                            }
                        }
                    })
                    .create();

            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(ActivityNewShout.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(ActivityNewShout.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {

                return;
            }

            LocationManager lm = (LocationManager) ActivityNewShout.this
                    .getSystemService(Context.LOCATION_SERVICE);

            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            Location net_loc = null, gps_loc = null, finalLoc = null;

            if (gps_enabled)
            {
                gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (network_enabled)
            {
                net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (gps_loc != null && net_loc != null)
            {

                if (gps_loc.getAccuracy() >= net_loc.getAccuracy())
                {
                    finalLoc = gps_loc;
                } else
                {
                    finalLoc = net_loc;
                }

                // I used this just to get an idea (if both avail, its upto you which you want to take as I taken location with more accuracy)

            } else
            {

                if (gps_loc != null)
                {
                    finalLoc = net_loc;
                } else if (net_loc != null)
                {
                    finalLoc = gps_loc;
                }
            }

            // Initialize the location fields
            if (finalLoc != null)
            {
                positionLocation = finalLoc;
                long daysOld = (System.currentTimeMillis() - finalLoc.getTime()) / DAY_IN_MILLISECONDS;
                if (daysOld <= 1)
                {
                    Toast.makeText(ActivityNewShout.this, "" + daysOld, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityNewShout.this, GeocodeAddressIntentService.class);
                    intent.putExtra(Constants.RECEIVER, mResultReceiver);
                    intent.putExtra(Constants.FETCH_TYPE_EXTRA, Constants.USE_ADDRESS_LOCATION);

                    intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA,
                            finalLoc.getLatitude());
                    intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA,
                            finalLoc.getLongitude());

                    startService(intent);
                } else
                {
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
                }
            } else
            {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
            }

        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions)
        {

        }
    };


    public void uploadImage()
    {
    /*
      Create the @Upload object
     */
        if (chosenFile == null)
        {
            return;
        }
        createUpload(chosenFile);

    /*
      Start upload
     */
        new UploadService(this).Execute(upload, new UiCallback());
    }

    private void createUpload(File image)
    {
        upload = new Upload();

        upload.image = image;
        upload.title = "oponion";
        upload.description = "oponion this is a test image";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_new_shout);

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        llCoverPhoto = (LinearLayout) findViewById(R.id.ll_coverPhoto);

        ivUserProfilePic = (ImageView) findViewById(R.id.iv_userProfilePic);

        Picasso.with(this).load("https://lh3.googleusercontent.com/-8twv_aWLqtY/AAAAAAAAAAI/AAAAAAAAAQ8/K1r--rxdH3w/s120-c/photo.jpg")
                .transform(new CircleTransform()).into(ivUserProfilePic);

        findViewById(R.id.fl_coverPhoto).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                isAddingCoverPhoto = true;
                tedBottomPicker.show(getSupportFragmentManager());
            }
        });

        ivCoverPhoto = (ImageView) findViewById(R.id.iv_coverPhoto);

        findViewById(R.id.iv_gallery).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tedBottomPicker.show(getSupportFragmentManager());
                //selectImage();
            }
        });

        final Button post = (Button) findViewById(R.id.btn_postFeed);

        post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                post.setVisibility(View.GONE);
                pbProcessing.setVisibility(View.VISIBLE);

                String location = "0,0";

                if (positionLocation != null)
                {
                    location = positionLocation.getLatitude() + "," + positionLocation.getLongitude();
                }
                final String title = etTitle.getText().toString();
                final String body = etBody.getText().toString();

                if (chosenFile != null)
                {
                    uploadImage();
                } else
                {
                    Feed.postFeed("9409210488", location, imageResponse.data.link, title, body, new Feed.PostFeedListener()
                    {
                        @Override
                        public void onPostSuccess()
                        {
                            post.setVisibility(View.VISIBLE);
                            pbProcessing.setVisibility(View.GONE);
                            ActivityNewShout.this.finish();
                        }

                        @Override
                        public void onPostFailed()
                        {
                            post.setVisibility(View.VISIBLE);
                            pbProcessing.setVisibility(View.GONE);
                        }
                    });
                }

            }
        });


        final Html.ImageGetter imageGetter = new Html.ImageGetter()
        {
            Drawable d = null;

            @Override
            public Drawable getDrawable(final String source)
            {

                LevelListDrawable d = new LevelListDrawable();
                Drawable empty = getResources().getDrawable(R.drawable.ic_autorenew_black_18dp);
                d.addLevel(0, 0, empty);
                d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

                new LoadImage().execute(source, d);

                return d;
            }

        };


        findViewById(R.id.ic_link).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNewShout.this);
                builder.setTitle("Insert Link");

                // Set up the input
                final EditText input = new EditText(ActivityNewShout.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        linkUrl = input.getText().toString();
                        linkUrl = "<img src='" + linkUrl + "'/>";
                        etBody.setText(Html.fromHtml(etBody.getText() + linkUrl, imageGetter, null));
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        etBody = (EditText) findViewById(R.id.tv_feedBody);

        etTitle = (EditText) findViewById(R.id.et_feedTitle);

        pbProcessing = (ProgressBar) findViewById(R.id.pb_processing);

        //etBody.setText(Html.fromHtml("￼\nhello Sir I am not sure if you have any questions or concerns please visit the lockouts page and the", imageGetter, null));

        ivSelectedImage = (ImageView) findViewById(R.id.iv_selectedImage);

        tvLocation = (TextView) findViewById(R.id.tv_location);

        ivLocation = (ImageView) findViewById(R.id.iv_location);

        ivLocation.setOnClickListener(this);

        mResultReceiver = new AddressResultReceiver(null);

        checkForPermission();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.i(TAG, "onActivityResult: ON ACTIVITY RESULT");

        Log.i(TAG, "onActivityResult: RESULT CODE IS OK!!!");

        Log.i(TAG, "onActivityResult: REQUEST CODE IS: " + resultCode);

        switch (requestCode)
        {
            case SELECT_FILE:
                onSelectFromGalleryResult(data);
                break;

            case REQUEST_CAMERA:
                onCaptureImageResult(data);
                break;

            case REQUEST_LOCATION:
                if (data != null)
                {
                    Intent intent = new Intent(this, GeocodeAddressIntentService.class);
                    intent.putExtra(Constants.RECEIVER, mResultReceiver);
                    intent.putExtra(Constants.FETCH_TYPE_EXTRA, Constants.USE_ADDRESS_LOCATION);

                    intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA, data.getDoubleExtra("lat", 0));
                    intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA, data.getDoubleExtra("lng", 0));
                } else
                {
                    Log.i(TAG, "onActivityResult: DATA is NULL!!!!!");
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);


        }


    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data)
    {
        Bitmap bm = null;
        if (data != null)
        {
            try
            {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        ivSelectedImage.setImageBitmap(bm);
    }


    private void onCaptureImageResult(Intent data)
    {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try
        {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ivSelectedImage.setImageBitmap(thumbnail);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (userChosenTask.equals("Take Photo"))
                    {
                        cameraIntent();
                    } else if (userChosenTask.equals("Choose from Library"))
                    {
                        galleryIntent();
                    }
                } else
                {
                    //code for deny
                }
                break;
        }
    }


    private void selectImage()
    {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                boolean result = Utility.checkPermission(ActivityNewShout.this);

                if (items[item].equals("Take Photo"))
                {
                    userChosenTask = "Take Photo";
                    if (result)
                    {
                        cameraIntent();
                    }
                } else if (items[item].equals("Choose from Library"))
                {
                    userChosenTask = "Choose from Library";
                    if (result)
                    {
                        galleryIntent();
                    }
                } else if (items[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(ActivityNewShout.this, MapsActivity.class);
        Log.i(TAG, "onClick: REQUEST_LOCATION CODE IS: " + REQUEST_LOCATION);
        startActivityForResult(i, REQUEST_LOCATION);
    }

    @SuppressLint("ParcelCreator")
    class AddressResultReceiver extends ResultReceiver
    {
        public AddressResultReceiver(Handler handler)
        {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData)
        {
            if (resultCode == Constants.SUCCESS_RESULT)
            {
                //final Address address = resultData.getParcelable(Constants.RESULT_ADDRESS);
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String address = resultData.getString(Constants.RESULT_DATA_KEY);
                        address = address.substring(0, address.lastIndexOf(" "));
                        tvLocation.setText(address);
                        tvLocation.setVisibility(View.VISIBLE);
                    }
                });
            } else
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                    }
                });
            }
        }
    }


    class LoadImage extends AsyncTask<Object, Void, Bitmap>
    {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params)
        {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try
            {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null)
            {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = etBody.getText();
                etBody.setText(t);
            }
        }
    }


    void checkForPermission()
    {
        new TedPermission(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }


    private class UiCallback implements Callback<ImageResponse>
    {

        @Override
        public void success(ImageResponse imageResponse, Response response)
        {
            Log.i(TAG, imageResponse.toString());
            ActivityNewShout.this.imageResponse = imageResponse;
        }

        @Override
        public void failure(RetrofitError error)
        {
            //Assume we have no connection, since error is null
            if (error == null)
            {
                Toast.makeText(ActivityNewShout.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

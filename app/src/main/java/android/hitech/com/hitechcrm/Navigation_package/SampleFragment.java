package android.hitech.com.hitechcrm.Navigation_package;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hitech.com.hitechcrm.R;
import android.hitech.com.hitechcrm.database.Dbsave;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

import static android.R.attr.orientation;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class SampleFragment extends Fragment {
    ImageView ivv;
    private HorizontalCalendar horizontalCalendar;
    int j;
    Uri selectedImage;
    Bitmap bitmap;
    Dbsave dbSave;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    String url1="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
        //initialize all the resources
        ivv = (ImageView) rootView.findViewById(R.id.iv1);
        tv1 = (TextView) rootView.findViewById(R.id.leadstext);
        tv2 = (TextView) rootView.findViewById(R.id.contacttext);
        tv3 = (TextView) rootView.findViewById(R.id.accounttext);
        tv4 = (TextView) rootView.findViewById(R.id.companytext);
        tv5 = (TextView) rootView.findViewById(R.id.callstext);
        tv6 = (TextView) rootView.findViewById(R.id.canditext);
        tv7 = (TextView) rootView.findViewById(R.id.mailstext);
        tv1.setText("30\n" + "Leads");
        //Profile image click listener
        ivv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Alert dialog box to choose image
                final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                alertDialog.show();
                alertDialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                Window win = alertDialog.getWindow();
                win.setContentView(R.layout.customdialogg);
                ImageView ivv1 = (ImageView) win.findViewById(R.id.imageView2);
                ivv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        opencam();
                        alertDialog.dismiss();
                    }
                });

                ImageView ivv2 = (ImageView) win.findViewById(R.id.imageView3);
                ivv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        opengallery();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        dbSave = new Dbsave(getContext());
        Drawable drawable = new BitmapDrawable(getResources(), dbSave.getData());
        System.out.println("drawa" + drawable);
        ivv.setImageBitmap(dbSave.getData());
        System.out.println("drawabm" + dbSave.getData());

        /** end after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /** start before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);


        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .dayNameFormat("EEE")
                .dayNumberFormat("dd")
                .monthFormat("MMM")
                .textSize(14f, 24f, 14f)
                .showDayName(true)
                .showMonthName(true)
                .textColor(Color.LTGRAY, Color.WHITE)
                .selectedDateBackground(Color.TRANSPARENT)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                Toast.makeText(getContext(), DateFormat.getDateInstance().format(date) + " is selected!", Toast.LENGTH_SHORT).show();
            }

        });
        String url="http://192.168.12.48/api/Task/AllModuleCount?CreateDate=2017-07-28 12:52:42&UserId=c3b62f5d-57d1-4975-b6a1-0c9d136e41c3";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        MyNetwork.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);

        return rootView;
    }

//Open camera
    public void opencam() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
    }
//Open gallery
    public void opengallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 2);
    }
//The choosen image will be returned
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


        switch (requestCode) {
            case 0:
                selectedImage = imageReturnedIntent.getData();
                System.out.println("photu" + imageReturnedIntent.getData());
                ivv.setImageURI(selectedImage);
                if (resultCode == RESULT_OK && imageReturnedIntent.getData() != null)
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                Dbsave dbSave = new Dbsave(getContext());
                dbSave.insertData(j, bitmap);
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    ivv.setImageURI(selectedImage);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dbSave = new Dbsave(getContext());
                    dbSave.insertData(j, bitmap);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, "", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
    }
}

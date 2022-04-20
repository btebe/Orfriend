package com.orfriend.orfriend.frontend.userPage.adoptPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orfriend.orfriend.ListCategory;
import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;

import java.io.IOException;
import java.util.List;

/**
 * csis 330 project
 * project name: OrFriend
 * ChooseChild.java
 * purpose: to choose options to do with the child
 * @author basmatebe
 * @version 1.0 27/11/2016
 */

public class ChooseChild extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    /**
     * represents the home button
     */
    public ImageButton chooceChildBackBtn;

    public Spinner dropDownSpinner;


    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(ChooseChild.this);
        session.checkLogin();

        if (googleServicesAvailable()){
            Toast.makeText(this,"perfect", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_choose_child);

            initMap();
        }else{
            // no google map layout
        }
        goBack();
        try {
            dropDownMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        chooceChildBackBtn = (ImageButton) findViewById(R.id.chooceChildBackBtn);
        chooceChildBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ChooseChild.this,AdoptPage.class);
                startActivity(x);
            }
        });
    }
    public void dropDownMenu() throws IOException {
        dropDownSpinner = (Spinner) findViewById(R.id.dropDownSpinner);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(ChooseChild.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownSpinner.setAdapter(myadapter);
        dropDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    try {
                        geoCod("hawally",8);
                        geoCod("mangaf",8);
                        geoCod("Kuwait",8);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(i == 2){
                    try {
                        geoCod("Eygpt",5);
                        geoCod("Cairo",5);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }



    public boolean googleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }else{
            Toast.makeText(this,"cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void initMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocation(29.2985, 42.5510);
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll =  new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll =  new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }

    public void geoCod(String country, int zoom) throws IOException {

        Geocoder gc = new Geocoder(this);
        List<android.location.Address> list = gc.getFromLocationName(country, 1);
        android.location.Address address = list.get(0);
        String locality = address.getLocality();
        //Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat, lng, zoom);
        //Toast.makeText(this, country , Toast.LENGTH_LONG).show();
        setMarker(country, lat, lng);

    }

    Context context;
    ListView lsv;
    /*public void listview(){
        lsv = new ListView(this);
        String [] items = {"basma","fufu","tuts","tomata"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.listv, items);
        lsv.setAdapter(adapter);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.listv);
                Toast.makeText(ChooseChild.this, txt.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }*/

    public void showDiologV(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setPositiveButton("ok", null);
        builder.setView(lsv);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void dialogview(){
        context = this;
        //lsv = new ListView();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("select child");
        alertDialog.setMessage("soon to be list");
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //cancels
        alertDialog.setNegativeButton("cancel", null);
        AlertDialog ald = alertDialog.create();
        ald.show();

    }

    public void setMarker(String country, double lat, double lng){

        final MarkerOptions options = new MarkerOptions().title(country)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .position(new LatLng(lat,lng)).snippet("I am here!");
        mGoogleMap.addMarker(options);
        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getTitle().equals("hawally")){
                    //listview();
                    //showDiologV();
                    Intent x = new Intent(ChooseChild.this,ListCategory.class);
                    x.putExtra("O_ID","1");
                    x.putExtra("location", "Hawally");
                    startActivity(x);

                }
                if(marker.getTitle().equals("Cairo")){
                    Intent x = new Intent(ChooseChild.this,ListCategory.class);
                    x.putExtra("O_ID","3");
                    x.putExtra("location", "Cairo");
                    startActivity(x);

                }
                if(marker.getTitle().equals("mangaf")){
                    Intent x = new Intent(ChooseChild.this,ListCategory.class);
                    x.putExtra("O_ID","2");
                    x.putExtra("location", "Mangaf");
                    startActivity(x);

                }
            }
        });



    }

    }


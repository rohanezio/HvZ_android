package edu.acase.hvz.hvz_app;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Z extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    class mapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View view;

        public mapInfoWindowAdapter() {
            view = getLayoutInflater().inflate(R.layout.custom_marker_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            TextView title = ((TextView) view.findViewById(R.id.title));
            title.setText(marker.getTitle());

            TextView snippet = ((TextView) view.findViewById(R.id.snippet));
            snippet.setText(marker.getSnippet());

            return view;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    private GoogleMap gmap;

    @Override
    public void onMapReady(GoogleMap Map) {
        gmap = Map;
        gmap.setIndoorEnabled(false);
        gmap.setTrafficEnabled(false);
        gmap.getUiSettings().setMapToolbarEnabled(false);

        LatLng nrv = new LatLng(41.513883, -81.605746);

        gmap.addMarker(new MarkerOptions().position(nrv).title("North Res Village"));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(nrv));
        gmap.setMinZoomPreference(15);
        gmap.setOnMapLongClickListener(this);
        gmap.setInfoWindowAdapter(new mapInfoWindowAdapter());
    }

    @Override
    public void onMapLongClick(LatLng point) {
        Marker newMarker = gmap.addMarker(new MarkerOptions()
                .position(point)
                .snippet(point.toString()));
        newMarker.setTitle(newMarker.getId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button helpButton = (Button) findViewById(R.id.helpButton),
                infoButton = (Button) findViewById(R.id.infoButton),
                reportButton = (Button) findViewById(R.id.reportButton),
                caughtButton = (Button) findViewById(R.id.caughtButton);

        final Context context = this;
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonDialogs.getHelpButtonDialog(context, v);
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonDialogs.getInfoButtonDialog(context, v);
            }
        });
        reportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = CommonDialogs.getAlertBuilder(context);
                builder.setTitle("Sighted Human")
                        .setMessage("Code for fropped pin and form goes here")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();

            }
        });
        caughtButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),stunned.class);
                startActivity(i);
            }
        });
    }

    private AlertDialog.Builder getModalBuilder(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        else
            return new AlertDialog.Builder(context);
    }
}
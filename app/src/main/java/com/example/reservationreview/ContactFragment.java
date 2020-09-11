package com.example.reservationreview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactFragment extends Fragment {
    MapView mMapView;
    private GoogleMap map;
    private EditText editTextSubject;
    private EditText editTextCompose;
    private Button sendEmailButton;
    private static String emailTo = "marija.velinova8@gmail.com";

    public static Fragment getInstance(Context context) {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                LatLng coordinates = new LatLng(41.748415, 22.200997);
                map = mMap;
                MarkerOptions address = new MarkerOptions();
                address.position(coordinates);
                address.title("Radio Kanal 77");
                address.snippet("Opened now");
                Marker marker = map.addMarker(address);
                marker.showInfoWindow();
                CameraUpdateFactory.newLatLngZoom(coordinates, 10);
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title("You set this marker at " + latLng.latitude + ":" + latLng.longitude);
                        map.clear();map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        map.addMarker(markerOptions);

                    }
                });
            }
        });

        //send email
        editTextSubject = rootView.findViewById(R.id.subject);
        editTextCompose = rootView.findViewById(R.id.composeEmail);
        sendEmailButton = rootView.findViewById(R.id.send_email);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        return rootView;
    }

    private void sendEmail(){
        String[] recipientList = new String[1];
        recipientList[0] = emailTo;
        String subject = editTextSubject.getText().toString();
        String message = editTextCompose.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipientList);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(intent);
        editTextCompose.setText("");
        editTextSubject.setText("");

    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

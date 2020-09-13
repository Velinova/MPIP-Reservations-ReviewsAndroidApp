package com.example.reservationreview;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.reservationreview.domain.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReservationFragment extends Fragment {
    private EditText reservationName;
    private EditText numberGuests;
    private DatePicker reservationDate;
    private TimePicker reservationTime;
    private Button make_reservation;
    private Map<String, Object> reservations = new HashMap<String, Object>();

    DatabaseReference reference;

    public static Fragment getInstance(Context context) {
        ReservationFragment fragment = new ReservationFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_reservation, container, false);

        make_reservation = rootView.findViewById(R.id.make_reservation);
        make_reservation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference();
                reservationName = rootView.findViewById(R.id.name_reservation);
                numberGuests = rootView.findViewById(R.id.persons_reservation);
                reservationDate = rootView.findViewById(R.id.reservation_date_picker);
                reservationTime = rootView.findViewById(R.id.reservation_time_picker);
                String name = reservationName.getText().toString();
                String guests = numberGuests.getText().toString();
                Date date = new Date(reservationDate.getYear(), reservationDate.getMonth(), reservationDate.getDayOfMonth(), reservationTime.getHour(), reservationTime.getMinute());
                Reservation newReservation = new Reservation(name, Integer.parseInt(guests) , date.toString());
                reservations.put(UUID.randomUUID().toString(), newReservation);
                reference.child("/reservations").updateChildren(reservations);
                reservationName.setText("");
                numberGuests.setText("");
                //Show notification in status bar
                String notificationMessage = "You just made a reservation!";
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder( getActivity())
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Reservations")
                        .setContentText(notificationMessage)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("notify_001",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel); }
                notificationManager.notify(0, builder.build());

            }
        });

        return rootView;
    }
}

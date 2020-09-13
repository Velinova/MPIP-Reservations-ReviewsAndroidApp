package com.example.reservationreview;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.reservationreview.domain.MenuItem;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservationreview.helpers.MenuDatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
//        RecyclerView menu_recycler_view = rootView.findViewById(R.id.menu_recycler_view);
//        menu_recycler_view.setLayoutManager(new LinearLayoutManager((rootView.getContext())));
//
//        MenuDatabaseHelper helper = new MenuDatabaseHelper();
//        List<MenuItem> menu = helper.getRestaurantMenu();

        return  rootView;
    }
}

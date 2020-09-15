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
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    MenuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        reference = FirebaseDatabase.getInstance().getReference("/foods");
        recyclerView = rootView.findViewById(R.id.menu_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        FirebaseRecyclerOptions<MenuItem> menuItems
                = new FirebaseRecyclerOptions.Builder<MenuItem>()
                .setQuery(reference, MenuItem.class)
                .build();
        adapter = new MenuAdapter(menuItems);
        recyclerView.setAdapter(adapter);
        return  rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter != null) {
            adapter.stopListening();
        }
    }
}

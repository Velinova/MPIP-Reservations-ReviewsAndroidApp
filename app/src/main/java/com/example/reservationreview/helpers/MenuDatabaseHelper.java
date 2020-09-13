package com.example.reservationreview.helpers;

import androidx.annotation.NonNull;
import androidx.arch.core.internal.FastSafeIterableMap;

import com.example.reservationreview.domain.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuDatabaseHelper {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<MenuItem> menu = new ArrayList<MenuItem>();

    public interface DataStatus{
        void DataIsLoaded(List<MenuItem> items, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public MenuDatabaseHelper(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("menu");
    }

    public void getRestaurantMenu(final DataStatus dataStatus){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menu.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot node: snapshot.getChildren()){
                    keys.add(node.getKey());
                    MenuItem item = node.getValue(MenuItem.class);
                    menu.add(item);
                }
                dataStatus.DataIsLoaded(menu, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

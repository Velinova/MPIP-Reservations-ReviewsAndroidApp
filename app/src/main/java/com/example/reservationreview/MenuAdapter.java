package com.example.reservationreview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservationreview.domain.MenuItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MenuAdapter extends FirebaseRecyclerAdapter<MenuItem, MenuAdapter.MenuViewHolder> {
    public MenuAdapter(@NonNull FirebaseRecyclerOptions<MenuItem> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull MenuItem model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_menu_item, parent, false);
        return new MenuAdapter.MenuViewHolder(view);
    }
    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.menu_item_name_holder);
            price = itemView.findViewById(R.id.menu_item_price_holder);
        }
    }


}

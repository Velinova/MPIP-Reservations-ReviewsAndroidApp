//package com.example.reservationreview.adapters;
//
//import android.view.LayoutInflater;
//import com.example.reservationreview.domain.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.reservationreview.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
//
//    private List<MenuItem> menu;
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu_item, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
//        // Get the data model based on position
//        MenuItem item = menu.get(position);
//
//        // Set item views based on your views and data model
//        TextView name = holder.menuItemName;
//        name.setText(item.getName());
//        TextView price = holder.menuItemPrice;
//        price.setText(item.getPrice());
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView menuItemName;
//        public TextView menuItemPrice;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            menuItemName = (TextView) itemView.findViewById(R.id.menu_item_name_holder);
//            menuItemPrice = (TextView) itemView.findViewById(R.id.menu_item_price_holder);
//        }
//    }
//}

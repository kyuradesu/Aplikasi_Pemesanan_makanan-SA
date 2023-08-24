package com.example.aplikasipemesananmakanansa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<MenuItem> menuItems;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public MenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);

        holder.ximgMenu.setImageResource(menuItem.getImageResId());
        holder.xtxtNamaMenu.setText(menuItem.getNamaMenu());
        holder.xtxtDeskripsiMenu.setText(menuItem.getDeskripsiMenu());
        holder.xtxtHargaMenu.setText("Rp " + (int)menuItem.getHargaMenu());


        Button btnPesan = holder.itemView.findViewById(R.id.btnPesan);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PesanActivity.class);

                intent.putExtra("itemName", menuItem.getNamaMenu());
                intent.putExtra("itemDescription", menuItem.getDeskripsiMenu());
                intent.putExtra("itemPrice", menuItem.getHargaMenu());
                intent.putExtra("itemImage", menuItem.getImageResId());

                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = getSelectedPosition();
                int currentPosition = holder.getAdapterPosition();

                if (previousSelectedPosition != currentPosition) {
                    selectedPosition = currentPosition;
                    notifyItemChanged(previousSelectedPosition);
                    notifyItemChanged(selectedPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public int getSelectedPosition() {
        int selectedPosition = 0;
        return selectedPosition;
    }

    // Inner ViewHolder class
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ximgMenu;
        TextView xtxtNamaMenu;
        TextView xtxtDeskripsiMenu;
        TextView xtxtHargaMenu;
        Button xbtnPesan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ximgMenu = itemView.findViewById(R.id.menuItemImage);
            xtxtNamaMenu = itemView.findViewById(R.id.menuItemName);
            xtxtDeskripsiMenu = itemView.findViewById(R.id.menuItemDescription);
            xtxtHargaMenu = itemView.findViewById(R.id.menuItemPrice);
            xbtnPesan = itemView.findViewById(R.id.btnPesan);
        }
    }
}

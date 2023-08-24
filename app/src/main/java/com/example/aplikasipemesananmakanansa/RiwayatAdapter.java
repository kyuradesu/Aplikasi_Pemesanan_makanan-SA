package com.example.aplikasipemesananmakanansa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private List<RiwayatItem> riwayatItems;

    public RiwayatAdapter(List<RiwayatItem> riwayatItems) {
        this.riwayatItems = riwayatItems;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_card, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        RiwayatItem riwayatItem = riwayatItems.get(position);

        holder.textItemPesanan.setText(riwayatItem.getItemPesanan());
        holder.textTotalHarga.setText(riwayatItem.getTotalHarga());
        holder.textTanggal.setText(riwayatItem.getTanggal());
        holder.textMetodePembayaran.setText(riwayatItem.getMetodePembayaran());
    }

    @Override
    public int getItemCount() {
        return riwayatItems.size();
    }

    public class RiwayatViewHolder extends RecyclerView.ViewHolder {

        private TextView textItemPesanan;
        private TextView textTotalHarga;
        private TextView textTanggal;
        private TextView textMetodePembayaran;
        private CardView cardView;

        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            textItemPesanan = itemView.findViewById(R.id.textItemPesanan);
            textTotalHarga = itemView.findViewById(R.id.textTotalHarga);
            textTanggal = itemView.findViewById(R.id.textTanggal);
            textMetodePembayaran = itemView.findViewById(R.id.textMetodePembayaran);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

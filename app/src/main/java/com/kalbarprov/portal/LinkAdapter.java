package com.kalbarprov.portal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.HolderLink> {

    private Context context;
    private List<RedirectModel> listLink;

    public LinkAdapter(Context context, List<RedirectModel> listLink) {
        this.context = context;
        this.listLink = listLink;
    }

    @NonNull
    @Override
    public HolderLink onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_link, parent, false);
        return new HolderLink(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderLink holder, int position) {
        RedirectModel redirectModel = listLink.get(position);
        holder.tvJudul.setText(breakUp(redirectModel.getName()));
        Glide.with(context).load(redirectModel.getIcon()).into(holder.ivImageLink);
        holder.cvLink.setCardBackgroundColor(Color.parseColor(redirectModel.getColor()));
        holder.cvLink.setOnClickListener(view -> {
            Uri uri = Uri.parse(redirectModel.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listLink.size();
    }

    public class HolderLink extends RecyclerView.ViewHolder {
        TextView tvJudul;
        ImageView ivImageLink;
        CardView cvLink;

        public HolderLink(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.linkJudul);
            ivImageLink = itemView.findViewById(R.id.linkImage);
            cvLink = itemView.findViewById(R.id.linkCard);
        }
    }

    private String breakUp(String string){
        String result ="";
        for(String r:string.split(" ")){
            result += r;
            if(r.length() > 5)result += "%";
            else result += " ";
        }
        return result.substring(0, result.length() - 1).replace("%", "\n");
    }
}

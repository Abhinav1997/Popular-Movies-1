package io.github.the_dagger.movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harshit on 2/12/2016.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    List<Trailers.SingleTrailer> list;
    Context c;
    int position;
    public TrailersAdapter(List<Trailers.SingleTrailer> list,Context c) {
        Log.e("Constructor","I ran");
        this.list = list;
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_trailer_item,parent,false);
        Log.e("onCreate","I ran");
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.position = position;
        for (int i = 0; i < getItemCount(); i++) {
            Picasso.with(c).load("http://img.youtube.com/vi/" + list.get(position).getKey() + "/0.jpg").error(R.drawable.placeholder).into(holder.i);
            holder.t.setText(list.get(position).getTitle());
        }
    }
    @Override
    public int getItemCount() {
        if (list == null){
            return -1;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView i;
        public TextView t;
        public ViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.trailer_name);
            i = (ImageView) itemView.findViewById(R.id.singleTrailerImageView);
            t.setOnClickListener(this);
            i.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Uri videoTrailer = Uri.parse("https://www.youtube.com/watch?v="+list.get(position).getKey());
            Intent videoIntent = new Intent(Intent.ACTION_VIEW,videoTrailer);
            c.startActivity(videoIntent);
        }
    }
    public void swapList(List<Trailers.SingleTrailer> items){
        this.list = items;
        notifyDataSetChanged();
    }
}
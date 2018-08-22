package com.example.jlam.cobrakaisoundboard;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class MyAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private Context mContext;
    private int[] voiceList;
    MediaPlayer hi, bye;
    Analytics analytics = new Analytics();

    public MyAdapter(Context mContext, int[] voiceList) {
        this.mContext = mContext;
        this.voiceList = voiceList;
        hi = MediaPlayer.create(this.mContext, R.raw.hi);
        bye = MediaPlayer.create(this.mContext, R.raw.bye);
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_custom_layout,
                parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, final int position) {
        holder.mPlace.setImageResource(voiceList[position]);
        holder.mPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked on: " + position , Toast.LENGTH_SHORT).show();

                if((position%2)==0)
                {
                    hi.start();
                    analytics.sendAnalytics(mContext, "HI");
                }
                else
                {
                    bye.start();
                    analytics.sendAnalytics(mContext, "BYE");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return voiceList.length;
    }
}

class PlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView mPlace;

    public PlaceViewHolder(View itemView) {
        super(itemView);

        mPlace = itemView.findViewById(R.id.iv_hi);
    }
}



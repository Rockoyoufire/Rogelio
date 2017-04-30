package com.longisland_219408hotmail.mathematicswolf.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.longisland_219408hotmail.mathematicswolf.DetailsListActivity;
import com.longisland_219408hotmail.mathematicswolf.R;
import com.longisland_219408hotmail.mathematicswolf.model.MathematicsWolf;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by edgararana on 25/04/17.
 */

public class RvImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MathematicsWolf> mathematicsWolfList;
    private Context context;
    private ClickListener clickListener;


    public RvImageAdapter(Context context, List<MathematicsWolf> moviesList) {
        this.mathematicsWolfList = moviesList;
        this.context = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details, parent, false);

        return new ViewHolderImage(itemView);


    }

/*    @Override
    public int getItemViewType(int position) {
        ImageMeme medioFolios = imageMemeList.get(position);

    }*/

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MathematicsWolf imageMeme = mathematicsWolfList.get(position);


        if (holder instanceof ViewHolderImage) {
            final ViewHolderImage viewHolderImage = (ViewHolderImage) holder;
            Log.e("image", String.valueOf(mathematicsWolfList.get(position).getImage()));
            Picasso.with(context).load(mathematicsWolfList.get(position).getImage())
                    .into(viewHolderImage.Imageview);

            viewHolderImage.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //clickListener.itemClicked(position);


                }

            });
        }

    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {

        if (holder instanceof ViewHolderImage) {
            ViewHolderImage viewHolderVideo = (ViewHolderImage) holder;
            super.onViewRecycled(viewHolderVideo);
        }


    }


    class ViewHolderImage extends RecyclerView.ViewHolder {
        ImageView Imageview;
        FrameLayout main;

        ViewHolderImage(View view) {
            super(view);
            Imageview = (ImageView) view.findViewById(R.id.item_image);
            main = (FrameLayout) view.findViewById(R.id.almf_root);
        }
    }


    @Override
    public int getItemCount() {
        return mathematicsWolfList.size();
    }

    public interface ClickListener {
        void itemClicked(int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


}
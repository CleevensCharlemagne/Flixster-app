package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.util.Pair;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;

import java.util.List;

import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get the movie at the passed position
        Movie movie = movies.get(position);

        // Bind the movie data into the view holder
        holder.bind(movie);

    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;
        //RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;

            // if phone is landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                // Than imageURL = back drop image
                imageURL = movie.getBackdropPath();
            } else {
                // else imageURL = poster image
                imageURL = movie.getPosterPath();
            }

            //Glide.with(context).load(imageURL).into(ivPoster);
            int radius = 30; // corner radius, higher value = more rounded
            int margin = 10; // crop margin, set to 0 for corners with no crop
            Glide.with(context).load(imageURL).apply(new RequestOptions().placeholder(R.drawable.button_black_play)).apply(new RequestOptions().transform(new RoundedCornersTransformation(radius, margin))).into(ivPoster);
          container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));

                   Pair<View, String> a1 = Pair.create((View)tvTitle, "title");
                   Pair<View, String> a2 = Pair.create((View)tvOverview, "overview");
                   Pair<View, String> a3 = Pair.create((View)ivPoster, "image");

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, a1, a2, a3);
                    context. startActivity(i, options.toBundle());
                }
            });
        }

//        public Bitmap putOverlay(Bitmap bmp1, Bitmap overlay) {
//            Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bmOverlay);
//            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
//
//            canvas.drawBitmap(bmp1, 0, 0, null);
//            canvas.drawBitmap(overlay, 0, 0, null);
//
//            return bmOverlay;
//        }
    }
}

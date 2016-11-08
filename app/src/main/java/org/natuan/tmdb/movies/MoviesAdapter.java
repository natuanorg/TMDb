package org.natuan.tmdb.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.natuan.tmdb.R;
import org.natuan.tmdb.data.model.Movie;
import org.natuan.tmdb.util.Logger;
import org.natuan.tmdb.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MoviesAdapter(Context context) {
        this.mContext = context;
    }

    public void initData(@NonNull List<Movie> movies) {
        Logger.enter();
        checkNotNull(movies);
        mMovies = movies;
        Logger.d("Movies Size: " + mMovies.size());
        notifyDataSetChanged();
        Logger.exit();
    }

    public void updateData(@NonNull List<Movie> movies) {
        Logger.enter();
        checkNotNull(movies);
        Logger.d("New Movies Size: " + movies.size());
        mMovies.clear();
        mMovies.addAll(movies);
        Logger.d("Movies Size: " + mMovies.size());
        notifyDataSetChanged();
        Logger.exit();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if (mMovies.get(position) != null) {
            Movie movie = mMovies.get(position);
            holder.mTvName.setText(movie.getTitle());
            holder.mTvOverview.setText(movie.getOverview());
            Picasso.with(mContext).load(Utils.getPosterPath(movie.getPosterPath())).into(holder.mIvPoster);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private ImageView mIvPoster;
        private TextView mTvOverview;
        public MovieViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mIvPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            mTvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }
}

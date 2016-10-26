package example.com.tmdb.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.tmdb.R;
import example.com.tmdb.data.model.Movie;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();

    public MoviesAdapter() {
    }

    public void initData(List<Movie> movies) {
        if (movies == null || movies.size() == 0) return;
        if (this.mMovies != null) this.mMovies.clear();
        this.mMovies = movies;
        notifyDataSetChanged();
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
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}

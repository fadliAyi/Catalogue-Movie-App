package com.dicoding.academies.ayi.cataloguemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.academies.ayi.cataloguemovie.entity.MovieModel;
import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.R;
import com.dicoding.academies.ayi.cataloguemovie.activity.MovieItemActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ayi on 12/12/17.
 */

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder> {
    private Context context;

    private List<NewMovieModel> movieModels;


    public NowPlayingAdapter(List<NewMovieModel> movieModels, Context context){
        this.movieModels = movieModels;
        this.context = context;
    }

    public void clearData(){
        movieModels.clear();
    }

    @Override
    public NowPlayingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, parent, false);
        return new NowPlayingViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(NowPlayingViewHolder holder, final int position) {
        holder.tvJudul.setText(movieModels.get(position).getTitle());
        holder.tvDesc.setText(movieModels.get(position).getOverview());
        //holder.tvTerbit.setText(movieModels.get(position).getRelease_date());
        Picasso.with(context).load(movieModels.get(position).getPoster()).into(holder.imgMovie);
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "klik"+movieModels.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                pindahActivity(position, v);
            }
        });
    }

    private void pindahActivity(int position, View v) {
        MovieModel mMovie = new MovieModel();

        mMovie.setTitle(movieModels.get(position).getTitle());
        mMovie.setOverview(movieModels.get(position).getOverview());
        mMovie.setPopulariry(movieModels.get(position).getPopularity());
        mMovie.setRelease_date(movieModels.get(position).getRelease_date());
        mMovie.setBanner(movieModels.get(position).getBanner());
        mMovie.setPoster(movieModels.get(position).getPoster());
        Intent intent = new Intent(v.getContext(), MovieItemActivity.class);
        intent.putExtra(MovieItemActivity.EXTRA_MOVIE, (Parcelable) mMovie);
        v.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder{
        TextView tvJudul, tvDesc, tvTerbit;
        ImageView imgMovie;
        Button btnDetail;
        public NowPlayingViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView)itemView.findViewById(R.id.np_text_judul);
            tvDesc = (TextView)itemView.findViewById(R.id.np_text_desc);
            //tvTerbit = (TextView)itemView.findViewById(R.id.np_text_terbit);
            imgMovie = (ImageView)itemView.findViewById(R.id.np_img_movie);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);

            //btnDetail.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            if (v.getId() == btnDetail.getId()){
//                Toast.makeText(context, "Halaman detail", Toast.LENGTH_SHORT).show();
//            }
//
//        }
    }
}

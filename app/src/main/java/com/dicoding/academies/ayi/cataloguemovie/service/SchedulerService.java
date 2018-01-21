package com.dicoding.academies.ayi.cataloguemovie.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.dicoding.academies.ayi.cataloguemovie.R;
import com.dicoding.academies.ayi.cataloguemovie.activity.MovieItemActivity;
import com.dicoding.academies.ayi.cataloguemovie.adapter.UpComingAdapter;
import com.dicoding.academies.ayi.cataloguemovie.entity.MovieModel;
import com.dicoding.academies.ayi.cataloguemovie.entity.NewMovieModel;
import com.dicoding.academies.ayi.cataloguemovie.entity.ResponseNewMovieModel;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayi on 21/01/18.
 */

public class SchedulerService extends GcmTaskService{
    public static String TAG_TASK_UPCOMING = "upcomingScheduler";
    private final static String api_key = "99ddbbc08814cee32a1f8b5c631744a1";
    private final int NOTIF_ID_SCHEDULER = 108;
    List<NewMovieModel> movieModels;
    final MovieModel mMovie = new MovieModel();
    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(TAG_TASK_UPCOMING)){
            getApiService();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();
        UpcomingScheduler upcomingScheduler = new UpcomingScheduler(this);
        upcomingScheduler.createPeriodicTask();
    }

    public void getApiService() {
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call1 = apiInterface.getUpcomingMovie(api_key);

        call1.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                movieModels = response.body().getResults();

                mMovie.setTitle(movieModels.get(0).getTitle());
                mMovie.setOverview(movieModels.get(0).getOverview());
                mMovie.setPopulariry(movieModels.get(0).getPopularity());
                mMovie.setRelease_date(movieModels.get(0).getRelease_date());
                mMovie.setBanner(movieModels.get(0).getBanner());
                mMovie.setPoster(movieModels.get(0).getPoster());
                Log.d("movieModels", "title :"+mMovie.getTitle());
                showNotification(getApplicationContext());
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable t) {

            }
        });
    }

    public void showNotification(Context context){
        Intent pindah = new Intent(context, MovieItemActivity.class);
        pindah.putExtra(MovieItemActivity.EXTRA_MOVIE, (Parcelable) mMovie);
        pindah.setAction(Long.toString(System.currentTimeMillis()));
        Log.d("getmovie", "titlenya : "+mMovie.getTitle());
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, pindah, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification  = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_movie_filter_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_movie_filter_black_24dp))
                .setContentTitle("New Upcoming Movie")
                .setContentText("Sudah tau tentang film "+mMovie.getTitle()+" ?")
                .setContentIntent(pendingIntent1)
                .setSound(alarmSound)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIF_ID_SCHEDULER, notification.build());
    }
}

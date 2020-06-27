package com.ash.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    MyApi myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = RectroifClient.getInstance();
        myApi = retrofit.create(MyApi.class);

        fetching();


    }

    private void fetching() {
        compositeDisposable.add(myApi.getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Pojo>>() {
                    @Override
                    public void accept(List<Pojo> pojos) throws Exception {
                        ItemAdapter itemAdapter = new ItemAdapter(pojos, getApplicationContext());
                        recyclerView.setAdapter(itemAdapter);
                    }
                })

        );
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}

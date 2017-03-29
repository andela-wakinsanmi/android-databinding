package com.andela.playdatabinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.andela.playdatabinding.adapter.DataRecyclerAdapter;
import com.andela.playdatabinding.http.DataFetcher;
import com.andela.playdatabinding.http.DataFetcherImpl;
import com.andela.playdatabinding.model.User;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private DataFetcher dataFetcher;
    private Subscription subscription;
    private RecyclerView recyclerView;
    private DataRecyclerAdapter dataRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        dataRecyclerAdapter = new DataRecyclerAdapter();


        dataFetcher = new DataFetcherImpl();
        subscription = dataFetcher.fetchDataFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        dataRecyclerAdapter.swapList(users);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription == null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }
}

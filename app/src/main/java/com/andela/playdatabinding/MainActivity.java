package com.andela.playdatabinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andela.playdatabinding.adapter.DataRecyclerAdapter;
import com.andela.playdatabinding.http.DataFetcher;
import com.andela.playdatabinding.http.DataFetcherImpl;
import com.andela.playdatabinding.model.Dog;
import com.andela.playdatabinding.model.Person;
import com.andela.playdatabinding.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataRecyclerAdapter);


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
                        Log.d("waleola", "OnError called");

                    }

                    @Override
                    public void onNext(List<User> users) {
                        dataRecyclerAdapter.swapList(users);
                    }
                });

        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 2).findAll();
        puppies.size();
        Log.d("waleola", "The size is = " + puppies.size());

        puppies.addChangeListener(new RealmChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> element) {
                Log.d("waleola", "The vsize after adding is " + element.size());
            }
        });

        realm.beginTransaction();

        final Dog managedDog = realm.copyToRealm(dog);

        Person person = realm.createObject(Person.class);
        person.getDogs().add(managedDog);
        realm.commitTransaction();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = realm.where(Dog.class).equalTo("age", 1).findFirst();
                dog.setAge(3);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("waleola", "the new size of puppies " + puppies.size());
                Log.d("waleola", "the managed dog's age is " + managedDog.getAge());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }
}

package com.fjq.realmbrowerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.button4, R.id.button3, R.id.button2, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                Realm realm= Realm.getDefaultInstance();

                realm.beginTransaction();
                Dog user = realm.createObject(Dog.class); // Create a new object
                user.setName("John");
                user.setAge(11);
                realm.commitTransaction();
                break;
            case R.id.button3:
                Realm  mRealm=Realm.getDefaultInstance();

                final RealmResults<Dog> dogs=  mRealm.where(Dog.class).findAll();

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //删除所有数据
                        dogs.deleteAllFromRealm();
                    }
                });
                break;
            case R.id.button2:
                break;
            case R.id.button:
                Log.i(TAG, "onClick: "+queryAllDog().size());
                break;
        }
    }

    public List<Dog> queryAllDog() {
        Realm  mRealm=Realm.getDefaultInstance();

        RealmResults<Dog> dogs = mRealm.where(Dog.class).findAll();

        return mRealm.copyFromRealm(dogs);
    }
}

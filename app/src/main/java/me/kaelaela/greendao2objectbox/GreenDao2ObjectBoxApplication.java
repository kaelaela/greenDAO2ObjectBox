package me.kaelaela.greendao2objectbox;

import android.app.Application;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import me.kaelaela.greendao2objectbox.entity.DaoSession;
import me.kaelaela.greendao2objectbox.entity.Memo;
import me.kaelaela.greendao2objectbox.entity.MyObjectBox;

public class GreenDao2ObjectBoxApplication extends Application {
    private BoxStore boxStore;
    private DaoSession daoSession;

    @Override public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        daoSession = new DaoSession(boxStore);
    }

    public BoxStore getBoxStore() {
        return boxStore ;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}

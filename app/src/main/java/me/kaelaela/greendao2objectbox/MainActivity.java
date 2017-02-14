package me.kaelaela.greendao2objectbox;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Date;
import java.util.List;
import me.kaelaela.greendao2objectbox.entity.DaoMaster;
import me.kaelaela.greendao2objectbox.entity.DaoSession;
import me.kaelaela.greendao2objectbox.entity.Memo;
import me.kaelaela.greendao2objectbox.entity.MemoDao;

public class MainActivity extends AppCompatActivity {

    private final String DB_NAME = "sample-db";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        final DaoSession session = master.newSession();

        final EditText memoEditor = (EditText) findViewById(R.id.memo_editor);
        final Button saveButton = (Button) findViewById(R.id.save_button);

        final RecyclerView memoList = (RecyclerView) findViewById(R.id.memo_list);
        memoList.setLayoutManager(new LinearLayoutManager(this));
        final MemoAdapter adapter = new MemoAdapter(new MemoAdapter.OnItemCheckListener() {
            @Override public void onItemCheck(Memo memo) {
                updateMemo(session, memo);
            }

            @Override public void onItemDelete(Memo memo) {
                deleteMemo(session, memo);
            }
        });
        final long editorId = 1L;
        MemoDao dao = session.getMemoDao();
        List<Memo> dbMemoList = dao.loadAll();
        adapter.setMemoList(dbMemoList);
        memoList.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Memo memo = new Memo(null, memoEditor.getText().toString(),
                        new Date(System.currentTimeMillis()), false, editorId);
                saveMemo(session, memo);
                memoEditor.setText("");
                adapter.addMemo(memo);
                memoList.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }

    private void saveMemo(DaoSession session, Memo memo) {
        MemoDao dao = session.getMemoDao();
        dao.insert(memo);
    }

    private void updateMemo(DaoSession session, Memo memo) {
        MemoDao dao = session.getMemoDao();
        dao.update(memo);
    }

    private void deleteMemo(DaoSession session, Memo memo) {
        MemoDao dao = session.getMemoDao();
        dao.delete(memo);
    }
}

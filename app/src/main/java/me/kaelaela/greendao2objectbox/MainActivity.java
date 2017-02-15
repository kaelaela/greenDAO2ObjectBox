package me.kaelaela.greendao2objectbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import io.objectbox.Box;
import java.util.Date;
import java.util.List;
import me.kaelaela.greendao2objectbox.entity.Memo;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Box<Memo> memoBox =
                ((GreenDao2ObjectBoxApplication) getApplicationContext()).getBoxStore()
                        .boxFor(Memo.class);

        final EditText memoEditor = (EditText) findViewById(R.id.memo_editor);
        final Button saveButton = (Button) findViewById(R.id.save_button);

        final RecyclerView memoList = (RecyclerView) findViewById(R.id.memo_list);
        memoList.setLayoutManager(new LinearLayoutManager(this));
        final MemoAdapter adapter = new MemoAdapter();
        final long editorId = 1L;
        List<Memo> dbMemoList = memoBox.getAll();
        adapter.setMemoList(dbMemoList);
        memoList.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Memo memo = new Memo(null, memoEditor.getText().toString(),
                        new Date(System.currentTimeMillis()), false, editorId);
                memoBox.put(memo);
                memoEditor.setText("");
                adapter.addMemo(memo);
                memoList.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }
}

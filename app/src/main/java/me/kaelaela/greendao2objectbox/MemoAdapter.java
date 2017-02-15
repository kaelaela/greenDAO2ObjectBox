package me.kaelaela.greendao2objectbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import me.kaelaela.greendao2objectbox.entity.Memo;

public class MemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Memo> memoList = new ArrayList<>();

    public void addMemo(Memo memo) {
        memoList.add(memo);
        notifyItemInserted(memoList.size());
    }

    public void setMemoList(List<Memo> memoList) {
        this.memoList.clear();
        this.memoList.addAll(memoList);
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MemoViewHolder(inflater.inflate(R.layout.item_memo, parent, false));
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MemoViewHolder) {
            MemoViewHolder viewHolder = (MemoViewHolder) holder;
            viewHolder.set(memoList.get(position));
        }
    }

    @Override public int getItemCount() {
        return memoList.size();
    }

    private class MemoViewHolder extends RecyclerView.ViewHolder {

        private Memo memo;
        public MemoViewHolder(View itemView) {
            super(itemView);
        }

        public void set(Memo memo) {
            this.memo = memo;
            ((TextView) itemView.findViewById(R.id.memo)).setText(this.memo.getContent());
            final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.done_check);
            checkBox.setChecked(this.memo.getIsDone());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    MemoViewHolder.this.memo.setIsDone(isChecked);
                    MemoViewHolder.this.memo.put();
                }
            });
            Button deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    memoList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    MemoViewHolder.this.memo.remove();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    MemoViewHolder.this.memo.setIsDone(!checkBox.isChecked());
                    MemoViewHolder.this.memo.put();
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });
        }
    }
}

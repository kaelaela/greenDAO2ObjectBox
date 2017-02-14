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
    private OnItemCheckListener listener;

    public interface OnItemCheckListener {
        void onItemCheck(Memo memo);

        void onItemDelete(Memo memo);
    }

    public MemoAdapter(OnItemCheckListener listener) {
        this.listener = listener;
    }

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

        public MemoViewHolder(View itemView) {
            super(itemView);
        }

        public void set(final Memo memo) {
            ((TextView) itemView.findViewById(R.id.memo)).setText(memo.getContent());
            final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.done_check);
            checkBox.setChecked(memo.getIsDone());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    memo.setIsDone(isChecked);
                    listener.onItemCheck(memo);
                }
            });
            Button deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemDelete(memo);
                    memoList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    memo.setIsDone(!checkBox.isChecked());
                    listener.onItemCheck(memo);
                    checkBox.setChecked(!checkBox.isChecked());
                }
            });
        }
    }
}

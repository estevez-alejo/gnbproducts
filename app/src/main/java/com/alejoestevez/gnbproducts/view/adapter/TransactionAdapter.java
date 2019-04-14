package com.alejoestevez.gnbproducts.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.databinding.TransactionListItemBinding;
import com.alejoestevez.gnbproducts.data.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    List<? extends Transaction> transactionList;

    public void setProjectList(final List<? extends Transaction> articleList) {
        if (this.transactionList == null) {
            this.transactionList = articleList;
            notifyItemRangeInserted(0, articleList.size());
        }
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TransactionListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.transaction_list_item,
                        parent, false);


        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.binding.setTransaction(transactionList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return transactionList == null ? 0 : transactionList.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        final TransactionListItemBinding binding;

        public TransactionViewHolder(TransactionListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

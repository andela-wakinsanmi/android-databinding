package com.andela.playdatabinding.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.playdatabinding.R;
import com.andela.playdatabinding.databinding.RecyclerItemBinding;
import com.andela.playdatabinding.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataRecyclerAdapter extends RecyclerView.Adapter<DataRecyclerAdapter.MyViewHolder>  {
    private List<User> users;

    public DataRecyclerAdapter() {
        users = new ArrayList<>();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View itemView = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.recycler_item, parent, false);*/

        RecyclerItemBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.recycler_item, parent,false);
        return new MyViewHolder(viewDataBinding);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void swapList(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding binding;

        public MyViewHolder(RecyclerItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}

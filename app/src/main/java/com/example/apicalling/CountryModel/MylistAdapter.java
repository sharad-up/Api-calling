
package com.example.apicalling.CountryModel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apicalling.CountryModel.CountryModelClass;
import com.example.apicalling.R;

import java.util.ArrayList;


public class MylistAdapter extends RecyclerView.Adapter<MylistAdapter.MyViewHolder> {
    private Activity mactivity;
    private ArrayList<CountryModelClass> mlist;
    LinearLayout listco;
    RecyclerView.LayoutManager layoutManager;

    public MylistAdapter(Activity activity,ArrayList<CountryModelClass> list)
    {
        mactivity= activity;
        mlist = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.countrylist, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(mlist.get(position));

    }

    @Override
    public int getItemCount() {
        return mlist.size() ;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;

        public MyViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.coname);
            textView2 = view.findViewById(R.id.shname);
        }

        public void bindData(CountryModelClass country) {
            textView1.setText(country.getCountryname());
            textView2.setText(country.getShortname());
        }
    }
}



package com.aris.covid19app.adapter;

import android.app.SearchManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aris.covid19app.R;
import com.aris.covid19app.model.CountryModel;
import com.aris.covid19app.rest.respons.ResCovid;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CocidViewHolder> implements Filterable {

    private List<CountryModel> dataList;
    private List<CountryModel> exampleListFul;
    private SearchView searchView;
    private Context context;


    public MainAdapter(List<CountryModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        exampleListFul = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public MainAdapter.CocidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.covid_item, parent, false);
        return new CocidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CocidViewHolder holder, int position) {

        holder.country.setText(dataList.get(position).getCountry());
        holder.newConfirmed.setText(Integer.toString(dataList.get(position).getNewConfirmed()));
        holder.TotalConfirmed.setText(Integer.toString(dataList.get(position).getTotalConfirmed()));
        holder.NewDeaths.setText(Integer.toString(dataList.get(position).getNewDeaths()));
        holder.TotalDeaths.setText(Integer.toString(dataList.get(position).getTotalDeaths()));
        holder.NewRecovered.setText(Integer.toString(dataList.get(position).getNewRecovered()));
        holder.TotalRecovered.setText(Integer.toString(dataList.get(position).getTotalRecovered()));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    exampleListFul = dataList;

                } else {

                    ArrayList<CountryModel> tempFilteredList = new ArrayList<>();

                    for (CountryModel countryModel : dataList) {

                        // search for user name
                        if (countryModel.getCountry().toLowerCase().contains(searchString)) {

                            tempFilteredList.add(countryModel);
                        }
                    }

                    exampleListFul = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = exampleListFul;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                exampleListFul = (ArrayList<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void addList(List<CountryModel> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }


    static class CocidViewHolder extends RecyclerView.ViewHolder {

        TextView country, TotalConfirmed, NewDeaths, newConfirmed, TotalDeaths, NewRecovered, TotalRecovered;

        final View mView;


        CocidViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            country = itemView.findViewById(R.id.Country);
            TotalConfirmed = itemView.findViewById(R.id.TotalConfirmed);
            newConfirmed = itemView.findViewById(R.id.NewConfirmed);
            NewDeaths = itemView.findViewById(R.id.NewDeaths);
            TotalDeaths = itemView.findViewById(R.id.TotalDeaths);
            NewRecovered = itemView.findViewById(R.id.NewRecovered);
            TotalRecovered = itemView.findViewById(R.id.TotalRecovered);

        }
    }
}

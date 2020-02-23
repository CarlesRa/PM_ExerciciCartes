package com.carlesramos.pm_exercicicartes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.adapters.CartaRankingAdapter;
import com.carlesramos.pm_exercicicartes.adapters.PlayerRankingAdapter;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.model.Cartas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingCartasFragment extends Fragment {

    private ArrayList<Cartas> cartas;
    private IApiInterface mApiInterface;
    private RecyclerView rvCartas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ranking_cartas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCartas = getActivity().findViewById(R.id.rvCartasRanking);
        mApiInterface = APIUtils.getIApiInterface();
        mApiInterface.getCartas().enqueue(new Callback<ArrayList<Cartas>>() {
            @Override
            public void onResponse(Call<ArrayList<Cartas>> call, Response<ArrayList<Cartas>> response) {
                if (response.isSuccessful()){
                    cartas = response.body();
                    DividerItemDecoration dividerItemDecoration =
                            new DividerItemDecoration(rvCartas.getContext(),
                                    DividerItemDecoration.VERTICAL);
                    rvCartas.addItemDecoration(dividerItemDecoration);
                    CartaRankingAdapter adapter = new CartaRankingAdapter(cartas, getActivity());
                    rvCartas.setAdapter(adapter);
                    rvCartas.setHasFixedSize(true);
                    rvCartas.setLayoutManager(new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL,false));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cartas>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

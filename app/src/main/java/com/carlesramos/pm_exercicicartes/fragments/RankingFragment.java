package com.carlesramos.pm_exercicicartes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.carlesramos.pm_exercicicartes.R;
import com.carlesramos.pm_exercicicartes.adapters.PlayerRankingAdapter;
import com.carlesramos.pm_exercicicartes.apiclient.APIUtils;
import com.carlesramos.pm_exercicicartes.interfaces.IApiInterface;
import com.carlesramos.pm_exercicicartes.model.Jugadores;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingFragment extends Fragment {

    private RecyclerView rvRanking;
    private IApiInterface apiInterface;
    private ArrayList<Jugadores> jugadores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ranking_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvRanking = getActivity().findViewById(R.id.rvRanking);
        apiInterface = APIUtils.getIApiInterface();
        apiInterface.getJugadores().enqueue(new Callback<ArrayList<Jugadores>>() {
            @Override
            public void onResponse(Call<ArrayList<Jugadores>> call, Response<ArrayList<Jugadores>> response) {
                if(response.isSuccessful()){
                    jugadores = response.body();
                    DividerItemDecoration dividerItemDecoration =
                            new DividerItemDecoration(rvRanking.getContext(),
                                    DividerItemDecoration.VERTICAL);
                    rvRanking.addItemDecoration(dividerItemDecoration);
                    PlayerRankingAdapter adapter = new PlayerRankingAdapter(jugadores);
                    rvRanking.setAdapter(adapter);
                    rvRanking.setHasFixedSize(true);
                    rvRanking.setLayoutManager(new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL,false));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Jugadores>> call, Throwable t) {

            }
        });
    }


}

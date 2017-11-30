package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.theoldpinkeye.finalappforudemymvpcourse.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    private final String TAG = TopMoviesActivity.class.getName();
    private ListAdapter listAdapter;
    private List<ViewModel> resultList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);


        ButterKnife.bind(this);


    }

    @Override
    public void updateData(ViewModel viewModel) {  //Updates RecyclerView
        resultList.add(viewModel);
        int itemToInsert = resultList.isEmpty() ? 0 : resultList.size()-1;
        listAdapter.notifyItemInserted(itemToInsert);
        Log.d(TAG, "updateData: " + resultList.size());

    }

    @Override
    public void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    }
}

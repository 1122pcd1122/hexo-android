package activitytest.example.com.roomdemo.home;


import android.os.Bundle;

import activitytest.example.com.roomdemo.databinding.FragmentHomeBinding;
import activitytest.example.com.roomdemo.home.bean.Root;
import activitytest.example.com.roomdemo.home.lifecycle.HomeLifeCycle;
import activitytest.example.com.roomdemo.home.viewmodel.BlogViewModel;
import activitytest.example.com.roomdemo.home.vo.BlogIntroduce;
import activitytest.example.com.roomdemo.main.database.Configuration;
import activitytest.example.com.roomdemo.main.viewModel.ConfigurationViewModel;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import activitytest.example.com.roomdemo.R;
import androidx.lifecycle.ViewModelProvider;


public class HomeFragment extends Fragment {


    private final String TAG = HomeFragment.class.getName ();

    @Override
    public void onStart() {
        super.onStart ();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        BlogViewModel blogViewModel = new ViewModelProvider ( this ).get ( BlogViewModel.class );
        ConfigurationViewModel configurationViewModel = new ViewModelProvider ( this ).get ( ConfigurationViewModel.class );
        getLifecycle ().addObserver ( new HomeLifeCycle (this , blogViewModel ,configurationViewModel) );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate ( R.layout.fragment_home, container, false );
    }
}
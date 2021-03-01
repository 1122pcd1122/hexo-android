package activitytest.example.com.roomdemo.main.viewModel;

import android.util.Log;

import activitytest.example.com.roomdemo.MyApplication;
import activitytest.example.com.roomdemo.main.MainRepository;

import activitytest.example.com.roomdemo.main.database.Configuration;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class ConfigurationViewModel extends ViewModel {

    private static final String TAG  = ConfigurationViewModel.class.getName ();


    private LiveData<Configuration> configurationMutableLiveDataNet;
    private LiveData<Configuration> configurationMutableLiveDataBd;
    private LiveData<Integer> code;

    public void init(){
        Log.d ( TAG,"创建仓库" );
        MainRepository mainRepository = MainRepository.getInstance ();
        configurationMutableLiveDataNet = mainRepository.getConfiguration ();
        configurationMutableLiveDataBd = mainRepository.getConfigurationFromDB ();
        code = mainRepository.getCode ();
    }

    public LiveData<Configuration> getConfigurationNet(){
        Log.d ( TAG,"网络获取" );
        return configurationMutableLiveDataNet;
    }
    public LiveData<Configuration> getConfigurationDb(){
        Log.d ( TAG,"数据库获取" );

        return configurationMutableLiveDataBd;
    }

    public LiveData<Integer> getCode(){
        Log.d ( TAG,"获取状态码" );
        return code;
    }

}

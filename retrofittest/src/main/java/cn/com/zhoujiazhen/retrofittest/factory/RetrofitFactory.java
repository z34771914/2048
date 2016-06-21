package cn.com.zhoujiazhen.retrofittest.factory;

import java.util.Map;

import cn.com.zhoujiazhen.retrofittest.base.BaseNetworkService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by zhoujiazhen on 16/5/24.
 */
public class RetrofitFactory {
    private static final String APIKEY = "a72050deb6ee2a454cfec3b4368a0f8a";

    private final String TAG = "RetrofitFactory";

    private static Retrofit singleton;
    private static Call<ResponseBody> call;

    public static <T> T createApi(Class<T> clazz, String url){
        if (singleton == null){
            synchronized (RetrofitFactory.class){
                if (singleton == null){
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(url);
                    singleton = builder.build();
                }
            }
        }

        return singleton.create(clazz);
    }

    public static Call<ResponseBody> setGetRequest(BaseNetworkService service, Map<String,String> params){
        if (call == null){
            synchronized (RetrofitFactory.class){
                if (call == null){
                    call = service.getResult(APIKEY,params);
                }
            }
        }

        return call.clone();
    }

    public static Call<ResponseBody> setPostRequest(BaseNetworkService service, Map<String,String> params){
        if (call == null){
            synchronized (RetrofitFactory.class){
                if (call == null){
                    call = service.postResult(APIKEY,params);
                }
            }
        }

        return call.clone();
    }
}

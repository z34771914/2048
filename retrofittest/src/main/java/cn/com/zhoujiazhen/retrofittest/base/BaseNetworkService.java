package cn.com.zhoujiazhen.retrofittest.base;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zhoujiazhen on 16/5/24.
 */
public interface BaseNetworkService {

    @GET(".")
    Call<ResponseBody> getResult(@Header("apikey") String apikey, @QueryMap Map<String,String> params);

    // TODO: 16/5/26 添加POST请求接口
    @FormUrlEncoded
    @POST(".")
    Call<ResponseBody> postResult(@Header("apikey") String apikey, @FieldMap Map<String,String> params);
}

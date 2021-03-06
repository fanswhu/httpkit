package com.fanswhu.httpkit.core;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {


/**
 * 一些实例
 */

    @GET("service/getIpInfo.php/")
    Observable<Object> getData(@Query("ip") String ip);

    @GET("{url}")
    Observable<Object> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);

    @POST("{url}")
    Observable<Object> executePost(
            @Path("url") String url,
            @FieldMap Map<String, Object> maps);

   /* @Multipart
    @POST("{url}")
    Observable<Object> upLoadFile(
            @Path("url") String url,
            @Part("image\\";filename =\\"image.jpg") RequestBody avatar);*/

    @POST("{url}")
    Call<Object> uploadFiles(
            @Url String url,
            @Part("filename") String description,
            @PartMap() Map<String, String> maps);
}

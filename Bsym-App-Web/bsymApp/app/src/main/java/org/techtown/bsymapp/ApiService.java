package org.techtown.bsymapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


//    @GET("test/board/bslist/")
//    Call<ResponseBody> content(@Query("broadcastName") String path);
//
//    @GET("eround/{path}")
//    Call<ResponseBody> content(@Path("path") String path, @Query("num") String query);

//    @GET("/repos/{owner}/{repo}/contributors")
//Call<List<as>> asds(
//        @Path("owner") String owner,
//        @Path("repo") String repo);

    @FormUrlEncoded
    @POST("/bsym/api/joinProcess")
    Call<User> sendName(@Field("username") String username, @Field("name") String name, @Field("password") String password);


//    @GET("/bsym/app/bloglistPage/cook")
//    Call<List<as>> asds(@Query("broadcastName") String broadcastName,@Query("page") int page);

    @GET("/bsym/app/bloglistAll/cook")
    Call<List<as>> asds(@Query("broadcastName") String broadcastName ,@Query("username") String username);

//    @GET("/bsym/app/")
//    Call<List<as>> likelist();

    @GET("/bsym/api/loginProcess")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @FormUrlEncoded
    @POST("/bsym/app/like")
    Call<ContentMenu> check(@Field("username") String username, @Field("boardid") int boardid);

    @FormUrlEncoded
    @POST("/bsym/app/unlike")
    Call<ContentMenu> uncheck(@Field("username") String username, @Field("boardid") int boardid);

    @GET("/bsym/app/detail")
    Call<as> test(@Query("boardid") int boardid);

    @GET("/bsym/app/myPage/like")
    Call<List<as>> boguanham(@Query("username") String username);

}
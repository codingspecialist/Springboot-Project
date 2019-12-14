package org.techtown.elderlyperson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("management")
    Call<Person> per();

}

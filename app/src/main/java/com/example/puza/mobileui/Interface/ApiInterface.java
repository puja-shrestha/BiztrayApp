package com.example.puza.mobileui.Interface;

import com.example.puza.mobileui.models.FeaturedDao.Example;
import com.example.puza.mobileui.models.HotAtBiztrayDao.HBiztray;
import com.example.puza.mobileui.models.LoginNew;

import com.example.puza.mobileui.models.PostAdd;
import com.example.puza.mobileui.models.ProductCategoriesDAO.ProductCategories;
import com.example.puza.mobileui.models.ProductDetailsDAO.Message;
import com.example.puza.mobileui.models.ProductDetailsDAO.ProductDetails;
import com.example.puza.mobileui.models.RecentDao.RecentProducts;
import com.example.puza.mobileui.models.Registernew;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("register")
    Call<Registernew> createRegisterUser(@Header(value = "securitykey")String securitykey,
                                 @Query(value = "first_name")String first_name,
                                 @Query(value = "last_name")String last_name,
                                 @Query(value = "email")String email,
                                 @Query(value = "password")String password,
                                 @Query(value = "confirm_password")String confirm_password,
                                 @Query(value = "mobile")String mobile_number,
                                 @Query(value = "address") String address,
                                 @Query(value = "location")String location,
                                 @Query(value = "terms_conditions")String terms_conditions);

    @POST("login")
    Call<LoginNew> createLoginUser(@Header(value = "securitykey")String securitykey,
                                   @Query(value = "email")String email,
                                   @Query(value = "password")String password);

    @GET("featured-products")
    Call<Example> getFeaturedProducts(@Header(value = "securitykey") String securitykey);


    @GET("recent-products")
    Call<RecentProducts> getRecentProducts(@Header(value = "securitykey") String securitykey);


    @GET("hot-at-biztray")
    Call<HBiztray> getHotItem(@Header(value = "securitykey") String securitykey);


    @GET("product-details?product_id=1")
    Call<ProductDetails> getProductDetails(@Header(value = "securitykey") String securitykey);


    @GET("categories")
    Call<ProductCategories> getProductCategories(@Header(value = "securitykey") String securitykey);


    @POST("post-ad")
    Call<PostAdd> createPostAdd(@Header(value = "securitykey")String securitykey,
                                     @Query(value = "user_id") String user_id,
                                     @Query(value = "category_id") String category_id,
                                     @Query(value = "title")String title,
                                     @Query(value = "description")String description,
                                     @Query(value = "runtime")String runtime,
                                     @Query(value = "adtype")String adtype,
                                     @Query(value = "location")String location,
                                     @Query(value = "price")String price,
                                     @Query(value = "currency")String currency,
                                     @Query(value = "discount")String discount,
                                     @Query(value = "negotiable") String negotiable,
                                     @Query(value = "home_delivery")String home_delivery,
                                     @Query(value = "warranty")String warranty,
                                     @Query(value = "product_condition")String product_condition,
                                     @Query(value = "usedfor")String usedfor,
                                     @Query(value = "detailurl")String detailurl);

}

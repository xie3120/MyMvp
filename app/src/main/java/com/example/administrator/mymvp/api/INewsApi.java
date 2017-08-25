package com.example.administrator.mymvp.api;

import com.example.administrator.mymvp.api.bean.NewsDetailInfo;
import com.example.administrator.mymvp.api.bean.NewsInfo;
import com.example.administrator.mymvp.api.bean.PhotoSetInfo;
import com.example.administrator.mymvp.api.bean.SpecialInfo;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

import static com.example.administrator.mymvp.api.RetrofitService.AVOID_HTTP403_FORBIDDEN;
import static com.example.administrator.mymvp.api.RetrofitService.CACHE_CONTROL_NETWORK;

/**
 * Created by Administrator on 2017/8/21 0021.
 * API 接口
 */

public interface INewsApi {

/**
 * 获取新闻列表
 * eg: http://c.m.163.com/nc/article/headline/T1348647909107/60-20.html
 *      http://c.m.163.com/nc/article/list/T1348647909107/60-20.html
 *      @param type 新闻类型
 *      @param id 新闻ID
 *      @param startPage 起始页码
 *      @return
 */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String,List<NewsInfo>>> getNewsList(@Path("type") String type, @Path("id") String id,
                                                       @Path("startPage") int startPage);

    /**
     * 获取专题
     * eg: http://c.3g.163.com/nc/special/S1451880983492.html
     *
     * @param specialIde 专题ID
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("nc/special/{specialId}.html")
    Observable<Map<String, SpecialInfo>> getSpecial(@Path("specialId") String specialIde);

    /**
     * 获取新闻详情
     * eg: http://c.3g.163.com/nc/article/BV56RVG600011229/full.html
     *
     * @param newsId 专题ID
     * @return
     */
    @Headers(AVOID_HTTP403_FORBIDDEN)
    @GET("nc/article/{newsId}/full.html")
    Observable<Map<String, NewsDetailInfo>> getNewsDetail(@Path("newsId") String newsId);

    /**
     * 获取新闻详情
     * eg: http://c.3g.163.com/photo/api/set/0006/2136404.json
     *
     * @param photoId 图集ID
     * @return
     */
    @Headers(CACHE_CONTROL_NETWORK)
    @GET("photo/api/set/{photoId}.json")
    Observable<PhotoSetInfo> getPhotoSet(@Path("photoId") String photoId);
}

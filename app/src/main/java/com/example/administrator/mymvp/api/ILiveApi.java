package com.example.administrator.mymvp.api;

import com.example.administrator.mymvp.api.bean.LiveDetailBean;
import com.example.administrator.mymvp.api.bean.LiveListItemBean;
import com.example.administrator.mymvp.api.bean.LivePandaBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public interface ILiveApi {

    int LIMIT = 20;

    //请求获取不同游戏的直播列表
    @GET("/api/live/list/")
    Observable<Map<String,List<LiveListItemBean>>> getLiveList(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("live_type") String live_type,
            @Query("game_type") String game_type
    );

    //请求获取直播详情
    @GET("/api/live/detail/")
    Observable<LiveDetailBean> getLiveDetail(
            @Query("live_type") String live_type,
            @Query("live_id") String live_id,
            @Query("game_type") String game_type
    );

    //请求获取弹幕聊天室详情
    @GET("/ajax_chatinfo")
    Observable<LivePandaBean> getPandaChatroom(
            @Query("roomid") String roomid
    );
}

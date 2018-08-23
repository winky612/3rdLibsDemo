package com.zkhz.a3rdlibsdemo.retrofit;

import com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork.ReportData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public interface API {

//    //举报原因
//    @POST("ReportController/selectReportReasonList")
//    Call<ReportReasonData> getReportReasonData();


    //举报
    @POST("ReportController/saveReportInfo")
    @FormUrlEncoded
    Call<ReportData> getReportData(@Field("cus_id") String cusId,
                                   @Field("reported_cus_id") String reportedCusId,
                                   @Field("reported_reason") String reportedReason,
                                   @Field("report_details") String reportDetails,
                                   @Field("report_source")String reportSource);

}

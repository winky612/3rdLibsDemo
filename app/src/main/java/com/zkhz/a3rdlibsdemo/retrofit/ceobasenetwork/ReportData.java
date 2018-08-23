package com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork;

/**
 * Created by wk on 2018/8/21 0021
 *
 * 举报内容
 */
public class ReportData extends WebData {


    /**
     * data : {"saveReportInfo":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * saveReportInfo : 1
         */

        private int saveReportInfo;

        public int getSaveReportInfo() {
            return saveReportInfo;
        }

        public void setSaveReportInfo(int saveReportInfo) {
            this.saveReportInfo = saveReportInfo;
        }
    }
}

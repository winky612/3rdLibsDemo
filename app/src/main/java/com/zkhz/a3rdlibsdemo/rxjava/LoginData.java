package com.zkhz.a3rdlibsdemo.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class LoginData {

    /**
     * success : 0
     * msg : 成功
     * data : {"headUrl":"http://localhost:8088/FHMYSQL3//uploadFiles/uploadImgs/apphead/20171220/bef0fba1e11f4bf59fbd216d08e882b6.png","thumbUrl":"http://localhost:8088/FHMYSQL3//uploadFiles/uploadImgs/apphead/20171220/bef0fba1e11f4bf59fbd216d08e882b6.png","nickName":"Winky","sex":"2","age":"","realName":"","contactNum":"18204008682","individualSign":"","startTime":"","employee":"","endTime":"","phoneNum":"18204008682","hasPayPwd":"0","myManager":"中科惠泽","myManagerTel":"4006669654","birthDate":"2017-12-25","rongyun_token":"HRZ6miQHxzNaQsYLm8+xvLv/O4INbhPLcaR0Gcoi1UkthKAuC+lbak5Ql7AGCPtKkyfkhY4DIwWsG3Wx403CQiNkOzZGS/tg4czYARZWPjXLCSxVnptaUJi+h7D40pgmWW/MFGWrBnY=","userLevel":"0","userIdentity":"2","userId":"nikbGOeIdzo0JO3JTYQ9FQxCohwmKuGBTObHwhC8zKC38ZJW80F+ykxO8esZ4k/5o7sbvYnTg7aO7HaVosuTEQ==","loginTime":1524798253985,"tagList":[]}
     */

    private String success;
    private String msg;
    private DataBean data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * headUrl : http://localhost:8088/FHMYSQL3//uploadFiles/uploadImgs/apphead/20171220/bef0fba1e11f4bf59fbd216d08e882b6.png
         * thumbUrl : http://localhost:8088/FHMYSQL3//uploadFiles/uploadImgs/apphead/20171220/bef0fba1e11f4bf59fbd216d08e882b6.png
         * nickName : Winky
         * sex : 2
         * age :
         * realName :
         * contactNum : 18204008682
         * individualSign :
         * startTime :
         * employee :
         * endTime :
         * phoneNum : 18204008682
         * hasPayPwd : 0
         * myManager : 中科惠泽
         * myManagerTel : 4006669654
         * birthDate : 2017-12-25
         * rongyun_token : HRZ6miQHxzNaQsYLm8+xvLv/O4INbhPLcaR0Gcoi1UkthKAuC+lbak5Ql7AGCPtKkyfkhY4DIwWsG3Wx403CQiNkOzZGS/tg4czYARZWPjXLCSxVnptaUJi+h7D40pgmWW/MFGWrBnY=
         * userLevel : 0
         * userIdentity : 2
         * userId : nikbGOeIdzo0JO3JTYQ9FQxCohwmKuGBTObHwhC8zKC38ZJW80F+ykxO8esZ4k/5o7sbvYnTg7aO7HaVosuTEQ==
         * loginTime : 1524798253985
         * tagList : []
         */

        private String headUrl;
        private String thumbUrl;
        private String nickName;
        private String sex;
        private String age;
        private String realName;
        private String contactNum;
        private String individualSign;
        private String startTime;
        private String employee;
        private String endTime;
        private String phoneNum;
        private String hasPayPwd;
        private String myManager;
        private String myManagerTel;
        private String birthDate;
        private String rongyun_token;
        private String userLevel;
        private String userIdentity;
        private String userId;
        private long loginTime;
        private List<?> tagList;

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getContactNum() {
            return contactNum;
        }

        public void setContactNum(String contactNum) {
            this.contactNum = contactNum;
        }

        public String getIndividualSign() {
            return individualSign;
        }

        public void setIndividualSign(String individualSign) {
            this.individualSign = individualSign;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getHasPayPwd() {
            return hasPayPwd;
        }

        public void setHasPayPwd(String hasPayPwd) {
            this.hasPayPwd = hasPayPwd;
        }

        public String getMyManager() {
            return myManager;
        }

        public void setMyManager(String myManager) {
            this.myManager = myManager;
        }

        public String getMyManagerTel() {
            return myManagerTel;
        }

        public void setMyManagerTel(String myManagerTel) {
            this.myManagerTel = myManagerTel;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getRongyun_token() {
            return rongyun_token;
        }

        public void setRongyun_token(String rongyun_token) {
            this.rongyun_token = rongyun_token;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public String getUserIdentity() {
            return userIdentity;
        }

        public void setUserIdentity(String userIdentity) {
            this.userIdentity = userIdentity;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(long loginTime) {
            this.loginTime = loginTime;
        }

        public List<?> getTagList() {
            return tagList;
        }

        public void setTagList(List<?> tagList) {
            this.tagList = tagList;
        }
    }
}

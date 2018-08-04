package com.zkhz.a3rdlibsdemo.rongyun.messageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.rongyun.DataInterface;
import com.zkhz.a3rdlibsdemo.rongyun.message.ChatroomAdminRemove;

import io.rong.imlib.model.MessageContent;


/**
 * Created by duanliuyi on 2018/6/20.
 */

public class AdminRemoveView extends BaseMsgView {

    private TextView tvInfo;

    public AdminRemoveView(Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.msg_system_view, this);
        tvInfo = (TextView) view.findViewById(R.id.tv_system_info);
    }

    @Override
    public void setContent(MessageContent msgContent, String senderUserId) {
        if (msgContent instanceof ChatroomAdminRemove) {
            String id = ((ChatroomAdminRemove) msgContent).getId();
            String name = "";
            if (DataInterface.getUserInfo(id) != null) {
                name = DataInterface.getUserInfo(id).getName();
            } else {
                name = id;
            }
            tvInfo.setText("系统通知  " + name + "聊天室管理员身份被取消");
        }

    }
}

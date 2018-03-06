/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.message;

import sdk.coolq.api.IMessage;
import sdk.coolq.message.reply.GroupMessageReply;
import sdk.event.syncro.GroupMessageEvent;

/**
 * 群消息
 *
 * @author zyp
 */
public class GroupMessage extends IMessage<GroupMessageEvent, GroupMessageReply> {

    /**
     * string	"normal"、"anonymous"、"notice"	消息子类型，正常消息是 "normal"，匿名消息是
     * "anonymous"，系统提示（如「管理员已禁止群内匿名聊天」）是 "notice"
     */
    public String sub_type;//

    /**
     * number	-	群号
     */
    public long group_id;//
    /**
     * string	-	匿名用户显示名
     */
    public String anonymous;//
    /**
     * string	-	匿名用户 flag，在调用禁言 API 时需要传入
     */
    public String anonymous_flag;//

    @Override
    public Class<GroupMessageEvent> getEventClass() {
        return GroupMessageEvent.class;
    }
}

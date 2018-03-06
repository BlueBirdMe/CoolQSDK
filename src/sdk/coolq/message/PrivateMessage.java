/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.message;

import sdk.coolq.api.IMessage;
import sdk.coolq.message.reply.PrivateMessageReply;
import sdk.event.syncro.PrivateMessageEvent;

/**
 * 私聊消息
 *
 * @author zyp
 */
public class PrivateMessage extends IMessage<PrivateMessageEvent, PrivateMessageReply> {

    /**
     * "friend"、"group"、"discuss"、"other"	消息子类型，如果是好友则是
     * "friend"，如果从群或讨论组来的临时会话则分别是 "group"、"discuss"
     */
    public String sub_type;//

    @Override
    public Class<PrivateMessageEvent> getEventClass() {
        return PrivateMessageEvent.class;
    }
}

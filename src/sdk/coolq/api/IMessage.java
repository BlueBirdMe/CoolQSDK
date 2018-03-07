/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.coolq.message.DiscussMessage;
import sdk.coolq.message.GroupMessage;
import sdk.coolq.message.PrivateMessage;
import sdk.event.api.RepliableEvent;
import sdk.util.bean.FieldSelector;

/**
 *
 * @author zyp
 * @param <E>
 * @param <R>
 */
public abstract class IMessage<E extends RepliableEvent, R extends IReply> extends RepliableReport<E, R> {

    /**
     * number	-	发送者 QQ 号
     */
    public long user_id;//
    /**
     * number	-	消息 ID
     */
    public int message_id;//
    /**
     * string	"group"	消息类型
     */
    @FieldSelector(value = {"private", "group", "discuss"}, clazz = {PrivateMessage.class, GroupMessage.class, DiscussMessage.class})
    public String message_type;//
    /**
     * string/array	-	消息内容
     */
    public String message;//
    /**
     * number	-	字体
     */
    public int font;//
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.coolq.request.NewFriendApply;
import sdk.coolq.request.NewGroupMemberApply;
import sdk.event.RepliableEvent;
import sdk.util.bean.FieldSelector;

/**
 * 加好友请求
 *
 * @author zyp
 * @param <E>
 * @param <R>
 * @param <T>
 */
public abstract class IRequest<E extends RepliableEvent, R extends IReply> extends RepliableReport<E, R> {
    
    /**
     * string	请求类型
     */
    @FieldSelector(
            value = {"friend", "group"},
            clazz = {NewFriendApply.class, NewGroupMemberApply.class})
    public String request_type;//
    /**
     * number	-	发送请求的 QQ 号
     */
    public long user_id;//
    /**
     * string	-	验证信息
     */
    public String message;//
    /**
     * string	-	请求 flag，在调用处理请求的 API 时需要传入
     */
    public String flag;//
}

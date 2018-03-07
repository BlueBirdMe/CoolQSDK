/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.coolq.request.NewGroupInviting;
import sdk.coolq.request.NewGroupMemberApply;
import sdk.event.api.RepliableEvent;
import sdk.util.bean.FieldSelector;

/**
 *
 * @author zyp
 * @param <E>
 * @param <R>
 */
public abstract class GroupRequest<E extends RepliableEvent, R extends IReply> extends IRequest<E, R> {

    /**
     * string	"add"、"invite"	请求子类型，分别表示加群请求、邀请登录号入群
     */
    @FieldSelector(clazz = {NewGroupMemberApply.class, NewGroupInviting.class}, value = {"add", "invite"})
    public String sub_type;//
    /**
     * number	-	群号
     */
    public long group_id;//
}

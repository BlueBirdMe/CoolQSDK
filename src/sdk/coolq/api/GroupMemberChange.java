/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.coolq.event.GroupMemberDecrease;
import sdk.coolq.event.KickedFromGroup;
import sdk.event.api.AsynchroEvent;
import sdk.util.bean.FieldSelector;

/**
 *
 * @author zyp
 * @param <E>
 */
public abstract class GroupMemberChange<E extends AsynchroEvent> extends IGroupEvent<E> {

    /**
     * number	-	操作者 QQ 号（如果是主动退群，则和 user_id 相同）
     */
    public long operator_id;//
    /**
     * "leave"、"kick"、"kick_me"	事件子类型，分别表示主动退群、成员被踢、登录号被踢
     */
    @FieldSelector(value = {"leave", "kick","kick_me"}, clazz = {GroupMemberDecrease.class, GroupMemberDecrease.class,KickedFromGroup.class})
    public String sub_type;//
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.coolq.event.GroupFileUpload;
import sdk.coolq.event.GroupManagerChange;
import sdk.coolq.event.GroupMemberIncrease;
import sdk.coolq.event.NewFriend;
import sdk.event.AsynchroEvent;
import sdk.event.CoolQEvent;
import sdk.event.CoolQEventManager;
import sdk.util.bean.FieldSelector;

/**
 *
 * @author zyp
 * @param <E>
 * @param <R>
 */
public abstract class IEvent<E extends AsynchroEvent> extends IReport<E> {

    /**
     * "group_admin"	群管理员变动
     * "group_decrease"	群成员减少
     * "group_increase" 群成员增加
     */
    @FieldSelector(
            value = {"group_upload", "group_admin", "group_decrease", "group_increase", "friend_add"},
            clazz = {GroupFileUpload.class, GroupManagerChange.class, GroupMemberChange.class, GroupMemberIncrease.class, NewFriend.class})
    public String event;//

    @Override
    public void fire() throws Exception {
        E e = (E) CoolQEvent.getConstructor(getEventClass(), getClass()).newInstance(this);
        CoolQEventManager.getInstance().callAsynchroEvent(e);
    }
}

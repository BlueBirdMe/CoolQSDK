/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.IGroupEvent;
import sdk.event.asynchro.GroupMemberIncreaseEvent;

/**
 *
 * @author zyp
 */
public class GroupMemberIncrease extends IGroupEvent<GroupMemberIncreaseEvent> {

    /**
     * number	-	操作者 QQ 号（如果是主动退群，则和 user_id 相同）
     */
    public long operator_id;//
    /**
     * "approve"、"invite"	事件子类型，分别表示管理员已同意入群、管理员邀请入群
     */
    public String sub_type;//

    @Override
    public Class<GroupMemberIncreaseEvent> getEventClass() {
        return GroupMemberIncreaseEvent.class;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.GroupMemberChange;
import sdk.event.asynchro.GroupMemberDecreaseEvent;

/**
 * 玩家离开群,被踢或主动离开
 *
 * @author zyp
 */
public class GroupMemberDecrease extends GroupMemberChange<GroupMemberDecreaseEvent> {

    @Override
    public Class<GroupMemberDecreaseEvent> getEventClass() {
        return GroupMemberDecreaseEvent.class;
    }
}

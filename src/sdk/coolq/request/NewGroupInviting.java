/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request;

import sdk.coolq.api.GroupRequest;
import sdk.coolq.request.reply.NewGroupMemberReply;
import sdk.event.syncro.GroupInvitingEvent;

/**
 *
 * @author zyp
 */
public class NewGroupInviting extends GroupRequest<GroupInvitingEvent, NewGroupMemberReply> {

    @Override
    public Class<GroupInvitingEvent> getEventClass() {
        return GroupInvitingEvent.class;
    }
}

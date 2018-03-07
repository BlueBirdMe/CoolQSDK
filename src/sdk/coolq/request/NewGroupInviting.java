/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request;

import sdk.coolq.api.GroupRequest;
import sdk.coolq.request.reply.NewGroupInvitingReply;
import sdk.event.syncro.NewGroupInvitingEvent;

/**
 *
 * @author zyp
 */
public class NewGroupInviting extends GroupRequest<NewGroupInvitingEvent, NewGroupInvitingReply> {

    @Override
    public Class<NewGroupInvitingEvent> getEventClass() {
        return NewGroupInvitingEvent.class;
    }
}

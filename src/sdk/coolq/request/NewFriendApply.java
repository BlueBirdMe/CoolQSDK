/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request;

import sdk.coolq.api.IRequest;
import sdk.coolq.request.reply.NewFriendApplyReply;
import sdk.event.syncro.NewFriendApplyEvent;

/**
 * 加好友请求
 *
 * @author zyp
 */
public class NewFriendApply extends IRequest<NewFriendApplyEvent, NewFriendApplyReply> {

    @Override
    public Class<NewFriendApplyEvent> getEventClass() {
        return NewFriendApplyEvent.class;
    }
}

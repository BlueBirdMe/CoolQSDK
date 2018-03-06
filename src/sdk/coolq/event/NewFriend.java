/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.IEvent;
import sdk.event.asynchro.NewFriendEvent;

/**
 * 好友添加成功
 *
 * @author zyp
 */
public class NewFriend extends IEvent<NewFriendEvent> {

    /**
     * number	-	新添加好友 QQ 号
     */
    public long user_id;//

    @Override
    public Class<NewFriendEvent> getEventClass() {
        return NewFriendEvent.class;
    }
}

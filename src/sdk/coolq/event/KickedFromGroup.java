/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.GroupMemberChange;
import sdk.event.asynchro.KickedFromGroupEvent;

/**
 * 被踢群
 *
 * @author zyp
 */
public class KickedFromGroup extends GroupMemberChange<KickedFromGroupEvent> {

    @Override
    public Class<KickedFromGroupEvent> getEventClass() {
        return KickedFromGroupEvent.class;
    }
}

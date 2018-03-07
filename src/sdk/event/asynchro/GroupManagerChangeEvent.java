/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.asynchro;

import sdk.coolq.event.GroupManagerChange;
import sdk.event.api.AsynchroEvent;
import sdk.event.CoolQHandlerList;

/**
 *
 * @author zyp
 */
public class GroupManagerChangeEvent extends AsynchroEvent<GroupManagerChange> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public GroupManagerChangeEvent(GroupManagerChange report) {
        super(report);
    }

    @Override
    public CoolQHandlerList getHandlers() {
        return handlers;
    }

    public static CoolQHandlerList getHandlerList() {
        return handlers;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.request.NewGroupMemberApply;
import sdk.coolq.request.reply.NewGroupMemberReply;
import sdk.event.CoolQHandlerList;
import sdk.event.RepliableEvent;

/**
 *
 * @author zyp
 */
public class NewGroupMemberApplyEvent extends RepliableEvent<NewGroupMemberApply, NewGroupMemberReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public NewGroupMemberApplyEvent(NewGroupMemberApply apply) {
        super(apply);
    }

    /**
     * 同意
     *
     * @param remark 备注
     */
    public void approve() {
        reply = new NewGroupMemberReply();
        reply.approve = true;
        setCancelled(true);
    }

    /**
     * 拒绝请求
     *
     * @param reason
     */
    public void refuse(String reason) {
        reply = new NewGroupMemberReply();
        reply.approve = false;
        reply.reason = reason;
        setCancelled(true);
    }

    @Override
    public CoolQHandlerList getHandlers() {
        return handlers;
    }

    public static CoolQHandlerList getHandlerList() {
        return handlers;
    }
}

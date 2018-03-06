/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.request.*;
import sdk.coolq.request.reply.NewFriendApplyReply;
import sdk.event.*;

/**
 * 加好友请求
 *
 * @author zyp
 * @param <T>
 */
public class NewFriendApplyEvent extends RepliableEvent<NewFriendApply, NewFriendApplyReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public NewFriendApplyEvent(NewFriendApply apply) {
        super(apply);
    }

    /**
     * 同意加好友
     *
     * @param remark 备注
     */
    public void approve(String remark) {
        reply = new NewFriendApplyReply();
        reply.approve = true;
        reply.remark = remark;
        setCancelled(true);
    }

    /**
     * 拒绝请求
     *
     */
    public void refuse() {
        reply = new NewFriendApplyReply();
        reply.approve = false;
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

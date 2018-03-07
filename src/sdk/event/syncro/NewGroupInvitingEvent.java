/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.request.NewGroupInviting;
import sdk.coolq.request.reply.NewGroupInvitingReply;
import sdk.event.CoolQHandlerList;
import sdk.event.api.RepliableEvent;

/**
 * 被邀请入群事件,不知何时触发= =??
 *
 * @author zyp
 */
public class NewGroupInvitingEvent extends RepliableEvent<NewGroupInviting, NewGroupInvitingReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public NewGroupInvitingEvent(NewGroupInviting apply) {
        super(apply);
    }

    /**
     * 获取邀请者QQ
     *
     * @return
     */
    public long getInviter() {
        return getHandle().user_id;
    }

    /**
     * 获取被邀请入的群
     *
     * @return
     */
    public long getGroup() {
        return getHandle().group_id;
    }

    /**
     * 同意
     *
     * @param remark 备注
     */
    public void approve() {
        reply = new NewGroupInvitingReply();
        reply.approve = true;
        setCancelled(true);
    }

    /**
     * 拒绝请求
     *
     * @param reason
     */
    public void refuse(String reason) {
        reply = new NewGroupInvitingReply();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.request.NewGroupMemberApply;
import sdk.coolq.request.reply.NewGroupMemberReply;
import sdk.event.CoolQHandlerList;
import sdk.event.api.RepliableEvent;

/**
 * 有人请求入群事件???
 * @author zyp
 */
public class NewGroupMemberApplyEvent extends RepliableEvent<NewGroupMemberApply, NewGroupMemberReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public NewGroupMemberApplyEvent(NewGroupMemberApply apply) {
        super(apply);
    }
    
    /**
     * 获取想加群的QQ
     * @return 
     */
    public long getRequester(){
        return getHandle().user_id;
    }
    
    /**
     * 获取请求者申请加入的QQ群
     * @return 
     */
    public long getGroup(){
        return getHandle().group_id;
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

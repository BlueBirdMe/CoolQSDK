/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.message.PrivateMessage;
import sdk.coolq.message.reply.PrivateMessageReply;
import sdk.event.CoolQHandlerList;
import sdk.event.RepliableEvent;

/**
 * 私聊消息事件
 *
 * @author zyp
 */
public class PrivateMessageEvent extends RepliableEvent<PrivateMessage, PrivateMessageReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public PrivateMessageEvent(PrivateMessage report) {
        super(report);
    }

    /**
     * 回复消息,并自动中断广播
     *
     * @param message
     * @param at_sender 是否@用户
     */
    public void replyMessage(String message) {
        reply = new PrivateMessageReply();
        reply.reply = message;
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

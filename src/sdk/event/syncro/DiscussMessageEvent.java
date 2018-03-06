/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.message.DiscussMessage;
import sdk.coolq.message.reply.DiscussMessageReply;
import sdk.event.CoolQHandlerList;
import sdk.event.RepliableEvent;

/**
 *
 * @author zyp
 */
public class DiscussMessageEvent extends RepliableEvent<DiscussMessage, DiscussMessageReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public DiscussMessageEvent(DiscussMessage report) {
        super(report);
    }

    /**
     * 回复消息,并自动中断广播
     *
     * @param message
     * @param at_sender 是否@用户
     */
    public void replyMessage(String message, boolean at_sender) {
        reply = new DiscussMessageReply();
        reply.reply = message;
        reply.at_sender = at_sender;
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

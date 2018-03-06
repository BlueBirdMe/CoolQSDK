/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.syncro;

import sdk.coolq.message.GroupMessage;
import sdk.coolq.message.reply.GroupMessageReply;
import sdk.event.CoolQHandlerList;
import sdk.event.RepliableEvent;

/**
 *
 * @author zyp
 */
public class GroupMessageEvent extends RepliableEvent<GroupMessage, GroupMessageReply> {

    private static final CoolQHandlerList handlers = new CoolQHandlerList();

    public GroupMessageEvent(GroupMessage report) {
        super(report);
    }

    /**
     * 踢群,并中断广播
     */
    public void kickSender() {
        reply = new GroupMessageReply();
        reply.kick = true;
        setCancelled(true);
    }

    /**
     * 禁言30分钟,并中断广播
     * 需要其他时间请调用API
     */
    public void mute30Minute() {
        reply = new GroupMessageReply();
        reply.ban = true;
        setCancelled(true);
    }

    /**
     * 撤回消息,并中断广播
     */
    public void deleteMessage() {
        reply = new GroupMessageReply();
        reply.delete = true;
        setCancelled(true);
    }

    /**
     * 回复消息,并自动中断广播
     *
     * @param message
     * @param at_sender 是否@用户
     */
    public void replyMessage(String message, boolean at_sender) {
        reply = new GroupMessageReply();
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

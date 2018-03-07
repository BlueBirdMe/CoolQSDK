/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.api;

import sdk.coolq.api.IReply;
import sdk.coolq.api.IReport;
import sdk.event.CoolQCancellable;
import sdk.event.CoolQEvent;

/**
 *
 * @author zyp
 * @param <H>
 * @param <R>
 * @param <T>
 */
public abstract class RepliableEvent<H extends IReport, R extends IReply> extends CoolQEvent<H> implements CoolQCancellable {

    /**
     * 时间广播完成后,如果非空,则发送回执消息
     */
    protected R reply;

    /**
     * 为true时中断广播
     */
    private boolean cancle = false;

    public RepliableEvent(H report) {
        super(report);
    }

    public R getReply() {
        return reply;
    }

    @Override
    public boolean isCancelled() {
        return cancle;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancle = cancel;
    }

    /**
     * 当回执存在时,返回true
     *
     * @return
     */
    public boolean isReplied() {
        return reply != null;
    }

    public void setReply(R reply) {
        this.reply = reply;
    }

}

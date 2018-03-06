/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.event.CoolQEvent;
import sdk.util.bean.FieldSelector;

/**
 * 上报的Beans
 *
 * @author zyp
 * @param <E>
 * @param <R>
 * @param <T>
 */
public abstract class IReport<E extends CoolQEvent> {

    @FieldSelector(value = {"message", "event", "request"}, clazz = {IMessage.class, IEvent.class, IRequest.class})
    public String post_type;//上报类型,1.message:收到消息,2.event:群、讨论组变动等非消息类事件,3.request:加好友请求、加群请求／邀请
    public long time;//上报的时间戳
    public long self_id;//机器人的QQ号

    public abstract Class<E> getEventClass();

    public Object getReply() {
        return null;
    }
    
    /**
     * 需要处理请求的话,覆盖此方法
     */
    public void handle() {
    }

    /**
     * 异步事件返回null,
     * 同步时间有回执时返回回执,否则返回null
     *
     * @return
     */
    public abstract void fire() throws Throwable;
}

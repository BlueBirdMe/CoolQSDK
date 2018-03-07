/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event.api;

import sdk.coolq.api.IMessage;

/**
 *
 * @author zyp
 */
public interface IMessageEvent<H extends IMessage> {

    /**
     * 返回上报数据
     *
     * @return
     */
    public H getHandle();

    /**
     * 获取发送者QQ
     *
     * @return
     */
    public default long getSenderId() {
        return getHandle().user_id;
    }

    /**
     * 获取发送的消息
     *
     * @return
     */
    public default String getMessage() {
        return getHandle().message;
    }

    /**
     * 获取发送者使用的字体
     *
     * @return
     */
    public default int getFont() {
        return getHandle().font;
    }
}

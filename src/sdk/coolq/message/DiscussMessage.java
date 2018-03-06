/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.message;

import sdk.coolq.api.IMessage;
import sdk.coolq.message.reply.DiscussMessageReply;
import sdk.event.syncro.DiscussMessageEvent;

/**
 * 讨论组
 *
 * @author zyp
 */
public class DiscussMessage extends IMessage<DiscussMessageEvent,DiscussMessageReply> {

    /**
     * number	-	讨论组 ID
     */
    public long discuss_id;//

    @Override
    public Class<DiscussMessageEvent> getEventClass() {
        return DiscussMessageEvent.class;
    }
}

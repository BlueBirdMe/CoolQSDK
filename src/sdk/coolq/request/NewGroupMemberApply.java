/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request;

import sdk.coolq.api.IRequest;
import sdk.coolq.request.reply.NewGroupMemberReply;
import sdk.event.syncro.NewGroupMemberApplyEvent;

/**
 * 加群请求
 *
 * @author zyp
 */
public class NewGroupMemberApply extends IRequest<NewGroupMemberApplyEvent, NewGroupMemberReply> {

    /**
     * string	"add"、"invite"	请求子类型，分别表示加群请求、邀请登录号入群
     */
    public String sub_type;//
    /**
     * number	-	群号
     */
    public long group_id;//

    @Override
    public Class<NewGroupMemberApplyEvent> getEventClass() {
        return NewGroupMemberApplyEvent.class;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request.reply;

import sdk.coolq.api.IReply;

/**
 *
 * @author zyp
 */
public class NewGroupMemberReply implements IReply{

    /**
     * 是否同意请求／邀请
     */
    public boolean approve;
    /**
     * 拒绝理由（仅在拒绝时有效）
     */
    public String reason;
}

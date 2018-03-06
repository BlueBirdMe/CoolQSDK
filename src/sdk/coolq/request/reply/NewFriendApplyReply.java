/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.request.reply;

import sdk.coolq.api.IReply;

/**
 * 加好友回执
 * @author zyp
 */
public class NewFriendApplyReply implements IReply{

    /**
     * boolean	是否同意请求
     */
    public boolean approve;
    /**
     * string	添加后的好友备注（仅在同意时有效）
     */
    public String remark;
}

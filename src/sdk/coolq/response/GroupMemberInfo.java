/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.response;


/**
 *
 * @author zyp
 */
public class GroupMemberInfo  {

    /**
     * number	群号
     */
    public long group_id;//	
    /**
     * number	QQ 号
     */
    public long user_id;//
    /**
     * string	昵称
     */
    public String nickname;//
    /**
     * string	群名片／备注
     */
    public String card;//
    /**
     * string	性别，male 或 female 或 unknown
     */
    public String sex;//
    /**
     * number;	年龄
     */
    public int age;//
    /**
     * string	地区
     */
    public String area;//
    /**
     * number	加群时间戳
     */
    public long join_time;//
    /**
     * number	最后发言时间戳
     */
    public long last_sent_time;//
    /**
     * string	成员等级
     */
    public String level;//
    /**
     * string	角色，owner 或 admin 或 member
     */
    public String role;//
    /**
     * bool	是否不良记录成员
     */
    public boolean unfriendly;//
    /**
     * string	专属头衔
     */
    public String title;//
    /**
     * number	专属头衔过期时间戳
     */
    public long title_expire_time;//
    /**
     * bool	是否允许修改群名片
     */
    public boolean card_changeable;//
    
}

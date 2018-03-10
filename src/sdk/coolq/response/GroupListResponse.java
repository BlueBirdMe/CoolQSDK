/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.response;

import java.util.List;
import sdk.util.bean.ComponentType;

/**
 *
 * @author zyp
 */
public class GroupListResponse extends Response{
    @ComponentType(clazz = GroupInfo.class)
    public List<GroupInfo> data;
}

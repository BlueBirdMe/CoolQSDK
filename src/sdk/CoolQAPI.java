/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk;

import com.xiaoleilu.hutool.http.HttpUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import static sdk.SDKConfig.coolQURL;
import static sdk.SDKConstant.*;
import sdk.coolq.api.CoolQAPIFailException;
import sdk.coolq.response.GroupListResponse;
import sdk.coolq.response.GroupMemberInfo;
import sdk.coolq.response.GroupMemberInfoResponse;
import sdk.coolq.response.GroupMemberListResponse;
import sdk.coolq.response.Response;
import sdk.coolq.response.Status;
import sdk.coolq.response.VersionInfo;
import sdk.coolq.response.VersionResponse;
import sdk.util.API;
import sdk.util.bean.BeanUtils;

/**
 *
 * @author zyp
 */
public class CoolQAPI {

    private static String getStatus(String status, int retcode) {
        String re = "ok";
        if (retcode != 0) {//请求失败
            re = Status.getStatus(status, retcode);
            if (re == null) {
                re = "请求失败,未知retcode:" + retcode;
            }
        }
        return re;
    }

    private static void command(String action, Object[] data) throws CoolQAPIFailException {
        StringBuilder sb = new StringBuilder();
        if (data != null) {
            if (data.length % 2 != 0) {
                throw new RuntimeException("参数必须成对存在");
            }
            if (data.length > 0) {
                sb.append("?");
                try {
                    sb.append(URLEncoder.encode(data[0].toString(), SDKConfig.UTF_8.name())).append("=").append(URLEncoder.encode(data[1].toString(), SDKConfig.UTF_8.name()));
                    if (data.length > 1) {
                        for (int i = 3; i < data.length; i += 2) {
                            sb.append("&").append(URLEncoder.encode(data[i - 1].toString(), SDKConfig.UTF_8.name())).append("=").append(URLEncoder.encode(data[i].toString(), SDKConfig.UTF_8.name()));
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        String re = HttpUtil.get(coolQURL + action + sb.toString(), TIME_OUT);
        JSONObject json = new JSONObject(re);
        int retcode = json.getInt("retcode");
        String status = json.getString("status");
        assertSuccess(status, retcode);
    }

    /**
     * 发送私聊消息
     *
     * @param user_id
     * @param qq QQ号
     * @param message 消息
     *
     * @return
     */
    public static void sendPrivateMsg(long user_id, String message) throws CoolQAPIFailException {
        command(SEND_PRIVATE_MSG, new Object[]{"user_id", user_id, "message", message});
    }

    /**
     * 发送群聊消息
     *
     * @param group_id
     * @param groupId 群ID
     * @param message 消息
     *
     * @return
     */
    public static void sendGroupMsg(long group_id, String message) throws CoolQAPIFailException {
        command(SEND_GROUP_MSG, new Object[]{"group_id", group_id, "message", message});
    }

    /**
     * @param group_id
     * @param user_id
     *
     * @用户并发送群聊消息
     * @param message 消息
     *
     * @return
     */
    public static void sendGroupMsg(long group_id, long user_id, String message) throws CoolQAPIFailException {
        command(SEND_GROUP_MSG, new Object[]{"group_id", group_id, "message", API.getAtCode(user_id) + message});
    }

    /**
     * 发送讨论组消息
     *
     * @param discuss_id
     * @param groupId 讨论组ID
     * @param message 消息
     *
     * @return
     */
    public static void sendDisCussMsg(long discuss_id, String message) throws CoolQAPIFailException {
        command(SEND_DISCUSS_MSG, new Object[]{"discuss_id", discuss_id, "message", message});
    }

    /**
     * 发送讨论组消息
     *
     * @param discuss_id
     * @param user_id
     * @param message 消息
     *
     * @throws sdk.coolq.api.CoolQAPIFailException
     */
    public static void sendDisCussMsg(long discuss_id, long user_id, String message) throws CoolQAPIFailException {
        command(SEND_DISCUSS_MSG, new Object[]{"discuss_id", discuss_id, "message", API.getAtCode(user_id) + message});
    }

    /**
     * 撤回消息
     *
     * @param message_id
     * @param messageId 消息ID
     *
     * @return
     */
    public static void deleteMsg(long message_id) throws CoolQAPIFailException {
        command(DELETE_MSG, new Object[]{"message_id", message_id});
    }

    /**
     * 发送好友赞
     *
     * @param user_id
     * @param qq QQ号
     * @param times 赞的次数，每个好友每天最多 10 次
     *
     * @return
     */
    public static void sendLike(long user_id, long times) throws CoolQAPIFailException {
        command(SEND_LIKE, new Object[]{"user_id", user_id, "times", times});
    }

    /**
     * 群组踢人
     *
     * @param user_id
     * @param group_id
     * @param qq QQ
     * @param groupId 群号
     *
     * @return
     */
    public static void setGroupKick(long user_id, long group_id) throws CoolQAPIFailException {
        command(SET_GROUP_KICK, new Object[]{"user_id", user_id, "group_id", group_id});
    }

    /**
     * 群组单人禁言
     *
     * @param user_id
     * @param group_id
     * @param qq QQ
     * @param groupId 群号
     * @param duration 禁言时长，单位秒，0 表示取消禁言
     *
     * @return
     */
    public static void setGroupBan(long user_id, long group_id, long duration) throws CoolQAPIFailException {
        command(SET_GROUP_BAN, new Object[]{"user_id", user_id, "group_id", group_id, "duration", duration});
    }

    /**
     * 群组匿名用户禁言
     *
     * @param flag 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param group_id
     * @param groupId 群号
     * @param duration 禁言时长，单位秒，无法取消匿名用户禁言
     *
     * @return
     */
    public static void setGroupAnonymousBan(String flag, long group_id, long duration) throws CoolQAPIFailException {
        command(SET_GROUP_ANONYMOUS_BAN, new Object[]{"flag", flag, "group_id", group_id, "duration", duration});
    }

    /**
     * 群组全员禁言
     *
     * @param group_id
     * @param groupId 群号
     * @param enable 是否禁言
     *
     * @return
     */
    public static void setGroupWholeBan(long group_id, boolean enable) throws CoolQAPIFailException {
        command(SET_GROUP_WHOLE_BAN, new Object[]{"enable", enable, "group_id", group_id});
    }

    /**
     * 获取群列表
     *
     * @return
     */
    public static List<Long> getGroupList() throws CoolQAPIFailException {
        String resp = HttpUtil.get(coolQURL + GET_GROUP_LIST, SDKConstant.TIME_OUT);
        GroupListResponse response = buildResponse(GroupListResponse.class, resp);
        assertSuccess(response);
        ArrayList<Long> re = new ArrayList();
        response.data.forEach(p -> re.add(p.group_id));
        return re;
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param qq QQ 号（不可以是登录号）
     *
     * @return
     */
    public static GroupMemberInfo getGroupMemberInfo(long group_id, long user_id) throws CoolQAPIFailException {
        String resp = HttpUtil.get(coolQURL + GET_GROUP_MEMBER_INFO + "?group_id=" + group_id + "&user_id=" + user_id + "&no_cache=true", SDKConstant.TIME_OUT);
        GroupMemberInfoResponse response;
        response = buildResponse(GroupMemberInfoResponse.class, resp);
        assertSuccess(response);
        return response.data;
    }

    /**
     * 获取群成员列表
     *
     * @param groupId 群号
     *
     * @return
     */
    public static List<Long> getGroupMemberList(long group_id) throws CoolQAPIFailException {
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", group_id);
        String resp = HttpUtil.get(coolQURL + GET_GROUP_MEMBER_LIST, map);
        GroupMemberListResponse response = buildResponse(GroupMemberListResponse.class, resp);
        assertSuccess(response);
        ArrayList<Long> re = new ArrayList();
        response.data.forEach(p -> re.add(p.user_id));
        return re;
    }

    /**
     * 获取酷 Q 及 HTTP API 插件的版本信息
     *
     * @return
     */
    public static VersionInfo getVersionInfo() throws CoolQAPIFailException {
        String resp = HttpUtil.get(coolQURL + GET_VERSION_INFO, TIME_OUT);
        VersionResponse response = buildResponse(VersionResponse.class, resp);
        assertSuccess(response);
        return response.data;
    }

    /**
     * 重启酷 Q，并以当前登录号自动登录（需勾选快速登录）
     *
     * @return
     */
    public static void setRestart() throws CoolQAPIFailException {
        command(SET_RESTART, null);

    }

    /**
     * 重启 HTTP API 插件
     *
     * @return
     */
    public static void setRestartPlugin() throws CoolQAPIFailException {
        command(SET_RESTART_PLUGIN, null);
    }

    private static void assertSuccess(String status, int retcode) throws CoolQAPIFailException {
        if (!status.equals("ok")) {
            throw new CoolQAPIFailException(getStatus(status, retcode));
        }
    }

    private static void assertSuccess(Response r) throws CoolQAPIFailException {
        assertSuccess(r.status, r.retcode);
    }

    private static <T> T buildResponse(Class<T> clazz, String message) throws CoolQAPIFailException {
        T response;
        try {
            response = BeanUtils.create(clazz, new JSONObject(message));
        } catch (Exception ex) {
            SDK.log("消息解析失败:"+message);
            throw new CoolQAPIFailException(ex);
        }
        return response;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.response;

import java.util.HashMap;

/**
 *
 * @author zyp
 */
public class Status {

    private static final HashMap<Integer, String> retcodes = new HashMap();

    /**
     * 成功返回ok,否则返回描述信息
     *
     * @param r
     *
     * @return
     */
    public static String getStatus(String status, int retcode) {
        if (status.equals("ok")) {
            return "ok";
        } else {
            if (retcode == 0) {
                return "请求失败,status=" + status + ",但retcode=0";
            } else {
                String s = retcodes.get(retcode);
                if (s == null) {
                    s = "请求失败,未知状态码,status=" + status;
                }
                return s;
            }
        }
    }

    static {
        retcodes.put(0, "操作成功");
        retcodes.put(1, "同时 status 为 async，表示操作已进入异步执行，具体结果未知");
        retcodes.put(100, "参数缺失或参数无效，通常是因为没有传入必要参数，某些接口中也可能因为参数明显无效（比如传入的 QQ 号小于等于 0，此时无需调用酷 Q 函数即可确定失败），此项和以下的 status 均为 failed");
        retcodes.put(102, "酷 Q 函数返回的数据无效，一般是因为传入参数有效但没有权限，比如试图获取没有加入的群组的成员列表");
        retcodes.put(103, "操作失败，一般是因为用户权限不足，或文件系统异常、不符合预期");
        retcodes.put(201, "工作线程池未正确初始化（无法执行异步任务）");
        retcodes.put(-1, "请求发送失败");
        retcodes.put(-2, "未收到服务器回复，可能未发送成功");
        retcodes.put(-3, "消息过长或为空");
        retcodes.put(-4, "消息解析过程异常");
        retcodes.put(-5, "日志功能未启用");
        retcodes.put(-6, "日志优先级错误");
        retcodes.put(-7, "数据入库失败");
        retcodes.put(-8, "不支持对系统帐号操作");
        retcodes.put(-9, "帐号不在该群内，消息无法发送");
        retcodes.put(-10, "该用户不存在/不在群内");
        retcodes.put(-11, "数据错误，无法请求发送");
        retcodes.put(-12, "不支持对匿名成员解除禁言");
        retcodes.put(-13, "无法解析要禁言的匿名成员数据");
        retcodes.put(-14, "由于未知原因，操作失败");
        retcodes.put(-15, "群未开启匿名发言功能，或匿名帐号被禁言");
        retcodes.put(-16, "帐号不在群内或网络错误，无法退出/解散该群");
        retcodes.put(-17, "帐号为群主，无法退出该群");
        retcodes.put(-18, "帐号非群主，无法解散该群");
        retcodes.put(-19, "临时消息已失效或未建立");
        retcodes.put(-20, "参数错误");
        retcodes.put(-21, "临时消息已失效或未建立");
        retcodes.put(-22, "获取QQ信息失败");
        retcodes.put(-23, "找不到与目标QQ的关系，消息无法发送");
        retcodes.put(-101, "应用过大");
        retcodes.put(-102, "不是合法的应用");
        retcodes.put(-103, "不是合法的应用");
        retcodes.put(-104, "应用不存在公开的Information函数");
        retcodes.put(-105, "无法载入应用信息");
        retcodes.put(-106, "文件名与应用ID不同");
        retcodes.put(-107, "返回信息解析错误");
        retcodes.put(-108, "AppInfo返回的Api版本不支持直接加载，仅支持Api版本为9(及以上)的应用直接加载");
        retcodes.put(-109, "AppInfo返回的AppID错误");
        retcodes.put(-110, "缺失AppInfo返回的AppID对应的[Appid].json文件");
        retcodes.put(-111, "[Appid].json文件内的AppID与其文件名不同");
        retcodes.put(-120, "无Api授权接收函数(Initialize)");
        retcodes.put(-121, "Api授权接收函数(Initialize)返回值非0");
        retcodes.put(-122, "尝试恶意修改酷Q配置文件，将取消加载并关闭酷Q");
        retcodes.put(-150, "无法载入应用信息");
        retcodes.put(-151, "应用信息Json串解析失败，请检查Json串是否正确");
        retcodes.put(-152, "Api版本过旧或过新");
        retcodes.put(-153, "应用信息错误或存在缺失");
        retcodes.put(-154, "Appid不合法");
        retcodes.put(-160, "事件类型(Type)错误或缺失");
        retcodes.put(-161, "事件函数(Function)错误或缺失");
        retcodes.put(-162, "应用优先级不为10000、20000、30000、40000中的一个");
        retcodes.put(-163, "事件类型(Api)不支持应用Api版本");
        retcodes.put(-164, "应用Api版本大于8，但使用了新版本已停用的事件类型(Type)：1(好友消息)、3(临时消息),请阅读开发文档,升级至新版事件类型");
        retcodes.put(-165, "事件类型为2(群消息)、4(讨论组消息)、21(私聊消息)，但缺少正则表达式(regex)的表达式部分(expression),请阅读开发文档,添加正则表达式");
        retcodes.put(-166, "存在为空的正则表达式(regex)的key");
        retcodes.put(-167, "存在为空的正则表达式(regex)的表达式部分(expression)");
        retcodes.put(-168, "应用事件(event)id参数不存在或为0");
        retcodes.put(-169, "应用事件(event)id参数有重复,重复值见日志");
        retcodes.put(-180, "应用状态(status)id参数不存在或为0");
        retcodes.put(-181, "应用状态(status)period参数不存在或设置错误");
        retcodes.put(-182, "应用状态(status)id参数有重复,重复值见日志");
        retcodes.put(-201, "无法载入应用，可能是应用文件已损坏");
        retcodes.put(-202, "Api版本过旧或过新");
        retcodes.put(-997, "应用未启用");
        retcodes.put(-998, "应用调用在Auth声明之外的 酷Q Api。见日志警告。在应用信息json中添加相应的auth，授予应用该Api的调用权限。");
    }
}

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
public class VersionInfo {
    public String coolq_directory;//	string	酷 Q 根目录路径
    public String coolq_edition;//	string	酷 Q 版本，air 或 pro
    public String plugin_version;//	string	HTTP API 插件版本，例如 2.1.3
    public String plugin_build_number;//	number	HTTP API 插件 build 号
    public String plugin_build_configuration;//	string	HTTP API 插件编译配置，debug 或 release
}

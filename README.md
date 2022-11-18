# FetchIPInfo-Chinese
## 本Java项目旨在实时监控apache2的access.log文件，获取IP访问的地理位置，并将其实时转写为简体中文输出。
适用系统：Ubuntu<br />
默认输出路径`/var/log/apache2/access.log.Chinese`<br />
本项目使用java17构建，使用低版本java可能会有兼容性问题。<br />
本项目用到的第三方类库：
* org.apache.commons.io:  commons-io-2.11.0
* net.sf.json:  json-lib-2.4

感谢码农实验室 www.fkcoder.com 提供的免费IP查询API。

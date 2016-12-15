package net.wendal.nutzbook;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by y on 2016/10/13.
 */
@IocBy(type=ComboIocProvider.class, args={"*js", "./conf/ioc/",
        "*anno", "net.wendal.nutzbook",
        "*tx"})
@SetupBy(value = MainSetup.class)
@Modules(scanPackage = true)
@Fail("jsp:jsp.500") //入口方法或适配器或其他原因导致异常时需要走的视图,依然是打开MainModule,加入代码,含义就是内部重定向到/WEB-INF/jsp/500.jsp页面
@Ok("json:full") //这个项目以json交互为主,所以,默认用json视图好了. 打开MainModule,加入代码,这里的json指UTF8JsonView类, 后面的full是JsonFormat的其中一种内置格式的缩写
@Localization(value="msg/", defaultLocalizationKey="zh-CN") //本地化,国际化. 在conf目录中,新建一个文件夹叫msg, 在里面再建一个文件文件夹zh-CN, 里面放一个空文件叫user.properties
public class MainModule {
}

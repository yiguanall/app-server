package com.crabar.common.startup;

import com.crabar.common.model.Answer;
import com.crabar.common.model.User;
import com.crabar.web.TaskController;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.spring.SpringPlugin;
import com.jfinal.render.ViewType;

/**
 * Created with IntelliJ IDEA.
 * User: wmh
 * Date: 14-7-29
 * Time: ÏÂÎç11:23
 */
public class CrabarConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes me) {
        me.add(new CrabarRouter());
    }

    @Override
    public void configPlugin(Plugins me) {
        loadPropertyFile("conf/crabar.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("mysql.url"), getProperty("mysql.user"), getProperty("mysql.pwd"));
        me.add(c3p0Plugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setContainerFactory(new HumpContainerFactory());
        me.add(arp);
        arp.addMapping("user", User.class);
        arp.addMapping("answer", Answer.class);

        me.add(new SpringPlugin("classpath*:/conf/context-crabar.xml"));

    }

    @Override
    public void configInterceptor(Interceptors me) {
    }

    @Override
    public void configHandler(Handlers me) {
    }
}

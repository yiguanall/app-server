package com.crabar.common.startup;

import com.crabar.web.IndexController;
import com.crabar.web.TaskController;
import com.jfinal.config.Routes;

/**
 * Created with IntelliJ IDEA.
 * User: wmh
 * Date: 14-7-29
 * Time: обнГ11:30
 */
public class CrabarRouter extends Routes {

    @Override
    public void config() {
        this.add("/", IndexController.class);
        this.add("/task", TaskController.class);
    }
}

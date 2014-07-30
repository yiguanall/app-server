package com.crabar.common.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;

/**
 * User: wmh
 * Date: 14-7-30
 * Time: ионГ7:30
 */
public class BaseController extends Controller {

    @Override
    public void renderJson(Object object) {
        super.renderJson(JSON.toJSONString(object));
    }

}

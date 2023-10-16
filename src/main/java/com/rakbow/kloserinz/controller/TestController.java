package com.rakbow.kloserinz.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rakbow.kloserinz.data.ApiResult;
import com.rakbow.kloserinz.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-16 22:13
 * @Description:
 */
@Controller
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping(path = "/get-sku-list", method = RequestMethod.POST)
    @ResponseBody
    public String getSKUList(@RequestBody JSONObject json) {
        ApiResult res = new ApiResult();
        try {

            res.data = testService.getSKUList();

        } catch (Exception e) {
            res.setErrorMessage(e);
        }
        return res.toJson();
    }

    // @RequestMapping(path = "/add-sku", method = RequestMethod.POST)
    // @ResponseBody
    // public String addSKU(@RequestBody JSONObject json) {
    //     ApiResult res = new ApiResult();
    //     try {
    //
    //         res.data = testService.addSKU();
    //
    //     } catch (Exception e) {
    //         res.setErrorMessage(e);
    //     }
    //     return res.toJson();
    // }

}

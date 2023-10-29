package com.rakbow.kloserinz.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rakbow.kloserinz.data.ApiResult;
import com.rakbow.kloserinz.data.MetaData;
import com.rakbow.kloserinz.data.QueryParams;
import com.rakbow.kloserinz.service.BasicDataService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-27 2:32
 * @Description:
 */
@Controller
@RequestMapping("/basic-data")
public class BasicDataController {

    @Resource
    private BasicDataService basicDataService;

    @RequestMapping(path = "/get-meta-data", method = RequestMethod.POST)
    @ResponseBody
    public String getMetaData() {
        ApiResult res = new ApiResult();
        try {
            res.data = MetaData.toJson();
        } catch (Exception e) {
            res.setErrorMessage(e);
        }
        return res.toJson();
    }

    @RequestMapping(path = "/get-sku", method = RequestMethod.POST)
    @ResponseBody
    public String getSKU(@RequestBody JSONObject json) {
        ApiResult res = new ApiResult();
        try {
            res = basicDataService.getSKU(new QueryParams(json));
        } catch (Exception e) {
            res.setErrorMessage(e);
        }
        return res.toJson();
    }

}

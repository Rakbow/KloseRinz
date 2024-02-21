package com.rakbow.kloserinz.controller;

import com.rakbow.kloserinz.data.common.ApiResult;
import com.rakbow.kloserinz.service.BasicDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rakbow
 * @since 2023-10-27 2:32
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basic-data")
public class BasicDataController {

    private final BasicDataService srv;

    @PostMapping("add-sku-category")
    public ApiResult addSKUCategory() {
        ApiResult res = new ApiResult();
        try{

        }catch (Exception e){
            res.fail(e);
        }
        return res;
    }

}

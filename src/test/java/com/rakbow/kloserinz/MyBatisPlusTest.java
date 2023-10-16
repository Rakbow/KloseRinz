package com.rakbow.kloserinz;

import com.rakbow.kloserinz.data.ApiResult;
import com.rakbow.kloserinz.entity.SKU;
import com.rakbow.kloserinz.service.TestService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 0:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KloseRinzApplication.class)
public class MyBatisPlusTest {

    @Resource
    private TestService testService;

    @Test
    public void CRUDTest() {

        SKU sku = new SKU();
        sku.setNameZh("测试商品1");
        sku.setNameEn("test product 1");
        sku.setCategoryId(0);
        sku.setCategoryName("默认");
        sku.setPriceCn(10);
        sku.setPriceWw(10);
        testService.addSKU(sku);
    }

}

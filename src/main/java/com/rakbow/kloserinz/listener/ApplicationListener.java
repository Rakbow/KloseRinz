package com.rakbow.kloserinz.listener;

import com.rakbow.kloserinz.service.BasicDataService;
import jakarta.annotation.Resource;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-28 4:08
 * @Description:
 */
@Component
public class ApplicationListener {

    @Resource
    private BasicDataService basicDataService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // 在应用启动后执行的代码
        basicDataService.loadMetaData();
    }

}

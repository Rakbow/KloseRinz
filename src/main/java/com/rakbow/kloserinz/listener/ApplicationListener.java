package com.rakbow.kloserinz.listener;

import com.rakbow.kloserinz.service.BasicDataService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Rakbow
 * @since 2023-10-28 4:08
 */
@Component
@RequiredArgsConstructor
public class ApplicationListener {

    private final BasicDataService srv;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // 在应用启动后执行的代码
        srv.loadMetaData();
    }

}

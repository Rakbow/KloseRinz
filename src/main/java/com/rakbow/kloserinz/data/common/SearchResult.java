package com.rakbow.kloserinz.data.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rakbow.kloserinz.util.DateUtil;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Rakbow
 * @since 2024/2/21 11:20
 */
@AllArgsConstructor
public class SearchResult<T> {

    public long total;//查询结果数
    public List<T> data;//查询结果数据
    public long page;
    public long pageSize;
    public String searchTime;

    public SearchResult(List<T> data, long total) {
        this.total = total;
        this.data = data;
        this.searchTime = DateUtil.nowStr();
    }

    public SearchResult(List<T> data, long total, long page, long pageSize) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.searchTime = DateUtil.nowStr();
    }

    public SearchResult(IPage<T> page) {
        this.total = page.getTotal();
        this.data = page.getRecords();
        this.page = page.getCurrent();
        this.pageSize = page.getSize();
        this.searchTime = DateUtil.nowStr();
    }

    public SearchResult() {
        total = 0;
        data = null;
        page = 0;
        pageSize = 10;
        searchTime = DateUtil.nowStr();
    }
}

package cn.finwood.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 分页查询请求参数DTO
 * created by haoyanbing on 2018/11/9 11:21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDto extends ParamDto {
    //默认页
    public static final int INIT_PAGE_INDEX = 1;
    //默认分页大小
    public static final int INIT_PAGE_SIZE = 10;
    //第几页
    private int pageIndex;
    //分页大小
    private int pageSize;
    //总条数（翻页时无需查询时使用)
    private int total;

    //默认初始化
    public QueryDto() {
        pageIndex = INIT_PAGE_INDEX;
        pageSize = INIT_PAGE_SIZE;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 500)
            pageSize = 500;
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        int offset = 0;
        if (pageIndex <= 0) {
            offset = 0;
        } else {
            offset = (pageIndex - 1) * pageSize;
        }
        return offset;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return this.pageSize;
    }

}

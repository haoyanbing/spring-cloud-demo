package cn.finwood.demo.model;

/**
 * 分页结果返回DTO
 * created by haoyanbing on 2018/11/9 11:21
 */
public class PagingDto<T> extends CommonDto<T> {

    //第几页
    private int pageIndex;
    //分页大小
    private int pageSize;

    //记录条数
    private int total;


    public PagingDto(QueryDto queryDto) {
        super();
        pageSize = queryDto.getPageIndex();
        pageSize = queryDto.getPageSize();
    }

    public PagingDto() {
        super();
        pageSize = 10;
        pageIndex = 1;
    }


    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int total) {
        this.total = total;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the totalCount
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        int pageCount = 0;
        if (pageIndex > 0) {
            if (total % pageSize == 0) {
                pageCount = total / pageSize;
            } else {
                pageCount = total / pageSize + 1;
            }
        }
        return pageCount;
    }

}

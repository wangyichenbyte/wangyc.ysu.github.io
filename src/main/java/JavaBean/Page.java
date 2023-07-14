package JavaBean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = -330730920289954571L;

    private int currentPage;
    private int pageSize;
    private int totalRecord;
    private int totalPage;
    private List<T> datalist;

    public Page() {
        super();
    }

    public Page(int pageNum, int pageSize, List<T> sourceList) {
        if(sourceList == null){
            throw new IllegalArgumentException("源列表不能为空");
        }
        this.totalRecord = sourceList.size();
        System.out.println(sourceList.size());
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil((double) this.totalRecord / this.pageSize);
        this.currentPage = Math.min(Math.max(1, pageNum), this.totalPage);
        int fromIndex = this.pageSize * (this.currentPage - 1);
        int toIndex = Math.min(this.totalRecord, this.pageSize * this.currentPage);
        this.datalist = sourceList.subList(fromIndex, toIndex);
    }

    public Page(int currentPage, int pageSize, int totalRecord, int totalPage, List<T> datalist) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.datalist = datalist;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }
}


package cn.itcast.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndpage;//最后一页
    private Integer page;   //当前页
    private List<Integer> pages=new ArrayList<>();   //显示页
    private Integer count;//问题条数
    private Integer totalPage;

    public void setPaginnation(Integer count, Integer page, Integer size) {
        this.page=page;
        //判断数据要分几页
        this.totalPage=totalPage;
        if (count%size==0){
            totalPage=count/size;
        }else {
            totalPage=count/size+1;
        }
        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>=0){
                pages.add(0,page-i);
            }
            if (page+i<totalPage){
                pages.add(page+i);
            }
    }
        //判断是否为第一页或者最后一页
        if (page==0){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        if (page==totalPage-1){
            showNext=false;
        }else{
            showNext=true;
        }
        //判断pages是否包含第一页
        if (pages.contains(0)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }
        //判断pages是否包含最后一页
        if (pages.contains(totalPage-1)){
            showEndpage=false;
        }else {
            showEndpage=true;
        }
    }
}

package cn.finwood.demo.common.util;

import cn.finwood.demo.common.SystemPromptCode;
import cn.finwood.demo.model.CommonDto;
import cn.finwood.demo.model.PagingDto;
import cn.finwood.demo.model.QueryDto;

import java.util.List;

/**
 * 返回数据组装工具类
 * created by haoyanbing on 2018/11/9 15:06
 */
public class ReturnUtil {

    /**
     * 创建一个成功的dto 对象
     *
     * @return
     */
    public static <T extends Object> CommonDto<T> Success() {
        CommonDto<T> dto = new CommonDto<T>();
        return dto;
    }

    /**
     * 创建一个成功的dto 对象
     *
     * @param t
     * @return
     */
    public static <T> CommonDto<T> Success(T t) {
        CommonDto<T> dto = new CommonDto<T>();
        dto.setResult(t);
        return dto;
    }

    /**
     * 创建一个成功的 PagingDto 对象
     *
     * @param t
     * @return
     */
    public static <T> PagingDto<T> PagingSuccess(T t) {
        PagingDto<T> dto = new PagingDto<T>();
        dto.setResult(t);
        return dto;
    }


    /**
     * 设置分页数据
     *
     * @param queryDto
     */
    public static <T> PagingDto<T> PagingSuccess(T t, QueryDto queryDto) {
        PagingDto<T> dto = PagingSuccess(t);

        dto.setPageSize(queryDto.getPageSize());
        dto.setPageIndex(queryDto.getPageIndex());

//        if (dto.getPageIndex() > 1) {
//            dto.setTotal(queryDto.getTotal());
//        }
        dto.setTotal(queryDto.getTotal());
        return dto;
    }


    /**
     * 创建一个失败的 PagingDto 对象
     *
     * @param t
     * @return
     */
    public static <T> PagingDto<T> PagingFail(int code, T t) {
        PagingDto<T> dto = new PagingDto<T>();
        dto.setCode(code);
        dto.setResult(t);
        return dto;
    }

    public static <T> PagingDto<T> PagingFail(String msg, T t) {
        PagingDto<T> dto = new PagingDto<T>();
        dto.setCode(SystemPromptCode.SYSTEM_UNDEFINED.getCode());
        dto.setMessage(msg);
        dto.setResult(t);
        return dto;
    }
    

    /**
     * 创建一个失败的 PagingDto 对象
     *
     * @param t
     * @return
     */
    public static <T> PagingDto<T> PagingFail(T t) {
        PagingDto<T> dto = new PagingDto<T>();
        dto.setCode(SystemPromptCode.SYSTEM_UNDEFINED.getCode());
        dto.setResult(t);
        return dto;
    }


    /**
     * 创建一个失败的对象
     *
     * @param code
     * @return
     */
    public static CommonDto<Object> Fail(int code) {
        CommonDto<Object> dto = new CommonDto<Object>();
        dto.setCode(code);
        dto.setResult("");
        dto.setMessage(EnumUtil.getErrorText(code));
        return dto;
    }

    /**
     * 创建返回值失败的DTO对象
     *
     * @param code
     * @param result
     * @return
     */
    public static <T> CommonDto<T> Fail(int code, T result) {
        CommonDto<T> dto = new CommonDto<T>();
        dto.setCode(code);
        dto.setResult(result);
        dto.setMessage(EnumUtil.getErrorText(code));
        return dto;
    }


    /**
     * 创建返回值换败的DTO对象,使用通用错误码
     *
     * @param msg
     * @param result
     * @return
     */
    public static <T> CommonDto<T> Fail(String msg, T result) {
        CommonDto<T> dto = new CommonDto<T>();
        dto.setCode(SystemPromptCode.SYSTEM_UNDEFINED.getCode());
        dto.setResult(result);
        dto.setMessage(msg);
        return dto;
    }

//    /**
//     * 创建返回值换败的DTO对象,使用通用错误码
//     *
//     * @param msg
//     * @return
//     */
//    public static CommonDto<Object> Fail(String msg) {
//        CommonDto<Object> dto = new CommonDto<Object>();
//        dto.setCode(SystemPromptCode.SYSTEM_UNDEFINED.getCode());
//        dto.setResult(null);
//        dto.setMessage(msg);
//        return dto;
//    }

    /**
     * 创建返回值换败的DTO对象,使用通用错误码
     *
     * @param msg
     * @return
     */
    public static <T extends Object> CommonDto<T> Fail(String msg) {
        CommonDto<T> dto = new CommonDto<T>();
        dto.setCode(SystemPromptCode.SYSTEM_UNDEFINED.getCode());
        dto.setResult(null);
        dto.setMessage(msg);
        return dto;
    }

//    /**
//     * 是否加载页面
//     * @param queryDo
//     * @return
//     */
//    @Deprecated
//    public static <T extends QueryDto> boolean isLoadPage(T queryDo) {
//        if (queryDo.getPageIndex() > 1 && queryDo.getTotal() > 0) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 判断是否需要加载查询总数
     *
     * @param t
     * @param queryDto
     * @return
     */
    public static <T> boolean isLoadTotal(List<T> t, QueryDto queryDto) {
        if (queryDto.getPageIndex() == 1 || (queryDto.getPageIndex() > 1 && queryDto.getTotal() == 0)) {
            return true;
        }
        return false;
    }

}

package org.smartwork.comm.enums;

import lombok.Data;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***发布状态
 */
public enum ReleaseStateEnum {

    NOT_ISSUE(0,"未发布"),
    HAVE_RELEASED(1,"发布"),
    CANCELLED(2,"取消");

    private Integer code;
    private String name;


    ReleaseStateEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }


    /***
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(ReleaseStateEnum.values())
                .stream().map(releaseStateEnum -> ResultEnum.ResultEnumBuild
                .build()
                .setCode(releaseStateEnum.getCode())
                .setName(releaseStateEnum.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Integer code){
       return Arrays.asList(ReleaseStateEnum.values()).stream()
                .filter(releaseStateEnum -> ConvertUtils.isNotEmpty(code)&&releaseStateEnum.getCode().equals(code))
                .count() >=  1;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

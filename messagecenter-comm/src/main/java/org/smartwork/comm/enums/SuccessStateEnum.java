package org.smartwork.comm.enums;

import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 *
 */
public enum SuccessStateEnum {

    SUCCESS(0,"成功"),
    FAILURE(1,"失败");

    private Integer code;
    private String name;


    SuccessStateEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }


    /***
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(SuccessStateEnum.values())
                .stream().map(successStateEnum -> ResultEnum.ResultEnumBuild
                        .build()
                        .setCode(successStateEnum.getCode())
                        .setName(successStateEnum.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Integer code){
        return Arrays.asList(SuccessStateEnum.values()).stream()
                .filter(successStateEnum -> ConvertUtils.isNotEmpty(code)&&successStateEnum.getCode().equals(code))
                .count() >=  1;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

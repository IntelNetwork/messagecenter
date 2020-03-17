package org.smartwork.comm.enums;

import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AdTypeEnum {

    TEXT(0,"滚动文字"),
    SLIDESHOW(1,"轮播图"),
    STATIC_FIGURE(2,"静态图");
    private Integer code;
    private String name;


    AdTypeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }


    /***
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(AdTypeEnum.values())
                .stream().map(adTypeEnum -> ResultEnum.ResultEnumBuild
                        .build()
                        .setCode(adTypeEnum.getCode())
                        .setName(adTypeEnum.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Object code){
        return Arrays.asList(AdTypeEnum.values()).stream()
                .filter(adTypeEnum -> ConvertUtils.isNotEmpty(code)&&adTypeEnum.getCode().equals(code))
                .count() >=  1;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

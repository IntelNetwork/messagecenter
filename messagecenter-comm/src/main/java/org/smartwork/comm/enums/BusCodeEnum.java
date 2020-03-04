package org.smartwork.comm.enums;

import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/****业务编码
 */
public enum BusCodeEnum {

    REG_VERI_CODE("reg_veri_code","用户注册时验证码"),
    REG_SUCCESS("reg_success","注册成工"),
    DID_NOT_CHOOSE("did_not_choose","到选标时间还未选标"),
    PLEASE_ACCEPT("please_accept","请验收"),
    ACCEPT_SUCCESS("accept_success","验收成功");


    private String code;
    private String name;


    BusCodeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }


    /***
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(BusCodeEnum.values())
                .stream().map(busCodeEnum -> ResultEnum.ResultEnumBuild
                        .build()
                        .setCode(busCodeEnum.getCode())
                        .setName(busCodeEnum.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Object code){
        return Arrays.asList(MsgTypeEnum.values()).stream()
                .filter(busCodeEnum -> ConvertUtils.isNotEmpty(code)&&busCodeEnum.getCode().equals(code))
                .count() >=  1;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

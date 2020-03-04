package org.smartwork.comm.enums;

import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.ResultEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**** 消息类型
 */
public enum MsgTypeEnum {

    SMS(0,"短信"),
    OFFICIAL_ACCOUNTS(1,"公众号"),
    MAIL(2,"邮件");


    private Integer code;
    private String name;


    MsgTypeEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }


    /***
     *
     * @return
     */
    public static List<ResultEnum> resultEnums(){
        return Arrays.asList(MsgTypeEnum.values())
                .stream().map(msgTypeEnum -> ResultEnum.ResultEnumBuild
                        .build()
                        .setCode(msgTypeEnum.getCode())
                        .setName(msgTypeEnum.getName())).collect(Collectors.toList());
    }

    /***
     *   判断是否存在
     * @param code
     * @return
     */
    public static boolean existsCode(Object code){
        return Arrays.asList(MsgTypeEnum.values()).stream()
                .filter(msgTypeEnum -> ConvertUtils.isNotEmpty(code)&&msgTypeEnum.getCode().equals(code))
                .count() >=  1;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

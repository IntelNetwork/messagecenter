package org.smartwork;

import org.forbes.comm.constant.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"org.forbes", "org.smartwork"})
@EnableDiscoveryClient
@EnableSwagger2
public class SmartworkApplication {

    /***启动类
     * main方法慨述:
     * @param args void
     * @创建人 huanghy
     * @创建时间 2019年5月13日 上午10:49:22
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartworkApplication.class, args);
    }
}

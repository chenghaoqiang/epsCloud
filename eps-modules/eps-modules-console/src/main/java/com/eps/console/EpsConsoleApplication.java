package com.eps.console;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.eps.common.security.annotation.EnableCustomConfig;
import com.eps.common.security.annotation.EnableRyFeignClients;
import com.eps.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 后台模块
 * 
 * @author liuizhimin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class EpsConsoleApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EpsConsoleApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}

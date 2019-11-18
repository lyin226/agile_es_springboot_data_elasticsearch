package com.agile.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuyi
 * @date 2019/11/13
 */

@SpringBootApplication(scanBasePackages = "com.agile.es.*")
public class EsApplication {

    private static Logger logger = LoggerFactory.getLogger(EsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
        logger.info("es 启动成功!");
    }
}

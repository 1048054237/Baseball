package com.sjw.component;/**
 * Created by Administrator on 2019/1/12.
 */

import com.sjw.crawler.lancher.ImgLancher;
import com.sjw.crawler.lancher.PLancher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author shijiawei
 * @date 2019/1/12
 */
@Component
public class Start {
    @Value("${start}")
    private Integer start;
    @Autowired
    private PLancher pLancher;
    @Autowired
    private ImgLancher imgLancher;

    public void getStart() {
        if (start == null) {
            start = 1;
        }
        if (start == 1) {
            pLancher.stratCrawler();
        } else if (start == 0) {
            imgLancher.startDownload();
        } else {
            System.exit(0);
            throw new RuntimeException("程序启动码,错误");
        }

    }
}

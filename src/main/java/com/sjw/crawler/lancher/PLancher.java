package com.sjw.crawler.lancher;

import cn.hutool.core.date.DateUtil;
import com.sjw.component.SendMail;
import com.sjw.crawler.pageprocesser.PPageProcesser;
import com.sjw.crawler.pipeline.PPieline;
import com.sjw.tool.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/12/21.
 * 启动抓取 列表页的爬虫， 暂未完成，
 * 不能抓取全部列表。
 * 不能进行分页
 */
@Component
public class PLancher {
    @Autowired
    private PPieline pPieline;
    private List<String> list = new ArrayList<String>();

    @Autowired
    private SendMail sendMail;

    @Value("${crawler.thread.num}")
    private Integer threadNum;
    @Value("${crawler.name}")
    private String name;
    @Value("${crawler.url}")
    private String urls;


    public void stratCrawler() {
        String[] split = urls.split(",");
        list = Arrays.asList(split);
        sendMail.SendEmail("Cralwer is starting," + DateUtil.date(), name, "zhangning_holley@126.com");
        Request[] request = getRequest();
        Spider.create(new PPageProcesser())
                .addPipeline(pPieline)
                .addRequest(request)
                .thread(threadNum)
                .addUrl()
                .run();
        sendMail.SendEmail("Cralwer is end," + DateUtil.date(), name, "zhangning_holley@126.com");
    }

    public Request[] getRequest() {
        Request[] requests = new Request[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            Request request = new Request();
            String categories = UrlUtil.addPath("https://" + str, "categories");
            request.setUrl(categories);
            request.putExtra("host", categories);
            requests[i] = request;
        }
        return requests;
    }
}

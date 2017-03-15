package com.xielaoban;

import com.xielaoban.dao.Article;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xielaoban.util.XHttpUtils.getResponseString;

public class ZGQBYP {

    private static final String HOST = "http://www.zgqbyp.com/";
    private static String INDEX_URL = "http://www.zgqbyp.com/web/news/31.html";
    private static String OTHER_URL = "http://www.zgqbyp.com/web/news/31_";
    /**
     * GB2312
     */
    public static final String GB2312 = "GB2312";


    public static final String A_LIST = "<div class=\"newslist\">[\\s\\S]*?</div>";
    public static final String A_LI = "(?:<li>)([\\s\\S]*?)(?:</li>)";
    public static final String A_DATE = "(?:<span>)([\\s\\S]*?)(?:</span>)";
    public static final String A_LINK = "/html[\\s\\S]*?html";
    public static final String A_TITLE = "(?:<a.*?>)([\\s\\S]*?)(?:</a>)";
    public static final String A_CONTENT = "<div class=\"main\">([\\s\\S]*?)(?:<div class=\"gduos\">)";

    public static List<String> errorMsgList = new ArrayList<>();


    public static List<Article> getArticles() throws IOException, InterruptedException {
        List<Article> eachPageArticles = new ArrayList<>();

        String responseString = getResponseString(INDEX_URL, GB2312);
        Pattern pattern = Pattern.compile(A_LIST, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(responseString);
        String articleDiv = "";
        if (matcher.find()) {
            articleDiv = matcher.group(0);
        }
        eachPageArticles.addAll(eachListPage(articleDiv, 1));

        int i = 1;

        while (true) {
            i++;
            String responseString2 = getResponseString(OTHER_URL + i + ".html", GB2312);
            if (StringUtils.isEmpty(responseString2)) break;
            Pattern pattern2 = Pattern.compile(A_LIST, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(responseString2);
            String articleDiv2 = "";
            if (matcher2.find()) {
                articleDiv2 = matcher2.group(0);
            }
            eachPageArticles.addAll(eachListPage(articleDiv2, i));
        }
        return eachPageArticles;
    }

    private static List<Article> eachListPage(String articleDiv, int i) throws IOException, InterruptedException {
        Pattern patternLI = Pattern.compile(A_LI, Pattern.CASE_INSENSITIVE);
        Matcher matcherLI = patternLI.matcher(articleDiv);
        List<Article> articles = new ArrayList<>();
        while (matcherLI.find()) {
            String liString = matcherLI.group(1);
            Article article = new Article();
            articles.add(article);
            // 获取标题
            Pattern patternTitle = Pattern.compile(A_TITLE, Pattern.CASE_INSENSITIVE);
            Matcher matcherTitle = patternTitle.matcher(liString);
            if (matcherTitle.find()) {
                article.setTitle(matcherTitle.group(1));
            }

            // 获取链接
            Pattern patternLink = Pattern.compile(A_LINK, Pattern.CASE_INSENSITIVE);
            Matcher matcherLink = patternLink.matcher(liString);
            if (matcherLink.find()) {
                article.setLink(HOST + matcherLink.group(0));
            } else {
                // 这个链接可能不正常
                String errMsg = "第%d页%s不正常，链接为%s";
                String format = String.format(errMsg, i, article.getTitle(), article.getLink());
                errorMsgList.add(format);
                continue;
            }
            // 获取时间
            Pattern patternDate = Pattern.compile(A_DATE, Pattern.CASE_INSENSITIVE);
            Matcher matcherDate = patternDate.matcher(liString);
            if (matcherDate.find()) {
                article.setDate(matcherDate.group(1));
            }
            // 获取文章内容，先耦合在这里回头再改 TODO
/*            String rs = getResponseString(article.getLink(), GB2312);
            Pattern patternContent = Pattern.compile(A_CONTENT);
            Matcher matcherContent = patternContent.matcher(rs);
            String content = matcherContent.group(1);
            article.setContent(content);*/

        }
        return articles;
    }


}


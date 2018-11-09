package cn.finwood.demo.gateway.filter;

import cn.finwood.demo.common.SystemConst;
import cn.finwood.demo.common.SystemPromptCode;
import cn.finwood.demo.common.exception.GateWayException;
import cn.finwood.demo.common.util.MD5Util;
import cn.finwood.demo.common.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 验签过滤器
 * created by haoyanbing on 2018/11/8 9:43
 */
@Component
public class SignatureFilter extends ZuulFilter {
    private final static Logger logger = LoggerFactory.getLogger(SignatureFilter.class);


    private final static String secret = "finwood123";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 文件上传不验证签名
        String contentType = request.getContentType() == null ? "" : request.getContentType();
        if (contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            return null;
        }

        // 取header中的签名
        String sign = request.getHeader("sign");
        if (StringUtils.isEmpty(sign)) {
            logger.error("header中不包含签名sign字段");
            throw new GateWayException(SystemPromptCode.SYSTEM_SIGNATURE_INVALID.getCode());
        }

        Map paramMap = getParamMap(request);
        if (!verifySign(paramMap, sign)) {
            logger.error("签名验证错误");
            throw new GateWayException(SystemPromptCode.SYSTEM_SIGNATURE_INVALID.getCode());
        }
        return null;
    }

    /**
     * 处理Request中的请求数据
     * @param request 求购
     * @return 请求参数Map
     */
    private Map getParamMap (HttpServletRequest request) {
        String contentType = request.getContentType() == null ? "" : request.getContentType();
        // GET请求方式或表单请求数据处理
        if (request.getMethod().equalsIgnoreCase("get") || contentType.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            Map<String, String[]> paramMap = request.getParameterMap();
            if (paramMap.size() > 0) {
                Map<String, Object> map = new HashMap<>();
                Iterator iterator = paramMap.entrySet().iterator();
                String temp;
                while (iterator.hasNext()) {
                    temp = "";
                    Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
                    for (String item : entry.getValue()) {
                        temp = item + ",";
                    }
                    map.put(entry.getKey(), temp.substring(0, temp.length() - 1));
                }
                return map;
            }
            return null;
        } else if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            // json数据处理
            try {
                InputStream inputStream = request.getInputStream();
                String json = StreamUtils.copyToString(inputStream, Charset.forName("utf-8"));
                if (!StringUtils.isEmpty(json)) {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> map = mapper.readValue(json, Map.class);
                    return map;
                }
            } catch (IOException e) {
                logger.error("json数据处理异常");
                throw new GateWayException("json数据处理异常");
            }
        }
        return null;
    }

    /**
     * 验证签名
     * @param json
     * @param sign
     * @return
     */
    private Boolean verifySign(Map json, String sign) {
        if (sign.equals(signature(json, secret))) {
            return true;
        }
        return false;
    }

    /**
     * 生成签名(null数据直接跳过)
     * @param obj
     * @param secret
     * @return
     */
    private String signature(Map<String,Object> obj, String secret ) {
        Iterator<Map.Entry<String, Object>> iterator = obj.entrySet().iterator();
        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterator.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            sortedMap.put(key, value.toString());
        }
        String source = concatMap(sortedMap) + secret ;
        return MD5Util.md5(source);
    }

    /**
     * Map转字符串(key=value&key=value)
     * @param map
     * @return
     */
    private String concatMap(SortedMap<String, Object> map) {
        Iterator iterator = map.entrySet().iterator();
        StringBuffer buffer = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            buffer.append(String.format("%s=%s&", key, String.valueOf(value)));
        }
        if (buffer.length() > 0) {
            buffer = buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }
}

package aa.project;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class Test {

    @org.junit.Test
    public void test() throws Exception {
        /*URL*/
        String urlBuilder = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=KaSLgJZz5%2BioDq0OOHGy%2B9unmih%2F0nR7Cg%2BlPi0I7V94hmfI0aIjjQi3oMfNQs6SkTRe06Vddu0EmPlyEWQJ9g%3D%3D" + /*Service Key*/
                "&" + URLEncoder.encode("LAWD_CD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("11110", StandardCharsets.UTF_8) + /*각 지역별 코드*/
                "&" + URLEncoder.encode("DEAL_YMD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("201512", StandardCharsets.UTF_8); /*월 단위 신고자료*/
        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
            sb.append(System.getProperty("line.separator"));
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);
    }

    @org.junit.Test
    public void test2() throws Exception {
        /*URL*/

        int DEAL_YMD = 202103;
        for (int i = 0; i < 36; i++) {


            final String serviceKey = "KaSLgJZz5%2BioDq0OOHGy%2B9unmih%2F0nR7Cg%2BlPi0I7V94hmfI0aIjjQi3oMfNQs6SkTRe06Vddu0EmPlyEWQJ9g%3D%3D";
            final String requestUrl = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade?serviceKey=" + serviceKey +
                    "&LAWD_CD=11110&DEAL_YMD=" + DEAL_YMD;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/JSON");
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(httpHeaders);
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            ResponseEntity<TestDto> result = restTemplate.exchange(
                    URI.create(requestUrl),
                    HttpMethod.GET,
                    request,
                    TestDto.class
            );

            DEAL_YMD++;
            if (202113 == DEAL_YMD) {
                DEAL_YMD = 202201;
            } else if (202213 == DEAL_YMD) {
                DEAL_YMD = 202203;
            }
            System.out.println("result = " + result.getBody());
            System.out.println("================================================================");
        }

    }

    @org.junit.Test
    public void test3() throws Exception {


    }
}

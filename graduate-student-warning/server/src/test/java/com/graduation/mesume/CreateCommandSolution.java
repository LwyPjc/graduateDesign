package com.graduation.mesume;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.iotda.v5.*;
import com.huaweicloud.sdk.iotda.v5.region.IoTDARegion;
import com.huaweicloud.sdk.iotda.v5.model.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.HashMap;

public class CreateCommandSolution {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        String token = getToken();
        String paramStr = "{\"service_id\":\"STREETHOME\",\"command_name\":\"Control_Light\",\"paras\":{\"Light\":\"OFF\"},\"send_strategy\":\"immediately\",\"expire_time\":86400}";
        Map<String,Object> params = JSONUtil.toBean(paramStr,Map.class);


//        String url = "https://iotda.cn-north-4.myhuaweicloud.com/v5/iot/0a3c8d3bfd800f002fb2c001364994b6/devices/5ff47e814f04b0030996633d_865057044148389/async-commands";
        String url = "https://iotda.cn-north-4.myhuaweicloud.com/v5/iot/0a3c8d3bfd800f002fb2c001364994b6/devices/5ff47e814f04b0030996633d_865057044148389/shadow";
        /*HttpResponse response = HttpRequest.get(url)
                .header("X-Auth-Token",token)
                .header("Content-Type","application/json")
                .body(JSONUtil.toJsonStr(params)).execute();*/
        HttpResponse response = HttpRequest.get(url)
                .header("X-Auth-Token",token)
                .header("Content-Type","application/json")
                .execute();
        //创建SSLContext
       /* SSLContext sslContext=SSLContext.getInstance("SSL");
        TrustManager[] tm={new MyX509TrustManager()};
        //初始化
        sslContext.init(null, tm, new java.security.SecureRandom());;
        //获取SSLSocketFactory对象
        SSLSocketFactory ssf=sslContext.getSocketFactory();
        request.setSSLSocketFactory(ssf);
        Map<String,String> headers = new HashMap<>();
        headers.put("User-Agent","API Explorer");
        headers.put("X-Auth-Token","MIIaygYJKoZIhvcNAQcCoIIauzCCGrcCAQExDTALBglghkgBZQMEAgEwghjcBgkqhkiG9w0BBwGgghjNBIIYyXsidG9rZW4iOnsiZXhwaXJlc19hdCI6IjIwMjEtMDEtMjJUMDg6Mzk6MjMuNDMwMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwiY2F0YWxvZyI6W10sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjAifSx7Im5hbWUiOiJ0ZV9hZ2VuY3kiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kY3NfbXNfcndzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX3Nwb3RfaW5zdGFuY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdmFzX3Zjcl92Y2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWZfbm9kZWdyb3VwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NlX3R1cmJvX2VuaGFuY2VkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2FzY2VuZF9rYWkxIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2thZTEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kYnNfcmkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ibXNfaHBjX2gybGFyZ2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfZXNzZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2lvZHBzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmF0Y2hfZWNzX2NsdXN0ZXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfZ3B1X3YxMDAiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kd3NfcG9jIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2tjMV91c2VyX2RlZmluZWQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9tZWV0aW5nX2VuZHBvaW50X2J1eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF9ubHAiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zaXNfc2Fzcl9lbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3NhZF9iZXRhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc2VydmljZXN0YWdlX21ncl9kdG1fZW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9WSVNfSW50bCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19ncHVfcDJzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXZzX3ZvbHVtZV9yZWN5Y2xlX2JpbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rjc19kY3MyLWVudGVycHJpc2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jbmFkX2JldGEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92Y2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92Y3AiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTliIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZHBwIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3ZyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2M2bmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vY3NtYXJ0Y2FtcHVzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYmtzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYXBwY3ViZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21lZXRpbmdfaGFyZGFjY291bnRfYnV5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXVsdGlfYmluZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX25scF9tdCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX2lvdHN0YWdlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3NlXzJuZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VpcF9wb29sIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX2Z1bmN0aW9uIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtM2QiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wcm9qZWN0X2RlbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX202bXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zaGFyZUJhbmR3aWR0aF9xb3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2lfb2NlYW4iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfcmV0eXBlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYWFkX2ZyZWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vbmVhY2Nlc3MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3NfaXIzeCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VsYl9ndWFyYW50ZWVkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1zb3V0aHdlc3QtMmIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jaWUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zZnN0dXJibyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19uYXQiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3X2ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9odl92ZW5kb3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2NuLW5vcnRoLTRlIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9jbi1ub3J0aC00ZCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX0lFQyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RheXVfZGxtX2NsdXN0ZXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pbnRsX2NvbmZpZ3VyYXRpb24iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2VfbWNwX3RoYWkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ubHBfbGdfdGciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kc2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9zZXJ2aWNlc3RhZ2VfbWdyX2R0bSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tbm9ydGgtNGYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2xhLXNvdXRoLTJiIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3BoIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbXRkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV9nNXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF93a3Nfa3AiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2lfa3VucGVuZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3JpX2R3cyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfY24tc291dGh3ZXN0LTJkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaW90ZWRnZV9jYW1wdXMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2ZmbGluZV9kNiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZwY19mbG93X2xvZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX2ljcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FhZF9iZXRhX2lkYyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcmVwX2FjY2VsZXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2llZl9lZGdlbWVzaCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rzc19tb250aCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzZyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2RlY19tb250aF91c2VyIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX2VkZ2VhdXRvbm9teSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX3ZpcF9iYW5kd2lkdGgiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9vc2MiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2xkX3Jlb3VyY2UiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF93ZWxpbmticmlkZ2VfZW5kcG9pbnRfYnV5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZGNzX3JpIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmLWludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wc3RuX2VuZHBvaW50X2J1eSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21hcF9vY3IiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9kbHZfb3Blbl9iZXRhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfb2JzX2R1YWxzdGFjayIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2VkY20iLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc2JzX3Jlc3RvcmUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pdnNjcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19jNmEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF92cG5fdmd3IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfc21uX2NhbGxub3RpZnkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pcnRjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY2NlX2JtczIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9wY2EiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jY2VfYXNtX2hrIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY29uZmlndXJhdGlvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2NzYnNfcHJvZ3Jlc3NiYXIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pb3YtdHJpYWwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9lY3Nfb2ZmbGluZV9waTIiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9ldnNfcG9vbF9jYSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2JjZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfQ04tU09VVEgtMyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19vZmZsaW5lX2Rpc2tfNCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2dzc19mcmVlX3RyaWFsIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZXBzIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfY3Nic19yZXN0b3JlX2FsbCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2wyY2ciLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9XZUxpbmtfZW5kcG9pbnRfYnV5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaW50bF92cGNfbmF0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZmNzX3BheSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2lvdGFuYWx5dGljcyIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX21heGh1Yl9lbmRwb2ludF9idXkiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9sMmNnX2ludGwiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9hX2FwLXNvdXRoZWFzdC0xZSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFkIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbmxwX2tnIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9hcC1zb3V0aGVhc3QtMWYiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9pZWZfZGV2aWNlX2RpcmVjdCIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Rjc19kY3MyX3Byb3h5IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfZWNzX3ZncHVfZzUiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9obXNhIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfdGljc19vcGVuX2JldGEiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF9jc19hcm1fcG9jIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfbWFwX3Zpc2lvbiIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2Vjc19yaSIsImlkIjoiMCJ9LHsibmFtZSI6Im9wX2dhdGVkX2FfYXAtc291dGhlYXN0LTFjIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfYV9ydS1ub3J0aHdlc3QtMmMiLCJpZCI6IjAifSx7Im5hbWUiOiJvcF9nYXRlZF91bGJfbWlpdF90ZXN0IiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfaWVmX3BsYXRpbnVtIiwiaWQiOiIwIn0seyJuYW1lIjoib3BfZ2F0ZWRfVmlkZW9fQ2FtcHVzIiwiaWQiOiIwIn1dLCJwcm9qZWN0Ijp7ImRvbWFpbiI6eyJuYW1lIjoiaHc0MDM5MTI5MiIsImlkIjoiMGEzYzg0NGJlYzgwMTA4YTBmNDRjMDAxZTkwODU4NjAifSwibmFtZSI6ImNuLW5vcnRoLTQiLCJpZCI6IjBhM2M4ZDNiZmQ4MDBmMDAyZmIyYzAwMTM2NDk5NGI2In0sImlzc3VlZF9hdCI6IjIwMjEtMDEtMjFUMDg6Mzk6MjMuNDMwMDAwWiIsInVzZXIiOnsiZG9tYWluIjp7Im5hbWUiOiJodzQwMzkxMjkyIiwiaWQiOiIwYTNjODQ0YmVjODAxMDhhMGY0NGMwMDFlOTA4NTg2MCJ9LCJuYW1lIjoiaHc0MDM5MTI5MiIsInBhc3N3b3JkX2V4cGlyZXNfYXQiOiIiLCJpZCI6IjBhM2M4NDRjY2MwMGY1ZWMxZjkzYzAwMTVhNDYwZjU2In19fTGCAcEwggG9AgEBMIGXMIGJMQswCQYDVQQGEwJDTjESMBAGA1UECAwJR3VhbmdEb25nMREwDwYDVQQHDAhTaGVuWmhlbjEuMCwGA1UECgwlSHVhd2VpIFNvZnR3YXJlIFRlY2hub2xvZ2llcyBDby4sIEx0ZDEOMAwGA1UECwwFQ2xvdWQxEzARBgNVBAMMCmNhLmlhbS5wa2kCCQDcsytdEGFqEDALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAercyWS-92WuuXmik4dEr8aX2lV3jaDt3MFAwCHeZa-VN-QWStx2I55tZHJq-eYCMy8Aa9ErifXCh3AwVJV7A2dYJqT2kiI7UOZR7nhzmqE3bWCgCUzZW1DJ9kxcOCLxUt8ha5Y5skSoLiBBTdZQSMmr0e-8fKHVbU8zWOsRoxs1RYUzsHBbS7uyZlFdc3yLTVzBZZHNogkTRE450kem1tblbmM-n6k-vMxMpWQ5tGVtj8YaexnxKV+Wk6iO3SaAGzf651UpusjdLPW9Uvyrqardfbp6Oz9sBP8GTdBAErrO5gwZeGx7MWXI3LvOvegNrqLf4MHaM0mAJ2v+Ibkppmw==");
        headers.put("Content-Type","application/json");
        request.addHeaders(headers);

//        request.body(JSONUtil.toJsonStr(params));
        request.form(params);
        HttpResponse response = request.execute();*/
        System.out.println(response.toString());
    }

    public static String getToken(){
        String paramStr = "{\n" +
                " \"auth\": {\n" +
                "  \"identity\": {\n" +
                "   \"methods\": [\n" +
                "    \"password\"\n" +
                "   ],\n" +
                "   \"password\": {\n" +
                "    \"user\": {\n" +
                "     \"domain\": {\n" +
                "      \"name\": \"hw40391292\"\n" +
                "     },\n" +
                "     \"name\": \"hw40391292\",\n" +
                "     \"password\": \"013758657183asd\"\n" +
                "    }\n" +
                "   }\n" +
                "  },\n" +
                "  \"scope\": {\n" +
                "   \"domain\": {\n" +
                "    \"id\": \"0a3c844bec80108a0f44c001e9085860\",\n" +
                "    \"name\": \"hw40391292\"\n" +
                "   },\n" +
                "   \"project\": {\n" +
                "    \"id\": \"0a3c8d3bfd800f002fb2c001364994b6\",\n" +
                "    \"name\": \"cn-north-4\"\n" +
                "   }\n" +
                "  }\n" +
                " }\n" +
                "}";
        Map<String,Object> params = JSONUtil.toBean(paramStr,Map.class);

        String url = "https://iam.cn-east-2.myhuaweicloud.com/v3/auth/tokens?nocatalog=true";
        HttpResponse response = HttpRequest.post(url)
                .header("Content-Type","application/json")
                .body(JSONUtil.toJsonStr(params)).execute();
        return response.header("X-Subject-Token");
    }

}
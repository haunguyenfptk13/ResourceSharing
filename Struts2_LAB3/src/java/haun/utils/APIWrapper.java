/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.utils;

import com.google.gson.Gson;
import haun.account.AccountDTO;

/**
 *
 * @author msi
 */
public class APIWrapper {

    private static String appID = "1038925963721-9cvo2d9v6dldukghh0od8hd4dca0704t.apps.googleusercontent.com";
    private static String appSecret = "WOUraZrnOR6DJ4CsgcZaerPe";
    private static String redirectUrl = "http://localhost:8080/Struts2_LAB3/logingoogle";
    private static String secretKeyReCAPTCHA = "6Ldkzq4ZAAAAAH41mqttc-JAEBBCMAFxNOHooZEL";
    private String accessToken;
    private Gson gson;

    public APIWrapper() {
        gson = new Gson();
    }

    public static String getDialogLink() {
        String dialogLink = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=%s&response_type=code&client_id=%s&approval_prompt=force";
        return String.format(dialogLink, redirectUrl, appID);
    }

    public String getAccessToken(String code) {
        String accessTokenLink = "https://accounts.google.com/o/oauth2/token?"
                + "client_id=%s"
                + "&client_secret=%s"
                + "&redirect_uri=%s"
                + "&code=%s"
                + "&grant_type=authorization_code";
        accessTokenLink = String.format(accessTokenLink, appID, appSecret, redirectUrl, code);
        String result = NetUtils.GetResult2(accessTokenLink);
        String token = result.substring(result.indexOf(":") + 3, result.indexOf(",") - 1);
        return token;
    }

    public AccountDTO getUserInfo() {
        String infoUrl = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s";
        infoUrl = String.format(infoUrl, this.accessToken);
        String result = NetUtils.GetResult(infoUrl);
        AccountDTO userInfo = gson.fromJson(result, AccountDTO.class);
        return userInfo;
    }

    public boolean verifyReCAPTCHA(String code) {
        String infoUrl = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
        infoUrl = String.format(infoUrl, secretKeyReCAPTCHA, code);
        String result = NetUtils.GetResult(infoUrl);
        AccountDTO token = gson.fromJson(result, AccountDTO.class);
        boolean verify = token.getVerifyRECAPTCHA();
        return verify;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

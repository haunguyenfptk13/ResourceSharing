/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author msi
 */
public class NetUtils {
    public static String GetResult(String url){
        try{
            return Request.Get(url).setHeader("Accept-Charset", "utf-8").execute().returnContent().asString();
        } catch (IOException ex) {
            Logger.getLogger(NetUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public static String GetResult2(String url){
        try{
            return Request.Post(url).setHeader("Accept-Charset", "utf-8").execute().returnContent().asString();
        } catch (IOException ex) {
            Logger.getLogger(NetUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

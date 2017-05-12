package com.zeroxy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	private final static Logger LOG = LoggerFactory.getLogger(CommonUtil.class);  
			
	public static String generateCode() {
		StringBuffer sb = new StringBuffer()
				.append(new Random().nextInt(9) + 1)
				.append(new Random().nextInt(10))
				.append(new Random().nextInt(10))
				.append(new Random().nextInt(10));

		return sb.toString();
	}
	public static File renameFile(File oldFile,String newname){ 
		File newfile = null ;
		String oldName = oldFile.getName();
        if(!oldName.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            newfile=new File(oldFile.getParentFile().getPath()+"/"+newname); 
            if(!oldFile.exists()){
                return null;
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            {
                newfile.delete();
            	oldFile.renameTo(newfile); 
            }
            else{
            	oldFile.renameTo(newfile); 
            } 
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
        
        return newfile;
	 }

	public static String generateCode(int size) {
		StringBuffer sb = new StringBuffer()
				.append(new Random().nextInt(9) + 1);
		for (int i = 1; i < size; i++) {
			sb.append(new Random().nextInt(10));
		}

		return sb.toString();
	}
	
	public static String generateTaskId(){
		String taskId = UUID.randomUUID().toString();
		return taskId ;
	}

	public static String md5(String s) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes("UTF8"));
			return hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static String sha1(String s) throws NoSuchAlgorithmException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(s.getBytes("UTF8"));
			return hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static final String HMAC_SHA1 = "HmacSHA1";  
  
    /** 
     * 生成签名数据 
     *  
     * @param data 待加密的数据 
     * @param key  加密使用的key 
     * @return 生成MD5编码的字符串  
     * @throws InvalidKeyException 
     * @throws NoSuchAlgorithmException 
     */  
    public static String hmac_sha1(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {  
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
        Mac mac = Mac.getInstance(HMAC_SHA1);  
        mac.init(signingKey);  
        byte[] rawHmac = mac.doFinal(data);  
        return md5(new String(rawHmac));  
    }  

	public static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}
	 /**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key){      
    	
    	if (key == null) return null; 
    		BASE64Decoder decoder = new BASE64Decoder(); 
    	try { 
    		byte[] b = decoder.decodeBuffer(key); 
    		return b;
    	} catch (Exception e) { 
    		return null; 
    	} 
    }               
                  
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return new BASE64Encoder().encode(key).replace("\r", "").replace("\n", "");
    }

	public static String generateEmailCode(){
		try {
			return encryptBASE64(md5(""+System.currentTimeMillis()).getBytes());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return "";
	}
    
    /**
     * 将异常信息转化成字符串
     * @param t
     * @return
     * @throws IOException 
     */
	public static String castException2String(String msg,Throwable t) {
		if (t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			t.printStackTrace(new PrintStream(baos));
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String result = baos.toString();
		result = result.replaceAll("com.shield", "<span style='color:red;font-weight:bold'>com.shield</span>");
		result = "<h4>"+msg+"</h4><br/>"+result.replaceAll("\n", "<br/>");
		return result;
	}
	
	public static String regexFirstGet(String content, String regEx) {
		 Pattern pattern = Pattern.compile(regEx);
		 Matcher m = pattern.matcher(content);
  
		 String result = null ;
		 while( m.find() ){
			 result = m.group();
		 }
		 return result;
	}
	public static void main(String[] args) {
		String result = regexFirstGet("1111>", "<img (.*?)src=(.[^\\[^>]*)(.*?)>");
		String regex = "http.*?['\"]";
		String result2 = regexFirstGet(result, regex);
		System.out.println(result2.substring(0,result2.length()-1));
	}
}

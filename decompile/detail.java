
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class detail {

	public static void main(String[] args) {   
	   File file=new File("");   
	   File directory = new File("");//�趨Ϊ��ǰ�ļ���
	   File file0=new File(directory.getAbsolutePath()+"/apk");
	   File [] file1=file0.listFiles();
	   for(int q=0;q<file1.length;q++){
		   if(file1[q].getName().endsWith(".xml")){
			   file=file1[q];
		   }
	   }
	   
	   String a=file2String(file,"UTF-8");
	   
	   pattern("android:versionCode=\"\\d*\"",a); 

       pattern("android:versionName=\"[\\S^\"]*\"",a); 
       
       pattern("package=\"[\\S^\"]*\"",a); 
       
       String c[]=a.split("\n");
       for(int i=0;i<c.length;i++){
    	   if(c[i].contains("UMENG_APPKEY")){
    		   pattern("android:value=\"[\\S^\"]*\"",c[i]); 
    	   }else{
    		   if(c[i].contains("UMENG_CHANNEL")){
    			   pattern("android:value=\"[\\S^\"]*\"",c[i]); 
    		   }
    	   }
       }
	   //�鿴ǩ����־signlog.txt
	    File file2=new File(directory.getAbsolutePath()+"/apk/signlog.txt");
		if(file2==null){
			System.out.println("��apkδǩ����"); 
		}else{
			String d=file2String(file2,"UTF-8");
			String e[]=d.split("\n");
			if(e[e.length-1].contains("����֤")){
				System.out.println("��apk��ǩ����ǩ���ļ���Ϣ���£�");
				for(int j=0;j<e.length;j++){
					if(e[j].contains("��Ч��"))
					{
						System.out.println(e[j-1]);
						System.out.println(e[j]);
						break;
					}
				}
			}else{
				System.out.println("��apkδǩ����"); 
			}
			
			
		}
	}
	private static void pattern(String b,String a) {
		Pattern p2 = Pattern.compile(b); 
		   Matcher m2 = p2.matcher(a); 
		   while (m2.find()) { 
		           String s0 = m2.group(); 
		           System.out.println(s0); 
		   }
	}  
	/** 
     * �ı��ļ�ת��Ϊָ��������ַ��� 
     * 
     * @param file         �ı��ļ� 
     * @param encoding �������� 
     * @return ת������ַ��� 
     * @throws IOException 
     */ 
    public static String file2String(File file, String encoding) { 
            InputStreamReader reader = null; 
            StringWriter writer = new StringWriter(); 
            try { 
                    if (encoding == null || "".equals(encoding.trim())) { 
                            reader = new InputStreamReader(new FileInputStream(file), encoding); 
                    } else { 
                            reader = new InputStreamReader(new FileInputStream(file)); 
                    } 
                    //��������д������� 
                    char[] buffer = new char[2048]; 
                    int n = 0; 
                    while (-1 != (n = reader.read(buffer))) { 
                            writer.write(buffer, 0, n); 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    return null; 
            } finally { 
                    if (reader != null) 
                            try { 
                                    reader.close(); 
                            } catch (IOException e) { 
                                    e.printStackTrace(); 
                            } 
            } 
            //����ת����� 
            if (writer != null) 
                    return writer.toString(); 
            else return null; 
    }
	       
}

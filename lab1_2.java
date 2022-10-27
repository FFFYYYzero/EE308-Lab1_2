import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class lab1_2 {

	public static void main(String[] args) throws IOException {
		String keyword[]= {"auto", "break", "case", "char", "const","continue", "default", "do", "double", "else","enum", "extern", "float", "for", "goto", "if","int", "long", "register", "return", "short",
"signed", "sizeof", "static", "struct", "switch","typedef", "union", "unsigned", "void","volatile", "while"};
		Scanner Sc=new Scanner(System.in);
		System.out.println("please enter path:");
		String path=Sc.nextLine();
		System.out.println("please enter level:");
		String level1=Sc.nextLine();
		int level=Integer.parseInt(level1);
				String str =reader(path);
				if(level>0.5) {
				requirement1(str,keyword);
				}
				if(level>1.5) {
				requirement2(str);
				}
				if(level>2.5) {
				requirement3(str);
				}
				if(level>3.5) {
				requirement4(str);
				}
				
		}
	
	//read the data from the file,并将其保存为String形式
public static String reader(String path) throws IOException{
	BufferedReader br = null;
	String str = "";
	String str1[] ;
    try {
    	
        String file = path; 
        FileReader fr = new FileReader(file); 
        br = new BufferedReader(fr); 
        String line;
        
        while ((line = br.readLine()) != null) {
        	int i=0;
        	str=str+line;
        }
      
      }
      catch(IOException e) { e.printStackTrace();}
      finally
      {
          try { if (br != null) br.close(); }
          catch(IOException e) { e.printStackTrace(); }
          
      }
    
    return str;
}
//通过依次查找keyword在文件中出现的次数，并将他们累加，得到最终的答案
//使用了indexOf() function来帮助查找，如果String中能找到，则次数累加1，继续检索，直至无法找到
//因为在搜索keyword do时，会受到来自keyword double的干扰，因此在最后要再遍历一次消除干扰
public static void requirement1 (String str,String keyword[]) {
	int num=0;
	int index=0;
	for(int i=0;i<keyword.length;i++) {
		index=0;
		while(str.indexOf(keyword[i],index)!=-1) {
			num++;
			index=str.indexOf(keyword[i],index)+keyword[i].length();
			}
		}
	index=0;
	
	//Eliminate errors caused by do and double
	while(str.indexOf("do",index)!=-1) {
		
		index=str.indexOf("do",index)+"do".length();
		if(str.charAt(index)=='u') {
			num--;
		}
	}
	
	System.out.println("total num: "+num); 
}
//对于要求二，分两步进行，首先得到switch的个数，再以此为依据遍历switch的个数次，得到各个switch中case的个数
//依旧使用indexof() function来得到对应的个数，switch的个数直接应用function，而case则需要更多的一些步骤
//总的思路是遍历switch的个数次，寻找每一个switch中出现了几个case
//应用indexof() function查找所需的特定位置的“{”与“}”符号，从而得到该switch对应的所有内容，从而在次范围内寻找case的个数
public static void  requirement2 (String str) {
	
	int index=0;
	int num=0;
	//完成第一步，get the switch num
	while(str.indexOf("switch",index)!=-1) {
		num++;
		index=str.indexOf("switch",index)+"switch".length();
		
	}
	System.out.println("switch num: "+num);
	int switchnum=num;
	//初始化一些会用到的参数
	System.out.print("case num:");
	num=0;
	index=0;
	int diff=0;
	int begin=0;
	int end=0;
		index=str.indexOf("switch",index)+"switch".length();
		begin=str.indexOf("{",index);
		diff=1;
		index=begin+1;
		//遍历switch的个数次，寻找每一个switch中出现了几个case
			for(int j=0;j<switchnum;j++) {
				
			for(int i=begin+1;i<str.length();i++) {
				index=i;
				if(diff==0) {
					break;
				}
				else {
				if(str.charAt(i)=='{') {
					diff++;
				}
				
				if(str.charAt(i)=='}') {
					diff--;
				}
				
			}
			}
			
		
		end=index;
		
		
		String sub=str.substring(begin+1,end);
		int index1=index;
		index=0;
		num=0;
		while(sub.indexOf("case",index)!=-1) {
			
			index=sub.indexOf("case",index)+"case".length();
			num++;
			}
		System.out.print(" "+num);
		index=str.indexOf("switch",end)+"switch".length();
		begin=str.indexOf("{",index);
		
		index=begin+1;
		diff=1;
		}
			System.out.println();
}
	
//对于requirement3，依旧使用indexOf() function,首先依次查找出各个独立的if，而后查找他是否if else结构
//当使用indexOf()得到所需的“{”与“}”符号时，便可以截取字符串，得到if对应的下一个语句的内容
//而后进行判断，是if else结构还是if ，else if， else结构，并对结果进行累加
public static void  requirement3 (String str) {
	int ifindex=0;
	int ifelsenum=0;
	int begin=0;
	int end=0;
	int diff=0;
	String sub;
	while(str.indexOf("if",ifindex)!=-1) {
		
		ifindex=str.indexOf("if",ifindex)+2;
		if(str.charAt(ifindex-4)!='e') {
		for(int i=ifindex;i<str.length();i++) {
			if(str.charAt(i)=='{') {
				diff++;
			}
			if(str.charAt(i)=='}') {
				diff=diff-1;
				if(diff==0) {
					begin=i;
					break;
				}
			}
			
		}
		end=str.indexOf("{",begin);
		sub=str.substring(begin, end);
				if(sub.indexOf("i")==-1) {
					ifelsenum++;
				}
		}
	}
	System.out.println("if-else num: "+ifelsenum);
	
	
}
//对于requirement3，依旧使用indexOf() function,首先依次查找出各个独立的if，而后查找他是否if，else if，else结构
//当使用indexOf()得到所需的“{”与“}”符号时，便可以截取字符串，得到if对应的下一个语句的内容
//而后进行判断，是if else结构还是if ，else if， else结构，并对结果进行累加
public static void  requirement4 (String str) {
	int ifindex=0;
	int ifelsenum=0;
	int begin=0;
	int end=0;
	int diff=0;
	String sub;
	while(str.indexOf("if",ifindex)!=-1) {
		
		ifindex=str.indexOf("if",ifindex)+2;
		if(str.charAt(ifindex-4)!='e') {
		for(int i=ifindex;i<str.length();i++) {
			if(str.charAt(i)=='{') {
				diff++;
			}
			if(str.charAt(i)=='}') {
				diff=diff-1;
				if(diff==0) {
					begin=i;
					break;
				}
			}
			
		}
		end=str.indexOf("{",begin);
		sub=str.substring(begin, end);
				if(sub.indexOf("i")!=-1) {
					ifelsenum++;
				}
		}
	}
	System.out.println("if-elseif-else num: "+ifelsenum);
	
	
}
}



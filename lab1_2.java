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
	
	//read the data from the file,�����䱣��ΪString��ʽ
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
//ͨ�����β���keyword���ļ��г��ֵĴ��������������ۼӣ��õ����յĴ�
//ʹ����indexOf() function���������ң����String�����ҵ���������ۼ�1������������ֱ���޷��ҵ�
//��Ϊ������keyword doʱ�����ܵ�����keyword double�ĸ��ţ���������Ҫ�ٱ���һ����������
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
//����Ҫ��������������У����ȵõ�switch�ĸ��������Դ�Ϊ���ݱ���switch�ĸ����Σ��õ�����switch��case�ĸ���
//����ʹ��indexof() function���õ���Ӧ�ĸ�����switch�ĸ���ֱ��Ӧ��function����case����Ҫ�����һЩ����
//�ܵ�˼·�Ǳ���switch�ĸ����Σ�Ѱ��ÿһ��switch�г����˼���case
//Ӧ��indexof() function����������ض�λ�õġ�{���롰}�����ţ��Ӷ��õ���switch��Ӧ���������ݣ��Ӷ��ڴη�Χ��Ѱ��case�ĸ���
public static void  requirement2 (String str) {
	
	int index=0;
	int num=0;
	//��ɵ�һ����get the switch num
	while(str.indexOf("switch",index)!=-1) {
		num++;
		index=str.indexOf("switch",index)+"switch".length();
		
	}
	System.out.println("switch num: "+num);
	int switchnum=num;
	//��ʼ��һЩ���õ��Ĳ���
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
		//����switch�ĸ����Σ�Ѱ��ÿһ��switch�г����˼���case
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
	
//����requirement3������ʹ��indexOf() function,�������β��ҳ�����������if������������Ƿ�if else�ṹ
//��ʹ��indexOf()�õ�����ġ�{���롰}������ʱ������Խ�ȡ�ַ������õ�if��Ӧ����һ����������
//��������жϣ���if else�ṹ����if ��else if�� else�ṹ�����Խ�������ۼ�
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
//����requirement3������ʹ��indexOf() function,�������β��ҳ�����������if������������Ƿ�if��else if��else�ṹ
//��ʹ��indexOf()�õ�����ġ�{���롰}������ʱ������Խ�ȡ�ַ������õ�if��Ӧ����һ����������
//��������жϣ���if else�ṹ����if ��else if�� else�ṹ�����Խ�������ۼ�
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



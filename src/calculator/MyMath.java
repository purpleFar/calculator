package calculator;

public class MyMath{
	static String check(String s){
		int sum = (s.charAt(0)=='(')?1:0;
		for(int i=s.length()-1;i==s.length()-1;i--) {
			char c = s.charAt(i);
			if((c<'0'||c>'9')&&c!=')'&&c!='e'&&c!='π') {
				if(s.length()==1)	return "0";
				s = s.substring(0, s.length()-1);
			}
		}
		for(int i=1;i<s.length();i++) {
			char c = s.charAt(i),
				 last = s.charAt(i-1);
			if(c=='(')
				sum++;
			if(c==')')
				sum--;
			if(c=='('||c=='e'||c=='π')
				if((last>='0'&&last<='9')||last=='e'||last=='π'||last==')')
					s = s.substring(0, i)+"×"+s.substring(i++);
		}
		while(sum--!=0)
			s=s+")";
		return s;
	}
	static double cal(double a, char op, double b) {
		switch(op) {
			case '+':return a+b;
			case 'c':return a-b;
			case '×':return a*b;
			case '÷':return a/b;
			case '^':return Math.pow(a, b);
		}
		return a;
	}
	static double number(String s){
		if(s.equals("π"))	return Math.PI;
		if(s.equals("e"))	return Math.E;
		if(s.equals("-π"))	return -Math.PI;
		if(s.equals("-e"))	return -Math.E;
		if(s.charAt(s.length()-1)==')')
			if(s.charAt(0)=='-') return -number(s.substring(2,s.length()-1));
			else return number(s.substring(1,s.length()-1));
		try{
			return Double.parseDouble(s);
		}catch(NumberFormatException e){System.out.println(s+"浮點數轉換錯誤");}
		return 0.0;
	}
	static int findop(String s, char op1, char op2) {
	    int temp = 0;
	    for (int i=s.length()-1;i>=0;i--)
	    {
	        char c = s.charAt(i);
	    	if (c == '(') temp++;
	        if (c == ')') temp--;
	        if (temp == 0 && (c == op1 || c == op2))
	            return i;
	    }
		return -1;
	}
	static double parse(String s) {
	    int M = findop(s, '+', 'c');
	    if (M == -1) M=findop(s, '×', '÷');
	    if (M == -1) M=findop(s, '^', '_');
	    if (M != -1)
	        return cal(parse(s.substring(0,M)), s.charAt(M), parse(s.substring(M+1,s.length())));
	    if (s.charAt(0) == '(' && s.charAt(s.length()-1) == ')')
	        return parse(s.substring(1,s.length()-1));
	    return number(s);
	}
	static String count(String s) {
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i-1)!='('&&s.charAt(i-1)!='E'&&s.charAt(i)=='-')
				s = s.substring(0,i)+"c"+s.substring(i+1,s.length());
		}
		s =  ""+parse(s);
		s=acc(s);
		int index=s.indexOf(".");
		if(s.indexOf("E")!=-1||index==-1)	return s;
		else if(Long.valueOf(s.substring(index+1,s.length()))==0)
			return s.substring(0,index);
		return s;
	}
	static String acc(String s) {
		if(!(s.equals("Infinity")||s.equals("-Infinity"))) {
			int indexE = s.indexOf("E"),
				dot=s.indexOf(".");
			if(indexE==-1) {
				s = ""+Math.round(Double.parseDouble(s)*Math.pow(10,16-dot))/Math.pow(10,16-dot);
			}
			else {
				s = ""+Math.round(Double.parseDouble(s.substring(0,indexE))*Math.pow(10,15))/Math.pow(10,15)+s.substring(indexE);
			}
		}
		return s;
	}
	static String sign(String s) {
		if(s.equals("0")) return s;
		if(s.charAt(0)!='-') return "-"+s;
		return s.substring(1);
	}
	static String mistakeProofing(String s){
		char last = s.charAt(s.length()-1),
			 last2 = (s.length()>1)?s.charAt(s.length()-2):' ',
			 last3 = (s.length()>2)?s.charAt(s.length()-3):' ';
		if(last2 =='y') return "0";
		switch(last) {
			case '.':	for(int i=s.length()-2;i>=0;i--){
							char temp = s.charAt(i);
							if(temp=='.')
								s = s.substring(0, s.length()-1);
							if(temp<'0'||temp>'9')
								break;
						}
						if((last2>'9'||last2<'0')&&last2!='.')
							s = s.substring(0,s.length()-1);
						break;
			case '+':
			case '×':
			case '÷':
			case '^':	if(last2=='('||(last2=='-'&&(last3=='('||last3==' '))) {s = s.substring(0,s.length()-1); break;}
			case '-':	//if(last2=='.')	s = s.substring(0,s.length()-1);
						if(last2=='+'||last2=='÷'||last2=='×'||last2=='^'||last2=='-')
							s = s.substring(0, s.length()-2)+last;
						if(last3==' '&&last2=='0'&&last=='-') s = "-";
						break;	
			case ')':	int temp=0;
						if(last2=='.'||last2=='+'||last2=='×'||last2=='÷'||last2=='^'||last2=='-')
							s = s.substring(0,s.length()-1); 
						for(int i=0;i<s.length();i++) {
							temp+=(s.charAt(i)=='(')?1:0;
							temp-=(s.charAt(i)==')')?1:0;	
						}
						if(temp<0||last2=='(')
							s = s.substring(0,s.length()-1);
						break;
			case '(':	
			case 'e':
			case 'π':	if(last2=='.')
							s = s.substring(0,s.length()-1); 
			default:	if(last2=='0')
							if(last3!='.'&&(last3<'0'||last3>'9'))
								s = s.substring(0, s.length()-2)+last;
						if(last>='0'&&last<='9') {
							if(last2==')'||last2=='e'||last2=='π')
								s = s.substring(0,s.length()-1);
						}
		}
		return s;
	}
}
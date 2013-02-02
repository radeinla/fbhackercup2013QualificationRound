import java.util.HashMap;
import java.util.Scanner;

public class BalancedSmileys {
	private String s;
	
	public BalancedSmileys(String s){
		this.s = s;
	}
	
	private boolean isSmiley(int i){
		if (i == 0){
			return false;
		}
		
		return s.charAt(i-1) == ':';
	}
	
	public boolean isBalanced(){
		int maxOpen = 0;
		int minOpen = 0;
		
		for (int i = 0; i < s.length(); i++){
			if (s.charAt(i) == '('){
				maxOpen++;
				if (!isSmiley(i)){
					minOpen++;
				}
			}else if (s.charAt(i) == ')'){
				minOpen--;
				if (minOpen < 0){
					minOpen++;
				}
				if (!isSmiley(i)){
					maxOpen--;
					if (maxOpen < 0){
						return false;
					}
				}
			}
		}
		
		return maxOpen >= 0 && minOpen == 0;
	}
	
	public static void main(String args[]){
		Scanner scin = new Scanner(System.in);
		
		int t = scin.nextInt();
		scin.nextLine();
		
		for (int i = 1; i <= t; i++){
			String s = scin.nextLine();
			BalancedSmileys bs = new BalancedSmileys(s);
			if (bs.isBalanced()){
				System.out.println("Case #"+i+": YES");
			}else{
				System.out.println("Case #"+i+": NO");
			}
		}
	}
}

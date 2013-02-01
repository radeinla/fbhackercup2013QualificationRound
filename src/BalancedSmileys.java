import java.util.Scanner;

public class BalancedSmileys {
	private String s;
	public BalancedSmileys(String s){
		this.s = s;
	}
	
	public boolean isOther(char c){
		return c != '(' && c != ')';
	}
	
	private boolean isBalanced(int i, long depth, boolean smiley){
		if (i == s.length()){
//			System.out.println("depth: "+depth);
			return depth == 0;
		}
		if (depth > s.length()-i){
//			System.out.println("PRUNE!");
			return false;
		}
		if (s.charAt(i) == '('){
			if (smiley){
//				System.out.println("CONSUME SMILEY");
				return isBalanced(i+1, depth, false);
			}else{
//				System.out.println("ADD DEPTH");
				return isBalanced(i+1, depth+1, false);
			}
		}else if (s.charAt(i) == ')'){
			if (smiley){
				return isBalanced(i+1, depth, false);
			}else if (depth == 0){
				return false;
			}else{
				return isBalanced(i+1, depth-1, false);
			}
		}else if (s.charAt(i) == ':'){
//			System.out.println("TRY SMILEY");
			boolean balancedSmiley = isBalanced(i+1, depth, true);
			if (balancedSmiley){
//				System.out.println("BALANCED SMILEY");
				return true;
			}else{
				boolean balanced = isBalanced(i+1, depth, false);
				if (balanced){
//					System.out.println("BALANCED BUT NOT SMILEY");
				}
				return balanced;
			}
		}else if (smiley){
//			System.out.println("PRUNE2");
			return false;
		} else {
			return isBalanced(i+1, depth, false);
		}
	}
	
	public boolean isBalanced(){
		return isBalanced(0, 0, false);
	}
	
	public static void main(String args[]){
		Scanner scin = new Scanner(System.in);
		
		int t = scin.nextInt();
		scin.nextLine();
		
		for (int i = 1; i <= t; i++){
			String s = scin.nextLine();
//			System.out.println(s);
			BalancedSmileys bs = new BalancedSmileys(s);
			//System.out.println(s);
			//System.out.println(bs.fix());
			if (bs.isBalanced()){
				System.out.println("Case #"+i+": YES");
			}else{
				System.out.println("Case #"+i+": NO");
			}
		}
	}
}

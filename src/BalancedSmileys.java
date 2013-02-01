import java.util.HashMap;
import java.util.Scanner;

public class BalancedSmileys {
	private String s;
	
	private HashMap<MemoKey, Boolean> memo = new HashMap<MemoKey, Boolean>();
	
	public class MemoKey{
		int i = 0;
		long depth = 0;
		boolean smiley = false;
		
		public MemoKey(int i, long depth, boolean smiley){
			this.i = i;
			this.depth = depth;
			this.smiley = smiley;
		}
		
		@Override
		public int hashCode(){
			return (500*500*this.i)+(500*(int)this.depth) + (smiley ? 1 : 0);
		}
		
		@Override
		public boolean equals(Object o){
			MemoKey k = (MemoKey)o;
			return k.hashCode() == this.hashCode();
		}
	}
	
	public BalancedSmileys(String s){
		this.s = s;
	}
	
	public boolean isOther(char c){
		return c != '(' && c != ')';
	}
	
	private boolean isBalanced(int i, long depth, boolean smiley){

		MemoKey key = new MemoKey(i, depth, smiley);
		
		if (memo.get(key) != null){
			return memo.get(key);
		}else{
			boolean balanced = false;
			
			if (i == s.length()){
				balanced = (depth == 0);
			}else if (depth > s.length()-i){
				balanced = false;
			}else if (s.charAt(i) == '('){
				if (smiley){
					balanced = isBalanced(i+1, depth, false);
				}else{
					balanced = isBalanced(i+1, depth+1, false);
				}
			}else if (s.charAt(i) == ')'){
				if (smiley){
					balanced = isBalanced(i+1, depth, false);
				}else if (depth == 0){
					balanced = false;
				}else{
					balanced = isBalanced(i+1, depth-1, false);
				}
			}else if (s.charAt(i) == ':'){
				balanced = isBalanced(i+1, depth, true) || isBalanced(i+1, depth, false);
			}else{
				balanced = isBalanced(i+1, depth, false);
			}
			
			memo.put(key, balanced);
			
			return balanced;
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
			BalancedSmileys bs = new BalancedSmileys(s);
			if (bs.isBalanced()){
				System.out.println("Case #"+i+": YES");
			}else{
				System.out.println("Case #"+i+": NO");
			}
		}
	}
}

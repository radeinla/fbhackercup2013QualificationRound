import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BeautifulStrings {
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	
	private String str;
	
	public BeautifulStrings(String str){
		this.str = str.toLowerCase();
	}
	
	public long getMaxBeauty(){
		Map<Character, Integer> occ = new HashMap<Character, Integer>(str.length());
		for (int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if (alpha.indexOf(c) != -1){
				if (occ.get(c) == null){
					occ.put(c, 0);
				}
				
				occ.put(c, occ.get(c)+1);
			}
		}
		
		ArrayList<Integer> occList = new ArrayList<Integer>();
		for (Map.Entry<Character, Integer> e : occ.entrySet()){
			occList.add(e.getValue());
		}
		
		Collections.sort(occList);
		
		long max = 0;
		int beauty = 26;
		
		for (int i = occList.size()-1; i >= 0; i--){
			int last = occList.get(i);
			max += beauty*last;
			beauty--;
		}
		
		return max;
	}
	
	public static void main(String args[]){
		Scanner scin = new Scanner(System.in);
		
		int m = scin.nextInt();
		scin.nextLine();
		
		for (int x = 1; x <= m; x++){
			BeautifulStrings bs = new BeautifulStrings(scin.nextLine());
			System.out.println("Case #"+x+": " + bs.getMaxBeauty());
		}
	}
}

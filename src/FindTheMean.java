import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class FindTheMean {
	
	public static void addToValues(Map<Long, Integer> values, long v){
		if (values.get(v) == null){
			values.put(v, 1);
		}else{
			values.put(v, values.get(v) + 1);
		}
	}
	
	public static boolean inValues(Map<Long, Integer> values, long v){
		Integer value = values.get(v);
		return value != null && value > 0;
	}
	
	public static void removeFromValues(Map<Long, Integer> values, long v){
		Integer value = values.get(v);
		if (value != null && value > 0){
			values.put(v, value-1);
		}
	}

	public static long findMin(long n, long k, long a, long b, long c, long r) {
		n = ((n - k + k) % (k + 1)) + k + 1;

		List<Long> kValues = new ArrayList<Long>((int) k);
		Map<Long, Integer> values = new HashMap<Long, Integer>();
		TreeSet<Long> valueSet = new TreeSet<Long>();

		kValues.add(a);
		addToValues(values, a);
		valueSet.add(a);

		long lastVal = a;

		TreeSet<Long> poss = new TreeSet<Long>();

		for (int i = 0; i < k + 2; i++) {
			poss.add((long) i);
		}

		for (int j = 1; j < k; j++) {
			lastVal = ((b * lastVal) + c) % r;
			kValues.add(lastVal);
			addToValues(values, lastVal);
			valueSet.add(lastVal);
		}
		
		
		TreeSet<Long> skipped = new TreeSet<Long>();

		for (int j = (int) k; j < n; j++) {
			
			for (long l = poss.pollFirst(); poss.size() > 0; l = poss.pollFirst()) {
				if (!inValues(values, l)) {
					long toRemove = kValues.remove(0);
					removeFromValues(values, toRemove);
					if (!inValues(values, toRemove)){
						valueSet.remove(toRemove);
						if (skipped.size() > 0){
							if (skipped.contains(toRemove)){
								SortedSet<Long> tail = skipped.tailSet(toRemove, true);
								poss.addAll(tail);
								tail.clear();
							}
						}
					}
					lastVal = l;
					kValues.add(lastVal);
					break;
				} else {
					skipped.add(l);
				}
			}
		}

		return lastVal;
	}

	public static void main(String args[]) {
		Scanner scin = new Scanner(System.in);
		int cases = scin.nextInt();
		scin.nextLine();

		for (int i = 1; i <= cases; i++) {
			long n = scin.nextLong();
			long k = scin.nextInt();
			long a = scin.nextInt();
			long b = scin.nextInt();
			long c = scin.nextInt();
			long r = scin.nextInt();
			
			System.out.println("Case #" + i + ": " + findMin(n, k, a, b, c, r));
		}
	}
}
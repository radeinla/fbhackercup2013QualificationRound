import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class FindTheMean {

	public static long findMin(long n, long k, long a, long b, long c, long r) {
		n = ((n - k + k) % (k + 1)) + k + 1;

		List<Long> kValues = new ArrayList<Long>((int) k);
		TreeSet<Long> valueSet = new TreeSet<Long>();

		kValues.add(a);
		valueSet.add(a);

		long lastVal = a;

		List<Long> poss = new ArrayList<Long>();

		for (int i = 0; i < k + 1; i++) {
			poss.add((long) i);
		}

		for (int j = 1; j < k; j++) {
			lastVal = ((b * lastVal) + c) % r;
			if (lastVal < 0){
				throw new RuntimeException("NEGATIVE!");
			}
			kValues.add(lastVal);
			valueSet.add(lastVal);
			if (poss.get(0) == lastVal) {
				poss.add(poss.remove(0));
			}
		}
		
		System.out.println("GENERATED INITIAL");

		for (int j = (int) k; j < n/* k + k + 1 */; j++) {
			for (long l = poss.get(0); poss.size() > 0; l = poss.get(0)) {
				if (!valueSet.contains(l)) {
					poss.remove(0);
					long toRemove = kValues.remove(0);
					if (!kValues.contains(toRemove)) {
						valueSet.remove(toRemove);
					}
					if (poss.contains(toRemove) && toRemove < poss.get(0)
							&& !valueSet.contains(toRemove)) {
						poss.remove(toRemove);
						poss.add(0, toRemove);
					}
					lastVal = l;
					kValues.add(lastVal);
					valueSet.add(lastVal);
					break;
				} else {
					poss.add(poss.remove(0));
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
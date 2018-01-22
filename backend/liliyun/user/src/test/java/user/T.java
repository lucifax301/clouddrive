package user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> a = new ArrayList<>();
		a.add("a");
		a.add("b");
		a.add("c");
		a.add("d");
		a.add("e");
		
		for (int i=0;i<a.size();i++) {
			if (a.get(i).equals("b")){
				a.remove(i);
			}
		}
		
		System.out.println(a);
		
		
		int [] aa = {1,2,3};
		
		System.out.println(Arrays.asList(aa).size());

	}

}

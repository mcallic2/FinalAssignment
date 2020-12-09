import java.util.*;

public class Question3 {

	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
		
		E maybe = list[0];
		for(int i = 1; i< list.length; i++) {
			if(maybe.compareTo(list[i])<0) {
				maybe = list[i];
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		
		Integer[] list = {3,4,5,1,-3,-5,-1};
		System.out.println(linearSearch(list,2));
		System.out.println(linearSearch(list,5));
		

	}

}

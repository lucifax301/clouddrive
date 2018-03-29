package httpaccess;

import java.util.List;

public class Test2 {

	public <T> void dotest(Class<T> cls){
		Test1 t = new Test1();
		t.test(cls);
	}
	
	public static void main(String args[]){
		Test2 t=new Test2();
		t.<List>dotest(List.class);
	}
}

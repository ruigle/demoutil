package cn.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionTest {
	
	public static void main(String[] args) {
		/*
        List<String> listS = new ArrayList<String>();
        List<Employer1> list1 = new ArrayList<Employer1>();
        List<Employer2> list2 = new ArrayList<Employer2>();
        List<Employer3> list3 = new ArrayList<Employer3>();

        //一.将String类型的变量插入到listS中并排序
        //listS中的对象String 本身含有compareTo方法，所以可以直接调用sort方法，按自然顺序排序，即升序排序
        listS.add("5");
        listS.add("2");
        listS.add("9");
        Collections.sort(listS);

        //二.将Employer1类的对象插入到list1中并排序
        //将已创建的实现了Comparator接口的比较类MyCompare传入Collections的sort方法中即可实现依照MyCompare类中的比较规则。
        Employer1 a1 = new Employer1();
        Employer1 b1 = new Employer1();
        Employer1 c1 = new Employer1();
        a1.setName("a1");   a1.setAge(44);
        b1.setName("b1");   b1.setAge(55);
        c1.setName("b1");   c1.setAge(33);
        list1.add(a1);
        list1.add(b1);
        list1.add(c1);//Collections类的sort方法要求传入的第二个参数是一个已实现Comparator接口的比较器
        Collections.sort(list1, new MyCompare());

        //三.将Employer2类的对象插入到list2中并排序
        //其实原理和上面的二类似，只是没有单独创建MyCompare类，而是用匿名内部类来实现Comparator接口里面的具体比较。
        Employer2 a2 = new Employer2();
        Employer2 b2 = new Employer2();
        Employer2 c2 = new Employer2();
        a2.setName("a2");   a2.setAge(66);
        b2.setName("b2");   b2.setAge(33);
        c2.setName("b2");   c2.setAge(22);
        list2.add(a2);
        list2.add(b2);
        list2.add(c2); //Collections类的sort方法要求传入的第二个参数是一个已实现Comparator接口的比较器
        Collections.sort(list2,new Comparator<Employer2>(){
            @Override
            public int compare(Employer2 a2, Employer2 b2) {
                return a2.getOrder().compareTo(b2.getOrder());
            }

        });

        //四.将Employer3类的对象插入到list3中并排序
        //被排序的类Employer3实现了Comparable接口,在类Employer3中通过重载compareTo方法来实现具体的比较。
        Employer3 a3 = new Employer3();
        Employer3 b3 = new Employer3();
        Employer3 c3 = new Employer3();
        a3.setName("a3");   a3.setAge(77);
        b3.setName("b3");   b3.setAge(55);
        c3.setName("b3");   c3.setAge(99);
        list3.add(a3);
        list3.add(b3);
        list3.add(c3);
        Collections.sort(list3);//Collections类的sort方法要求传入的List中的对象是已实现Comparable接口的对象

        System.out.println(listS);
        System.out.println(list1);
        System.out.println(list3);
        System.out.println(list2);
		
		
		/*
		 List c = new ArrayList();
         c.add("w");
         c.add("o");
         c.add("r");
         c.add("l");
         c.add("d");
         System.out.println(c);
         Collections.shuffle(c);
         System.out.println(c);
         Collections.shuffle(c);
         System.out.println(c);
		*/
		
		/*
		List c = new ArrayList();
        c.add("w");
        c.add("o");
        c.add("r");
        c.add("a");
        c.add("d");
        System.out.println(c);
        int m = Collections.binarySearch(c, "o");
        System.out.println(Collections.min(c));
		*/
		/*
		List list = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(list);
	    List subList = Arrays.asList("three four five six".split(" "));
	    System.out.println(Collections.indexOfSubList(list, subList));
		*/
		
		/*
		List list = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(list);
	    Collections.reverse(list);
	    System.out.println(list);
		*/
		
		/*
		List list = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(list);
	    Collections.rotate(list, 2);
	    System.out.println(list);
		*/
		/*
		List m = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(m);
	    List n = Arrays.asList("我 是 复制过来的哈".split(" "));
	    System.out.println(n);
	    Collections.copy(m,n);
	    System.out.println(m);
		*/
		
		/*
		List m = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(m);
	    Collections.swap(m, 2, 4);
	    System.out.println(m);
	    */
		/*
		List m = Arrays.asList("one two three four five six siven".split(" "));
	    System.out.println(m);
	    Collections.fill(m, "haha52T25xixi");
	    System.out.println(m);
		*/
		
		List<String> nCopies = Collections.nCopies(5, "haha");
		System.out.println(nCopies);
    }
}
class Employer1{
    private String name;
    private Integer age;
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override//重载了Object类里的toString方法，使之可以按照我们要求的格式打印
    public String toString() {
        return "name is "+name+" age is "+ age;
    }
}
class MyCompare implements Comparator<Employer1> {
    @Override//重载了Comparator接口里面的compare方法实现具体的比较
    public int compare(Employer1 o1, Employer1 o2) {
        return o2.getAge().compareTo(o1.getAge());
    }
}
class Employer2{
    private String name;
    private Integer age;
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOrder() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override//重载了Object类里的toString方法，使之可以按照我们要求的格式打印
    public String toString() {
        return "name is "+name+" age is "+age;
    }
}
class Employer3 implements Comparable<Employer3>{
    private String name;
    private Integer age;
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override//重载了Object类里的toString方法，使之可以按照我们要求的格式打印
    public String toString() {
        return "name is "+name+" age is "+age;
    }
    @Override//重载了Comparable接口里的compareTo方法来实现具体的比较
    public int compareTo(Employer3 a) {
        return this.age.compareTo(a.getAge());
    }
    
}

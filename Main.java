import java.util.*;

class Trie
{
	Trie ch[];
	int wc;
	boolean ended;
	Trie()
	{
		ch=new Trie[26];
		wc=0;
		ended=false;
	}
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Trie root=new Trie();
		for(int i=0;i<n;i++)
		{
			int q=sc.nextInt();
			if(q==1)
			{
				String s=sc.next();
				insert(root,s);
			}
			else if(q==2)
			{
				List<String>li=new ArrayList<>();
				String te="";
				getAllWords(root,li,te);
				System.out.println("The words are:");
				for(int j=0;j<li.size();j++)
				{
					System.out.println(li.get(j));
				}
			}
			else if(q==3)
			{
				String s=sc.next();
				System.out.println(doesExists(root,s));
			}
			else
			{
				String s=sc.next();
				List<String>li=new ArrayList<>();
				getAllWordsWithPrefix(root,li,s);
				System.out.println("The words with prefix"+s+"are:");
				for(int j=0;j<li.size();j++)
				{
					System.out.println(li.get(j));
				}
			}
		}

	}
	static void insert(Trie root,String s)
	{
		Trie te=root;
		for(char ci:s.toCharArray())
		{
			int ind=ci-'a';
			if(te.ch[ind]==null)
				te.ch[ind]=new Trie();
			te=te.ch[ind];
			te.wc++;
		}
		te.ended=true;
	}
	static void getAllWords(Trie root,List<String>li,String te)
	{
		if(root.ended)li.add(te);
		for(int i=0;i<26;i++)
		{
			if(root.ch[i]!=null)
			{
				Character c=(char)(i+'a');
				getAllWords(root.ch[i],li,te+c);
			}
		}
	}
	static boolean doesExists(Trie root,String s)
	{
		Trie te=root;
		for(char ci:s.toCharArray())
		{
			int ind=ci-'a';
			if(te.ch[ind]==null)
				return false;
			te=te.ch[ind];
		}
		return te.ended;
	}
	static void getAllWordsWithPrefix(Trie root,List<String>li,String ps)
	{
		Trie te=root;
		for(char ci:ps.toCharArray())
		{
			int ind=ci-'a';
			if(te.ch[ind]==null)
				return;
			te=te.ch[ind];
		}
		getAllWords(te,li,ps);
	}

}
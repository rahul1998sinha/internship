package abcd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
 
class TrieNode 
{
    char data; 
    boolean isEnd; 
    int count;  
    LinkedList<TrieNode> childList; 
 
    /* Constructor */
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        data = c;
        count = 0;
    }  
    public TrieNode getChild(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
}
 
public class TrieDataStructure
{
    private TrieNode root;
 
     /* Constructor */
    public TrieDataStructure()
    {
        root = new TrieNode(' '); 
    }
    /* This function is used to insert a word in trie*/
    public void insert(String word)
    {
        if (search(word) == true) 
            return;        
        TrieNode current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.getChild(ch);
            if (child != null)
                current = child;
            else 
            {
             // If child not present, adding it io the list
                 current.childList.add(new TrieNode(ch));
                 current = current.getChild(ch);
            }
            current.count++;
        }
        current.isEnd = true;
    }
    /* This function is used to search a word in trie*/
    public boolean search(String word)
    {
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.getChild(ch) == null)
                return false;
            else
                current = current.getChild(ch);
        }      
        if (current.isEnd == true) 
            return true;
        return false;
    }
    /* This function is used to remove function from trie*/
    public void remove(String word)
    {
        if (search(word) == false)
        {
            System.out.println(word +" does not present in trien");
            return;
        }             
        TrieNode current = root;
        for (char ch : word.toCharArray()) 
        { 
            TrieNode child = current.getChild(ch);
            if (child.count == 1) 
            {
                current.childList.remove(child);
                return;
            } 
            else 
            {
                child.count--;
                current = child;
            }
        }
        current.isEnd = false;
    }
  
    public static void printAllWordsInTrie(TrieNode root,String s)
    {
      TrieNode current = root;
      if(root.childList==null || root.childList.size()==0)
       return;
      Iterator<TrieNode> iter=current.childList.iterator();
     while(iter.hasNext())
     {
      TrieNode node= iter.next();
      s+=node.data;
      printAllWordsInTrie(node,s);
      if(node.isEnd==true)
      { 
       System.out.print(" "+s);
       s=s.substring(0,s.length()-1);
      }
      else
      {
       s=s.substring(0,s.length()-1);
      }
     }
      
    }
    public static void main(String[] args) throws IOException
    {            
    	String line;
    	BufferedReader br = new BufferedReader(new FileReader(args[0])); 
    	line=br.readLine();
    	String[] data = line.split(",");
    	TrieDataStructure t = new TrieDataStructure();       
        for(String s:data)
        {
        	t.insert(s);
        }
        printAllWordsInTrie(t.root,"");              
    }
}
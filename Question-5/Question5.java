import java.util.HashSet;

/**
 *
 * @author gabriel.villacrez
 */
public class Node 
{
     int val; 
     Node next; 
  
     public Node(int val)  
     { 
        this.val = val; 
     } 
    
    public void removeDuplicates(Node head)  
    { 
        // Hash to store seen values 
        HashSet<Integer> values = new HashSet<>(); 
      
        /* Pick elements one by one */
        Node current = head; 
        Node prev = null;
        //Walk through the list
        while (current != null)  
        { 
            int curval = current.val; 
              
             // If current value is seen before 
            if (values.contains(curval)) 
            {
                prev.next = current.next; 
            }
            else
            { 
                values.add(curval); 
                prev = current; 
            } 
            current = current.next; 
        } 
  
    } 
      
    
}
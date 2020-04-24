/**
 *
 * @author gabriel.villacrez
 */


public class Questao7 
{

	  /* Link list node */
	static class Node 
	{ 
	  
		int data; 
		Node next; 
	}; 
  
// Function to return the intersection point 
// of the two linked lists head1 and head2 
// returns the pointer to the intersection
static Node getIntesectionNode(Node head1, Node head2) 
{ 
    Node current1 = head1; 
    Node current2 = head2; 
  
    // If one of the head is null 
    if (current1 == null || current2 == null ) 
        return null; 
  
    // Continue until find intersection node 
    while (current1 != null && current2 != null
        && current1 != current2) 
    { 
        current1 = current1.next; 
        current2 = current2.next; 
  
        // If we get intersection node 
        if (current1 == current2) 
            return current1;
  
        // If one of them reaches end 
        if (current1 == null ) 
            current1 = head2; 
        if (current2 == null ) 
            current2 = head1; 
    } 
  
    return current1;
} 
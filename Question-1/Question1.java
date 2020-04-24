public void replace_all_spaces(String input, int size)
{
	//trim() to remove the extra spaces provided in input
    //using regex, \\s refers to white spaces
    
	String replaced = input.trim()replaceAll("\\s", "&32");
}


char[] replaceSpaces(char[] str) 
{ 
        // count spaces to find current length of the string
        int space_count = 0, i = 0; 
        for (i = 0; i < str.length; i++) 
            if (str[i] == ' ') 
                space_count++; 
  
        // count spaces and find current length 
        while (str[i - 1] == ' ') 
        { 
            space_count--; 
            i--; 
        } 
  
        // Find new length of desired string with room for extra chars. 
        int new_length = i + space_count * 2; 
  
  
        // Start replacing the spaces from the end of string
        int index = new_length - 1; 
  
        char[] new_str = str; 
        str = new char[new_length]; 
  
        // Fill rest of the string from end 
        for (int j = i - 1; j >= 0; j--)  
        { 
  
            // inserts &32 in the places of spaces 
            if (new_str[j] == ' ')  
            { 
                str[index] = '2'; 
                str[index - 1] = '3'; 
                str[index - 2] = '&'; 
                index = index - 3; 
            }  
              
            else 
            { 
                str[index] = new_str[j]; 
                index--; 
            } 
        } 
        return str; 
} 
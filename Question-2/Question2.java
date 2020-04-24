public boolean isParcialPermutation(String origin, String perm)
{
        //sizes of each string
        int n1 = origin.length();
        int n2 = perm.length();

        //number of different chars
        int diff = 0;

        // If length of both strings is not same, it cant be a permutation
        if (n1 != n2)
            return false;

        // converting to arrays to make in order to compare char by char
        char ch1[] = origin.toCharArray();
        char ch2[] = perm.toCharArray();

        //checking the first char of each string
        if(ch1[0] == ch2[0])
        {

            //if the word has more than 3 letters
            if( (n2 > 3) )
            {
                for (int i =0 ; i < n1; i++)
                {
                    if (ch1[i] != ch2[i])
                        diff++;
                }

                if ((diff > 2 * n2 / 3) )
                    return false;
            }
            return true;
        }
        return false;
}
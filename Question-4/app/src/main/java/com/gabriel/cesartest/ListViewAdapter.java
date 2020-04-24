package com.gabriel.cesartest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter
{
        Context mContext;
        LayoutInflater inflater;

        private ArrayList<WordNames> arrayList;

        public ListViewAdapter(Context context)
        {
            mContext = context;
            inflater = LayoutInflater.from(mContext);
            this.arrayList = new ArrayList<WordNames>();
            this.arrayList.addAll(MainActivity.wordNamesArrayList);
        }

        public class ViewHolder
        {
            TextView name;
        }

        @Override
        public int getCount() {
            return MainActivity.wordNamesArrayList.size();
        }

        @Override
        public WordNames getItem(int position)
        {
            return MainActivity.wordNamesArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent)
        {
            final ViewHolder holder;
            if (view == null)
            {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.listview_item, null);
                holder.name = (TextView) view.findViewById(R.id.name);
                view.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) view.getTag();
            }
            // Set the results into TextViews
            holder.name.setText(MainActivity.wordNamesArrayList.get(position).getWordName());

            return view;
        }

        // Filter method -> Parcial permutation OR typo but not both
        public void filter(String charText)
        {
            charText = charText.toLowerCase(Locale.getDefault());
            MainActivity.wordNamesArrayList.clear();
            if (charText.length() == 0)
            {
                MainActivity.wordNamesArrayList.addAll(arrayList);
            }
            else
            {
                for (WordNames wp : arrayList)
                {
                    String name = wp.getWordName().toLowerCase(Locale.getDefault());
                    //verification to display the word
                    if( hasTypo(name,charText) || isParcialPermutation(name, charText) )
                    {
                        MainActivity.wordNamesArrayList.add(wp);
                    }

                }
            }
            notifyDataSetChanged();
        }

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

    public int getLevenshteinDistance(String s, String t)
    {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m)
        {
            // swap the input strings to consume less memory
            String tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n+1]; //'previous' cost array, horizontally
        int d[] = new int[n+1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i<=n; i++) {
            p[i] = i;
        }

        for (j = 1; j<=m; j++)
        {
            t_j = t.charAt(j-1);
            d[0] = j;

            for (i=1; i<=n; i++)
            {
                cost = s.charAt(i-1)==t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }
    public boolean hasTypo(String origin, String result)
    {
        int distance = getLevenshteinDistance( origin  , result );

        if(distance > 1)
           return false;
        else
            return true;
    }



}


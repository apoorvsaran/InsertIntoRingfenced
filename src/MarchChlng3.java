/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

import java.util.Scanner;

class MarchChlng3 {

	public static void main(String[] args) {
		
		Scanner ob = new Scanner(System.in);
		int t = ob.nextInt();
		while(t-- > 0)
		{
			int n = ob.nextInt();
			int a[] = new int[n];
			int b[] = new int[n];
			String s[] = new String[n];
			int count = 0;
			int x = 0;
			
			for(int i=0;i<n;i++)
			{
				s[i] = ob.next();
			}
			for(int i=0;i<n;i++)
			{
				int ax, ex, ix, ox, ux;
				ax=ex=ix=ox=ux=0;
				for(int j=0;j<s[i].length();j++)
				{
					if(ax==0 && s[i].charAt(j)=='a')
						ax = 16;
					else if(ex==0 && s[i].charAt(j)=='e')
						ex = 8;
					else if(ix==0 && s[i].charAt(j)=='i')
						ix = 4;
					else if(ox==0 && s[i].charAt(j)=='o')
						ox = 2;
					else if(ux==0 && s[i].charAt(j)=='u')
						ux = 1;
					a[i] = ax+ex+ix+ox+ux;
					if(a[i]==31)
					{
						count++;
						break;
					}
				}
				if(a[i]!=31)
				{
					b[x++] = a[i];
				}
			}
			int ct = 0;
			for(int i=0;i<x-1;i++)
			{
				for(int j=i+1;j<x;j++)
				{
					if(((b[i]|b[j])&31)==31)
					{
						ct++;
					}
				}
			}
			System.out.println(ct+(count*(n)-(count*(count+1))/2));
		}

	}

}
